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
