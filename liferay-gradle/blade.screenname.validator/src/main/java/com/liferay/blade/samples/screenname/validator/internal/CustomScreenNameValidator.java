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
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Locale;

/**
 * Screen Name validator, providing methods to validate the screen name
 * client and server.
 * @author Romeo Sheshi
 */
@Component(
	configurationPid = "com.liferay.blade.samples.screenname.validator.CustomScreenNameConfiguration",
	immediate = true, property = {"service.ranking:Integer=100"},
	service = ScreenNameValidator.class
)
public class CustomScreenNameValidator implements ScreenNameValidator {
    /**
     * Returns the javascript function to validate the screenName client side.
     *
     * @return the javascript function
     */
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

    /**
     * Returns the error message that display to user it can be localized using resource bundle.
     * @param locale  the error message locale
     * @return the error message to display
     */
	@Override
	public String getDescription(Locale locale) {
		return "The screen name contains reserved words";
	}

    /**
     *  Returns the  validation of screen name true if is valid.
     * @param companyId  the company id  of portal instance that the user belong
     * @param screenName the user input of the screen name
     * @return true if user is valid else false
     */
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

    /**
     * Returns the configuration of the plugin based on companyId.
     * @param companyId the company id  of portal instance that the user belong
     * @return  {@link com.liferay.blade.samples.screenname.validator.CustomScreenNameConfiguration}
     */
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

    /**
     * Returns the reserved words configured in fondation settings.
     * @param companyId   the company id  of portal instance that the user belong
     * @return String[] the reserved words default to "admin|user"
     */
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