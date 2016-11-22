package com.liferay.blade.samples.screenname.validator.internal;

import com.liferay.blade.samples.screenname.validator.CustomScreenName;
import com.liferay.blade.samples.screenname.validator.CustomScreenNameConfiguration;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.security.auth.ScreenNameValidator;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.settings.CompanyServiceSettingsLocator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Romeo Sheshi
 */
@Component(
	configurationPid = "com.liferay.blade.samples.screenname.validator.CustomScreenNameConfiguration",
	immediate = true, property = {"service.ranking:Integer=100"},
	service = ScreenNameValidator.class
)
public class CustomScreenNameValidator implements ScreenNameValidator {

	@Override
	public String getAUIValidatorJS() {
		StringBuilder javascript = new StringBuilder();

		try {
			Company company = _companyLocalService.getCompanyByWebId(
				PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));

			long companyId = company.getCompanyId();

			String[] reservedWords = _getReservedWords(companyId);

			javascript.append("function(val) { return !(");

			for (int i = 0; i < reservedWords.length; i++) {
				javascript.append(
					"val.indexOf(\"" + reservedWords[i] + "\") !== -1");

				if ((reservedWords.length > 1) &&
					(i < (reservedWords.length - 1))) {

					javascript.append(" || ");
				}
			}

			javascript.append(")}");
		}
		catch (PortalException pe) {
		}

		return javascript.toString();
	}

	@Override
	public String getDescription(Locale locale) {
		return "The screen name contains reserved words";
	}

	@Override
	public boolean validate(long companyId, String screenName) {
		String safeScreenName = StringUtil.toLowerCase(screenName);
		String[] reservedWords = _getReservedWords(companyId);

		for (String word : reservedWords) {
			if (safeScreenName.contains(StringUtil.toLowerCase(word))) {
				return false;
			}
		}

		return true;
	}

	private CustomScreenNameConfiguration _getConfiguration(long companyId) {
		try {
			return _configurationProvider.getConfiguration(
				CustomScreenNameConfiguration.class,
				new CompanyServiceSettingsLocator(
					companyId, CustomScreenName.SETTINGS_ID));
		}
		catch (ConfigurationException ce) {
			_log.error("Error initializing the configuration.");
		}

		return null;
	}

	private String[] _getReservedWords(long companyId) {
		CustomScreenNameConfiguration configuration = _getConfiguration(
			companyId);

		if (configuration != null) {
			return configuration.reservedWords();
		}

		return new String[0];
	}

	private static Log _log = LogFactoryUtil.getLog(
		CustomScreenNameValidator.class);

	@Reference
	private volatile CompanyLocalService _companyLocalService;

	@Reference
	private volatile ConfigurationProvider _configurationProvider;

}