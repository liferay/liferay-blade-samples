package com.liferay.blade.samples.servletfilter;

import com.liferay.portal.kernel.servlet.filters.invoker.InvokerFilterHelper;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.List;

import javax.portlet.GenericPortlet;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * A hacky demo portlet to list the available filters in the current system.
 * Don't deploy this in production, but use it in order to figure out the
 * before-filter property for the BladeServletFilter in this project.
 * 
 * Then delete this portlet from the sample code.
 * 
 * @author Olaf Kock
 */
@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.instanceable=false",
			"javax.portlet.display-name=Show ServletFilters",
			"javax.portlet.name=com_liferay_blade_samples_servletfilter_BladeServletFilterListingDemoPortlet",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class BladeServletFilterListingDemoPortlet extends GenericPortlet {
	@Override
	public void doView(RenderRequest request, RenderResponse response) 
			throws PortletException, IOException {
		PrintWriter out = response.getWriter();

		try {
			HttpServletRequest hsr = PortalUtil.getOriginalServletRequest(
					PortalUtil.getHttpServletRequest(request));
			Object ifh = hsr.getServletContext().getAttribute(
						    InvokerFilterHelper.class.getName());			
			Field filterNamesField = ifh.getClass().getDeclaredField("_filterNames");
			filterNamesField.setAccessible(true);
			@SuppressWarnings("unchecked")
			List<String> filterNames = (List<String>) filterNamesField.get(ifh);

			out.println("It looks like the following ");
			out.println(filterNames.size());
			out.println(" filters are currently available in your system:<br/>");
			out.println("Use them e.g. in your filter's 'before-filter' property");
			out.println("<ul>");

			for (String name : filterNames) {
				out.println("<li>" + name + "</li>");
			}
			out.println("</ul>");
		} catch (Exception e) {
			e.printStackTrace(out);
		}
	}
}
