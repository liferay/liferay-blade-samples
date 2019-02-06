package com.liferay.blade.login.web.resource.bundle.override;

import com.liferay.portal.kernel.util.AggregateResourceBundleLoader;
import com.liferay.portal.kernel.util.CacheResourceBundleLoader;
import com.liferay.portal.kernel.util.ClassResourceBundleLoader;
import com.liferay.portal.kernel.util.ResourceBundleLoader;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Liferay
 */
@Component(
	immediate = true,
	property = {
		"bundle.symbolic.name=com.liferay.login.web",
		"resource.bundle.base.name=content.Language",
		"servlet.context.name=login-web"
	}
)
public class LoginModuleResourceBundleLoader implements ResourceBundleLoader {

	@Override
	public ResourceBundle loadResourceBundle(Locale locale) {
		return _resourceBundleLoader.loadResourceBundle(locale);
	}

	@Reference(
		target = "(&(bundle.symbolic.name=com.liferay.login.web)(!(resource.bundle.aggregate=*))(!(component.name=com.liferay.samples.login.web.resource.bundle.LoginModuleResourceBundleLoader)))"
	)

	public void setResourceBundleLoader(
		ResourceBundleLoader resourceBundleLoader) {

		_resourceBundleLoader = new AggregateResourceBundleLoader(
			new CacheResourceBundleLoader(
				new ClassResourceBundleLoader(
					"content.Language",
					LoginModuleResourceBundleLoader.class.getClassLoader())),
			resourceBundleLoader);
	}

	private AggregateResourceBundleLoader _resourceBundleLoader;

}
