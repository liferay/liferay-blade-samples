/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
 * Provides methods to validate the user's screen name client-side and
 * server-side.
 *
 * @author Romeo Sheshi
 */
@Component(
	configurationPid = "com.liferay.blade.samples.screenname.validator.CustomScreenNameConfiguration",
	immediate = true, property = "service.ranking:Integer=100",
	service = ScreenNameValidator.class
)
public class CustomScreenNameValidator implements ScreenNameValidator {

	/**
	 * Returns the JavaScript function to validate the screen name client-side.
	 *
	 * @return the JavaScript function
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
			_log.error(pe);
		}

		return javascript.toString();
	}

	/**
	 * Returns the error message to display to the user. The message can be
	 * localized using a resource bundle.
	 *
	 * @param  locale the error message's locale
	 * @return the error message to display
	 */
	@Override
	public String getDescription(Locale locale) {
		return "The screen name contains reserved words";
	}

	/**
	 * Returns <code>true</code> if the user's screen name is valid.
	 *
	 * @param  companyId the ID of the portal instance to which the user belongs
	 * @param  screenName the user's screen name
	 * @return <code>true</code> if the user's screen name is valid else;
	 *         <code>false</code> otherwise
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
	 * Returns the plugin's configuration based on the company ID.
	 *
	 * @param  companyId the ID of the portal instance to which the user belongs
	 * @return {@link CustomScreenNameConfiguration}
	 */
	private CustomScreenNameConfiguration _getConfiguration(long companyId) {
		try {
			return _configurationProvider.getConfiguration(
				CustomScreenNameConfiguration.class,
				new CompanyServiceSettingsLocator(
					companyId, CustomScreenName.SETTINGS_ID));
		}
		catch (ConfigurationException ce) {
			_log.error("Error initializing the configuration", ce);
		}

		return null;
	}

	/**
	 * Returns the reserved words configured in the Control Panel &rarr;
	 * Configuration &rarr; System Settings &rarr; Foundation &rarr; ScreenName
	 * Validator menu.
	 *
	 * @param  companyId the ID of the portal instance to which the user belongs
	 * @return the reserved words, which by default are <code>admin|user</code>
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