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

package blade.document.action.portlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.GenericPortlet;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author liferay
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=BladeDocumentAction Portlet",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class BladeDocumentActionPortlet extends GenericPortlet {

	@Override
	protected void doView(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		PrintWriter printWriter = renderResponse.getWriter();

		String fileName = renderRequest.getParameter("fileName");
		String mimeType = renderRequest.getParameter("mimeType");
		String version = renderRequest.getParameter("version");
		String statusLabel = renderRequest.getParameter("statusLabel");
		String createdDate = renderRequest.getParameter("createdDate");
		String createdUserName = renderRequest.getParameter("createdUserName");

		printWriter.print(
			"<span style=\"color:green\">File Name</span>:" + fileName +
				"<br/>");
		printWriter.print(
			"<span style=\"color:green\">Type</span>:" + mimeType + "<br/>");
		printWriter.print(
			"<span style=\"color:green\">Version</span>:" + version + "<br/>");
		printWriter.print(
			"<span style=\"color:green\">Status</span>:" + statusLabel +
				"<br/>");
		printWriter.print(
			"<span style=\"color:green\">Created Date</span>:" + createdDate +
				"<br/>");
		printWriter.print(
			"<span style=\"color:green\">Created User Name</span>:" +
				createdUserName + "<br/>");
	}

}