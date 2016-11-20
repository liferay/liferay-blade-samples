package com.liferay.blade.samples.screenname.internal.configuration;

import com.liferay.blade.samples.screenname.configuration.CustomScreenNameConfiguration;
import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;
import org.osgi.service.component.annotations.Component;


/**
 * User: Romeo Sheshi <a href="rsheshi@gmail.com">Romeo Sheshi</a>
 * Date: 20/11/2016
 * Time: 15:21
 */
@Component
public class CustomScreenNameCompanyServiceConfigurationBeanDeclaration
		implements ConfigurationBeanDeclaration {

	@Override
	public Class<?> getConfigurationBeanClass() {
		return CustomScreenNameConfiguration.class;
	}

}