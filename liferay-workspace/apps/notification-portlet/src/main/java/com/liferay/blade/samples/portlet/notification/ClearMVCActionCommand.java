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
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;

import java.util.List;

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
		"mvc.command.name=clear"
	},
	service = MVCActionCommand.class
)
public class ClearMVCActionCommand implements MVCActionCommand {

	@Override
	public boolean processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		_handleActionCommand(actionRequest);

		return true;
	}

	private void _handleActionCommand(ActionRequest actionRequest) {
		String clearNotificationMessage = null;

		try {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				actionRequest);

			DynamicQuery dynamicQuery =
				UserNotificationEventLocalServiceUtil.dynamicQuery();

			Property typeProperty = PropertyFactoryUtil.forName("type");
			Property userIdProperty = PropertyFactoryUtil.forName("userId");

			dynamicQuery.add(
				typeProperty.eq(NotificationPortletKeys.NOTIFICATION));
			dynamicQuery.add(userIdProperty.eq(serviceContext.getUserId()));

			List<UserNotificationEvent> userNotificationEvents =
				UserNotificationEventLocalServiceUtil.dynamicQuery(
					dynamicQuery);

			int count = 0;

			for (UserNotificationEvent userNotificationEvent :
					userNotificationEvents) {

				UserNotificationEventLocalServiceUtil.
					deleteUserNotificationEvent(userNotificationEvent);
				count++;
			}

			clearNotificationMessage =
				"<b>" + count + "</b> notifications have been cleared!";
		}
		catch (Exception exception) {
			_log.error(exception);
		}

		actionRequest.setAttribute(
			"CLEAR_NOTIFICATION_MESSAGE", clearNotificationMessage);

		SessionMessages.add(
			actionRequest, "clearNotificationMessage",
			clearNotificationMessage);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ClearMVCActionCommand.class);

}