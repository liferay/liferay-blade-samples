# Resource Bundle Hook

The Resource Bundle Hook sample illustrates the recommended approach to hook an
application language keys file for any module that is deployed to Liferay’s OSGi
runtime (not applicable to Liferay's core language keys). 

This specific example overrides the default `add-blog-entry` key for Liferay's
default blogs application, which is located in
`liferay-portal/blob/master/modules/apps/collaboration/blogs/blogs-web/src/main/resources/content`,
for both English and Spanish. 

When this sample hook gets deployed on a Liferay DXP portal, if we add the blogs
application to a page in English, the button to add a new blog entry will
display the text "Overriden Add Blog Entry". If the language is then changed to
Spanish, we will see the text "Añadir entrada sobreescrita".

![Figure 1: The customized Blog application with the new add-blog-entry messages for English.](https://github.com/codyhoag/liferay-docs/blob/blade-sample-images/develop/tutorials/blade-images/hook-resourcebundle.png)

This example highlights the main steps to hook and override applications
language keys, which are:

- Implementing a resource bundle loader
- Registering the service
- Providing the new language keys which will override the original ones

The resource bundle loader is a class that should implement the interface
`com.liferay.portal.kernel.util.ResourceBundleLoader`. A closer look to our
sample source code will show that we need to implement the `loadResourceBundle`
method, returning our loaded resource bundle:

	@Override
	public ResourceBundle loadResourceBundle(String languageId) {
		return _resourceBundleLoader.loadResourceBundle(languageId);
	}
	
The rest of the code is setting the resource bundle loader into which the
resource bundles are loaded as an AggregateResourceBundleLoader.

	@Reference(target = "(&(bundle.symbolic.name=com.liferay.blogs.web)(!(component.name=com.liferay.blade.samples.hook.resourcebundle.ResourceBundleLoaderComponent)))")
	public void setResourceBundleLoader(
		ResourceBundleLoader resourceBundleLoader) {

		_resourceBundleLoader = new AggregateResourceBundleLoader(
			new CacheResourceBundleLoader(
				new ClassResourceBundleLoader(
					"content.Language",
					ResourceBundleLoaderComponent.class.getClassLoader())),
			resourceBundleLoader);
	}

Notice that in the @Reference annotation, we are specifying that we want to
target the original bundle, with `com.liferay.blogs.web` as symbolic name, which
is not the component
`com.liferay.blade.samples.hook.resourcebundle.ResourceBundleLoaderComponent`. 

Also note the required parameters for the ClassResourceBundleLoader, which are:

- `content.Language` as the base name
- The classloader for your resource bundle loader
- The resource bundle loader from the method’s parameter

Your class should also register the resource bundle loader in the OSGi runtime.
To accomplish that, the following three properties should be set:

- `bundle.symbolic.name=` - The symbolic name of the target module (the module
	whose keys you're overriding)
- `resource.bundle.base.name=` - The resource bundle base name that points to
  your language files
- `servlet.context.name=` - The servlet context name of the target module

For this example, the @Component annotation looks like this:

	@Component(
		immediate = true,
		property = {
			"bundle.symbolic.name=com.liferay.blogs.web",
			"resource.bundle.base.name=content.Language",
			"servlet.context.name=blogs-web"
		}
	)

The last step to do is adding the new language.properties files to the folder
`src/content` for each locale whose keys you want to override. As for this
example we are interested in override only the English and Spanish keys, we
added two new language.properties files `Language_en.properties` and
`Language_es.properties`.

Please bear in mind that this approach can be used to override any application
language keys, which means `language.properties` files that are inside a module
deployed to Liferay’s OSGi runtime. If you need to override any Liferay's core
language keys, please visit
[Liferay Developer Network - Modifying Liferay's Language Keys](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/modifying-liferays-language-keys).

More information on how to use a resource bundle hook to override applications
language keys can be found in
[Liferay Developer Network - Overriding a Module's Language Keys](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/overriding-a-modules-language-keys#implementing-a-resource-bundle-loader).