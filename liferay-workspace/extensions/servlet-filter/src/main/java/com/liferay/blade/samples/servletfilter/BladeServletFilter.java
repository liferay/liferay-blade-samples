package com.liferay.blade.samples.servletfilter;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BaseFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * Dynamically deployable ServletFilter sample.
 * Observe the comments all over this module for more information and
 * TODOs when you create your own filter from this template
 * 
 * @author Olaf Kock
 */

@Component(
		immediate = true,
		property = {
// To figure out valid values for before-filter, deploy the portlet 
// in this project and drop it on a page. If you omit this value or
// specify a nonexisting value, this filter will be added to the end
// of the list.
				"before-filter=Auto Login Filter",
				"dispatcher=REQUEST",
//				"dispatcher=FORWARD",
//				"dispatcher=ASYNC",
//				"dispatcher=ERROR",
//				"dispatcher=INCLUDE",
				"servlet-context-name=",
				// Pick your own unique filter name! Make sure to pick a
				// different one for every filter you write - see LPS-107575
				"servlet-filter-name=Blade Servlet Filter",
				"url-pattern=/web/*",
				"url-pattern=/change/me"
		},
		service=Filter.class
)

public class BladeServletFilter extends BaseFilter {
	
	@Override
	protected void processFilter(HttpServletRequest httpServletRequest, 
			HttpServletResponse httpServletResponse, FilterChain filterChain)
					throws Exception {
		// implement your own logic here
		// determine if you shortcut, or continue in the filter chain
		String requestURI = httpServletRequest.getRequestURI();
		httpServletResponse.addHeader("X-Blade-Servlet-Filter", requestURI);
		
		processFilter(BladeServletFilter.class.getName(), httpServletRequest, 
				httpServletResponse, filterChain);
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	private static final Log _log = LogFactoryUtil.getLog(BladeServletFilter.class);
}
