package com.liferay.blade.samples.screenname.validator.definition;

import com.liferay.blade.samples.screenname.validator.CustomScreenName;
import com.liferay.blade.samples.screenname.validator.CustomScreenNameConfiguration;
import com.liferay.portal.kernel.settings.definition.ConfigurationPidMapping;

import org.osgi.service.component.annotations.Component;

/**
 * @author Romeo Sheshi
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