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

package com.liferay.blade.samples.strutsportletaction;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.log.LogService;

/**
 * @author Liferay
 */
@Component(
	immediate = true, property = "path=/login/login",
	service = StrutsPortletAction.class
)
public class BladePortletAction extends BaseStrutsPortletAction {

	@Override
	public void processAction(
			StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		_log.log(LogService.LOG_INFO, "BladePortletAction - processAction");

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User loggedinUser = themeDisplay.getUser();

		if (loggedinUser != null) {
			_log.log(
				LogService.LOG_INFO,
				"Logging in with user:[" + loggedinUser.getFirstName() + " " +
					loggedinUser.getLastName() + "]");

			_log.log(
				LogService.LOG_INFO,
				"Logged in user: Current Greetings[" +
					loggedinUser.getGreeting() + "]");
		}

		originalStrutsPortletAction.processAction(
			originalStrutsPortletAction, portletConfig, actionRequest,
			actionResponse);
	}

	@Override
	public String render(
			StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse)
		throws Exception {

		_log.log(LogService.LOG_INFO, "BladePortletAction - render");

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User loggedinUser = themeDisplay.getUser();

		if (loggedinUser != null) {
			loggedinUser.setLastName("BLADE");

			loggedinUser.setGreeting(
				"Hello," + loggedinUser.getFirstName() + " from BLADE!");

			_userLocalService.updateUser(loggedinUser);
		}

		return originalStrutsPortletAction.render(
			originalStrutsPortletAction, portletConfig, renderRequest,
			renderResponse);
	}

	@Override
	public void serveResource(
			StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, ResourceRequest resourceRequest,
			ResourceResponse resourceResponse)
		throws Exception {

		_log.log(LogService.LOG_INFO, "BladePortletAction - serveResource");

		originalStrutsPortletAction.serveResource(
			originalStrutsPortletAction, portletConfig, resourceRequest,
			resourceResponse);
	}

	@Reference
	private LogService _log;

	@Reference(unbind = "-")
	private volatile UserLocalService _userLocalService;

}