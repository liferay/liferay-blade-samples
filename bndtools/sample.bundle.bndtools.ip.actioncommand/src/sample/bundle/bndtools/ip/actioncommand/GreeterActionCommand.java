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
package sample.bundle.bndtools.ip.actioncommand;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.ActionCommand;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;


/**
 * @author Kamesh Sampath
 *
 */
@Component(property={"action.command.name=greet",
					 "javax.portlet.name=greeter"},
		   service=ActionCommand.class)
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
		
		String greeterName = ParamUtil.get(actionRequest,
			"name", StringPool.BLANK);
		
		_log.info("Greeter name:"+greeterName);		
		
		String greetingMessage = "Hello "+greeterName+"! Welcome to OSGI";
		
		actionRequest.setAttribute("GREETER_MESSAGE", greetingMessage);
		
		SessionMessages.add(actionRequest,
			"greeting_message",greetingMessage);
		
	}

	private Log _log = LogFactoryUtil.getLog(GreeterActionCommand.class);

}
