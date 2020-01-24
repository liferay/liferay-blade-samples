/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

// To figure out valid values for before-filter, look up Liferay's
// WEB-INF/liferay-web.xml

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
		service = Filter.class
)
public class BladeServletFilter extends BaseFilter {

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	protected void processFilter(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, FilterChain filterChain)
		throws Exception {

		// implement your own logic here
		// determine if you shortcut, or continue in the filter chain

		httpServletResponse.addHeader(
			"X-Blade-Servlet-Filter", httpServletRequest.getRequestURI());

		processFilter(
			BladeServletFilter.class.getName(), httpServletRequest,
			httpServletResponse, filterChain);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BladeServletFilter.class);

}