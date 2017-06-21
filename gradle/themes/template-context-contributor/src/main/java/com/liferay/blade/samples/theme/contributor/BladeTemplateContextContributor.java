/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.blade.samples.theme.contributor;

import com.liferay.portal.kernel.template.TemplateContextContributor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * Provides the ability to inject variables into a template's context. You can
 * inject custom variables into your platform's global context (e.g., themes,
 * application display templates, DDM templates, etc.) or into the theming
 * context only.
 *
 * <p>
 * To change the context in which you want to inject your variables, update the
 * <code>property</code> attribute of the <code>@Component</code> annotation.
 * For example, the following logic would inject your variables into the global
 * context:
 * </p>
 *
 * <p>
 * <pre>
 * <code>
 * property = {"type=" + TemplateContextContributor.TYPE_GLOBAL}
 * </code>
 * </pre>
 * </p>
 *
 * @author Liferay
 */
@Component(
	immediate = true,
	property = {"type=" + TemplateContextContributor.TYPE_THEME},
	service = TemplateContextContributor.class
)
public class BladeTemplateContextContributor
	implements TemplateContextContributor {

	/**
	 * Injects a new string variable into the map of provided variables. The map
	 * is made available to non-JSP templates (FreeMarker, Velocity, etc.) that
	 * do not have access to the contextual objects native to the platform, like
	 * the request and session.
	 *
	 * <p>
	 * The <code>sample_text</code> variable can be used in any theme file.
	 * For example, you could add it to the <code>portal_normal.ftl</code> file
	 * in your theme as <code>${sample_text}</code>.
	 * </p>
	 *
	 * @param contextObjects the variables available in the context
	 * @param request the HTTP servlet request
	 */
	@Override
	public void prepare(
		Map<String, Object> contextObjects, HttpServletRequest request) {

		contextObjects.put("sample_text", "This is some sample text");
	}

}