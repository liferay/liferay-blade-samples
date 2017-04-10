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

package com.liferay.blade.samples.strutsportletaction;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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

/**
 * @author Liferay
 */
@Component(
	immediate = true, property = {"path=/login/login"},
	service = StrutsPortletAction.class
)
public class BladePortletAction extends BaseStrutsPortletAction {

	@Override
	public void processAction(
			StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug("BladePortletAction - procesAction");
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User loggedinUser = themeDisplay.getUser();

		if ((loggedinUser != null) && _log.isInfoEnabled()) {
			_log.info(
				"Logging in with user:[" + loggedinUser.getFirstName() + " " +
					loggedinUser.getLastName() + "]");

			_log.info(
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

		if (_log.isDebugEnabled()) {
			_log.debug("BladePortletAction - render");
		}

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

		if (_log.isDebugEnabled()) {
			_log.debug("BladePortletAction - serveResource");
		}

		originalStrutsPortletAction.serveResource(
			originalStrutsPortletAction, portletConfig, resourceRequest,
			resourceResponse);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BladePortletAction.class);

	@Reference(unbind = "-")
	private volatile UserLocalService _userLocalService;

}