package com.liferay.blade.samples.screenname.validator.definition;

import com.liferay.blade.samples.screenname.validator.CustomScreenNameConfiguration;
import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;

import org.osgi.service.component.annotations.Component;

/**
 * @author Romeo Sheshi
 */
@Component
public class CustomScreenNameCompanyServiceConfigurationBeanDeclaration
	implements ConfigurationBeanDeclaration {

	@Override
	public Class<?> getConfigurationBeanClass() {
		return CustomScreenNameConfiguration.class;
	}

}