# JSP Hook

The JSP Hook sample conveys Liferay's recommended approach to hook an application JSP by leveraging on OSGi fragment modules.

This specific example overrides the default login.jsp in com.liferay.login.web bundle, by adding the text "changed" in red, to the top of the Sign In form.

![Figure 1: The customized Sign In form with the "Changed" text.](https://github.com/codyhoag/liferay-docs/blob/blade-sample-images/develop/tutorials/blade-images/hook-jsp.png)

A closer look to this example source code will reveal that the end goal is achieved through the following steps:
- Declaring the fragment host
- Providing the JSP that will override the original one

To properly declare the fragment host in the bnd.bnd file, we need to specify the Bundle Symbolic Name of the host module (where the original JSP is located) and the exact version of the host module to which the fragment belongs. In this example, this would look like
	
	Fragment-Host: com.liferay.login.web;bundle-version="1.0.0"

The only thing that is left to do is providing the new JSP, which will override the original one. One important thing to keep in mind is to make sure that you mimic the host module’s folder structure when overriding its JAR. For this example, as the original JSP is in the folder `/META-INF/resources/login.jsp`, we have our new JSP file in the folder `src/main/resource/META-INF/resource/login.jsp`.

If needed, you can also target the original JSP, following one of the two possible naming conventions: `portal` or `original`. The pattern that can be used would look like the following example:

	<liferay-util:include 
    page="/login.original.jsp" (or login.portal.jsp) 
    servletContext="<%= application %>" 
	/> 

Please bear in mind that this approach can be used to override any application JSP, which means JSPs that are inside a module. If you need to override a core JSP, please visit the [blade.corejsphook README](https://github.com/liferay/liferay-blade-samples/blob/master/liferay-gradle/blade.corejsphook/README.markdown) to learn more about how to override a core JSP.

More information about how to use fragment modules to override application JSPs can be found in [Liferay Developer Network](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/overriding-a-modules-jsps)