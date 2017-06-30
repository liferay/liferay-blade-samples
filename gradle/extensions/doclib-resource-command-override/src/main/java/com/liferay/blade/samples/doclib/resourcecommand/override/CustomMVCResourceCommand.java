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

package com.liferay.blade.samples.doclib.resourcecommand.override;

import com.liferay.document.library.web.constants.DLPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Liferay
 */
@Component(
	property = {
		"javax.portlet.name=" + DLPortletKeys.DOCUMENT_LIBRARY,
		"javax.portlet.name=" + DLPortletKeys.DOCUMENT_LIBRARY_ADMIN,
		"javax.portlet.name=" + DLPortletKeys.MEDIA_GALLERY_DISPLAY,
		"mvc.command.name=/document_library/edit_folder", "service.rank=100"
	},
	service = MVCResourceCommand.class
)
public class CustomMVCResourceCommand implements MVCResourceCommand {

	@Override
	public boolean serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		final String cmd = ParamUtil.getString(resourceRequest, Constants.CMD);

		if (cmd.equals("BOOKMARK_ORDERABLE")) {
			serveOlderUserBookmarks(resourceRequest, resourceResponse);
		}
		else {
			_editFolderMVCResourceCommand.serveResource(
				resourceRequest, resourceResponse);
		}

		return true;
	}

	protected void serveOlderUserBookmarks(
		ResourceRequest resourceRequest, ResourceResponse resourceResponse) {

		// do your custom logic

	}

	@Reference(
		target = "(component.name=com.liferay.document.library.web.portlet.action.EditFolderMVCResourceCommand)"
	)
	private MVCResourceCommand _editFolderMVCResourceCommand;

}