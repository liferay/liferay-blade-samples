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
package blade.portlet.actioncommand;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.ActionCommand;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;


/**
 * @author Kamesh Sampath
 *
 */
@Component(
	immediate = true,
	property = {
		"action.command.name=greet",
		"javax.portlet.name=blade_portlet_GreeterPortlet"
	},
	service = ActionCommand.class
)
public class GreeterActionCommand implements ActionCommand {

	@Override
	public boolean processCommand(PortletRequest portletRequest,
		PortletResponse portletResponse)
		throws PortletException {

		_log.info("Processing Greeting Action");

		if(portletRequest instanceof ActionRequest
						&& portletResponse instanceof ActionResponse){

			_handleActionCommand((ActionRequest)portletRequest,
				(ActionResponse)portletResponse);

		}

		return true;
	}

	private void _handleActionCommand(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		String name = ParamUtil.get(actionRequest, "name", StringPool.BLANK);

		_log.info("Hello " + name);

		String greetingMessage = "Hello " + name + "! Welcome to OSGi";

		actionRequest.setAttribute("GREETER_MESSAGE", greetingMessage);

		SessionMessages.add(actionRequest,
			"greeting_message",greetingMessage);
	}

	private Log _log = LogFactoryUtil.getLog(GreeterActionCommand.class);

}
