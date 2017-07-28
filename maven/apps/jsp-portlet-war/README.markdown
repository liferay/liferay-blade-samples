# Liferay MVC Portlet

The Liferay MVC portlet provides a way to add various different fields into the database and display them in a table. This project is a Liferay MVC based portlet WAR that implements the same functionality as the `blade.servicebuilder.web` project. It manages JSP pages for display, uses a Liferay-annotated portlet class, and invokes the `blade.servicebuilder.api` to call services. Unlike the `blade.servicebuilder.web` module, this Liferay MVC portlet is delivered as a portlet WAR project. This project builds to a WAR file but leverages all of the Liferay Workspace tools and Gradle to build the WAR. You must build and deploy the `blade.servicebuilder.api` and `blade.servicebuilder.svc` modules for this sample to work properly.

You can easily modify this sample by customizing its `JSPPortlet` Java class or any of its JSPs stored in the `src/main/webapp` folder. For more information on customizing this sample, see the Javadoc listed in the `JSPPortlet` Java class.

Although this project is built and deployed as a portlet war, it still relies on the WAR->WAB conversion to repackage the portlet as an OSGi-friendly bundle. To that end, it relies on the same kinds of OSGi component declaration and reference resolution as a standard Liferay MVC portlet bundle.

NOTE: The javax.portlet.name value comes from the &lt;portlet-name /&gt; tag value from the liferay-portlet.xml file, except all punctuation will be removed.  For example, "blade-liferay-mvc-sample-portlet" would become "bladeliferaymvcsampleportlet".

The web context path comes from the Web-ContextPath key value from the liferay-plugin-package.properties file.