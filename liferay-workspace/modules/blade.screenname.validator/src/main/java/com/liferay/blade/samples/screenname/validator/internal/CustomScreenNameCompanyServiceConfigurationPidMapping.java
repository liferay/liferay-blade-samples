package com.liferay.blade.samples.screenname.validator.internal;

import com.liferay.blade.samples.screenname.validator.CustomScreenName;
import com.liferay.blade.samples.screenname.validator.CustomScreenNameConfiguration;
import com.liferay.portal.kernel.settings.definition.ConfigurationPidMapping;
import org.osgi.service.component.annotations.Component;


/**
 * User: Romeo Sheshi <a href="rsheshi@gmail.com">Romeo Sheshi</a>
 * Date: 26/10/2016
 * Time: 15:21
 */
@Component
public class CustomScreenNameCompanyServiceConfigurationPidMapping
	implements ConfigurationPidMapping {

	@Override
	public Class<?> getConfigurationBeanClass() {
		return CustomScreenNameConfiguration.class;
	}

	@Override
	public String getConfigurationPid() {
		return CustomScreenName.SETTINGS_ID;
	}

}