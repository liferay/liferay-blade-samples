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

package com.liferay.blade.samples.doclib.resourcecommand.override;

import com.liferay.document.library.web.constants.DLPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.log.LogService;

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

	@Activate
	public void activate() {
		_log.log(
			LogService.LOG_INFO, "Blade Doclib Resource Command Deployed!");
	}

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

	@Reference
	private LogService _log;

}