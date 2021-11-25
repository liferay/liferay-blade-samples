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

package main.java.com.liferay.blade.samples.portlet.notification;

import com.liferay.blade.samples.constants.NotificationPortletKeys;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;

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
		"javax.portlet.name=" + NotificationPortletKeys.NOTIFICATION,
		"mvc.command.name=notify"
	},
	service = MVCActionCommand.class
)
public class NotifyMVCActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		_handleActionCommand(actionRequest);

		return true;
	}

	private void _handleActionCommand(ActionRequest actionRequest) {
		String userNotificationMessage = null;

		try {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				actionRequest);

			UserNotificationEventLocalServiceUtil.sendUserNotificationEvents(
				serviceContext.getUserId(),
				NotificationPortletKeys.NOTIFICATION,
				UserNotificationDeliveryConstants.TYPE_WEBSITE, true, false,
				JSONFactoryUtil.createJSONObject());

			userNotificationMessage =
				"User notification Sent! Please check your notifications!";
		}
		catch (Exception exception) {
			_log.error(exception);
		}

		actionRequest.setAttribute(
			"USER_NOTIFICATION_MESSAGE", userNotificationMessage);

		SessionMessages.add(
			actionRequest, "userNotificationMessage", userNotificationMessage);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		NotifyMVCActionCommand.class);

}