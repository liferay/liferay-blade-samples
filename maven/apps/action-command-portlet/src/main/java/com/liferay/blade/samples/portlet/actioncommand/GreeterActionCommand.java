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

package com.liferay.blade.samples.portlet.actioncommand;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

/**
 * @author Liferay
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=com_liferay_blade_samples_portlet_actioncommand_GreeterPortlet",
		"mvc.command.name=greet"
	},
	service = MVCActionCommand.class
)
public class GreeterActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		_handleActionCommand(actionRequest);

		return true;
	}

	private void _handleActionCommand(ActionRequest actionRequest) {
		String name = ParamUtil.get(actionRequest, "name", StringPool.BLANK);

		if (_log.isInfoEnabled()) {
			_log.info("Hello " + name);
		}

		String greetingMessage = "Hello " + name + "! Welcome to OSGi";

		actionRequest.setAttribute("GREETER_MESSAGE", greetingMessage);

		SessionMessages.add(actionRequest, "greetingMessage", greetingMessage);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		GreeterActionCommand.class);

}