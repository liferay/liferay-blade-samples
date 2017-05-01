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
	protected void doView(RenderRequest request, RenderResponse response)
		throws IOException, PortletException {

		PrintWriter printWriter = response.getWriter();

		String fileName = request.getParameter("fileName");
		String mimeType = request.getParameter("mimeType");
		String version = request.getParameter("version");
		String statusLabel = request.getParameter("statusLabel");
		String createdDate = request.getParameter("createdDate");
		String createdUserName = request.getParameter("createdUserName");

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