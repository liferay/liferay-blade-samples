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

package com.liferay.blade.samples.portlet.form.taglib.override;

import com.liferay.portal.kernel.servlet.taglib.TagDynamicInclude;
import com.liferay.portal.search.web.constants.SearchPortletKeys;
import com.liferay.taglib.aui.FormTag;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Carlos Sierra Andrés
 */
@Component(immediate = true, service = TagDynamicInclude.class)
public class SampleSearchPortletFormTagDynamicInclude
	implements TagDynamicInclude {

	public void include(
			HttpServletRequest request, HttpServletResponse response,
			String tagClassName, String tagDynamicId, String tagPoint)
		throws IOException {

		PrintWriter printWriter = response.getWriter();

		printWriter.println(
			"<h2>Sample Search Portlet Form Override</h2><br />");
	}

	public void register(TagDynamicIncludeRegistry tagDynamicIncludeRegistry) {
		tagDynamicIncludeRegistry.register(
			FormTag.class.getCanonicalName(), SearchPortletKeys.SEARCH + "-fm",
			"doStartTag#before");
	}

}