# blade.springmvc.web

This is a Spring-MVC-based portlet war that implements the same functionality
as the blade.servicebuilder.web project.  It manages JSP pages for display, it
uses a Spring-annotated portlet class, and it invokes the blade.servicebuilder.api
to call services.

Unlike blade.servicebuilder.web, Spring-MVC portlets must be delivered as portlet
war projects. This project builds to a war file using Maven and the Liferay 7 
CE/Liferay DXP APIs for compatibility.
