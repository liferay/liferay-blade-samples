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
	property = "type=" + TemplateContextContributor.TYPE_THEME,
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