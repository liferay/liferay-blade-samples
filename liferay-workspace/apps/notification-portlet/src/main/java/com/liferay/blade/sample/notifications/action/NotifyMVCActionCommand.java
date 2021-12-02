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

package com.liferay.blade.sample.notifications.action;

import com.liferay.blade.sample.notifications.constants.BladeNotificationPortletKeys;
import com.liferay.blade.sample.notifications.notifications.BladeNotificationSubscriptionSender;
import com.liferay.blade.sample.notifications.notifications.BladeNotificationType;
import com.liferay.blade.sample.notifications.portlet.BladeNotificationPortlet;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.SubscriptionLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author István András Dézsi
 * @author Vilmos Papp
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + BladeNotificationPortletKeys.BLADE_NOTIFICATION,
		"mvc.command.name=/send_notification"
	},
	service = MVCActionCommand.class
)
public class NotifyMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (Validator.isNull(cmd)) {
			return;
		}

		if (BladeNotificationPortletKeys.NOTIFY.equals(cmd)) {
			long companyId = _portal.getCompanyId(actionRequest);

			String email = ParamUtil.getString(
				actionRequest, BladeNotificationPortletKeys.USER_EMAIL);

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				actionRequest);

			notifySubscriber(companyId, email, serviceContext);
		}
	}

	protected void notifySubscriber(
		long companyId, String userEmail, ServiceContext serviceContext) {

		String entryTitle = "Blade notification";

		String fromName = "Blade Notification Sender";
		String fromAddress = "blade.notifications@liferay.com";

		BladeNotificationSubscriptionSender subscriptionSender =
			new BladeNotificationSubscriptionSender();

		subscriptionSender.setValue("sample value");

		User sender = _userLocalService.fetchUserById(
			serviceContext.getUserId());

		subscriptionSender.setSender(sender.getScreenName());

		subscriptionSender.setBody("Blade Notification Body Text");
		subscriptionSender.setClassPK(0);
		subscriptionSender.setClassName(
			BladeNotificationPortlet.class.getName());
		subscriptionSender.setCompanyId(companyId);
		subscriptionSender.setCurrentUserId(serviceContext.getUserId());
		subscriptionSender.setEntryTitle(entryTitle);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setMailId("Blade Notification", 0);
		subscriptionSender.setNotificationType(
			BladeNotificationType.NOTIFICATION_TYPE_BLADE);
		subscriptionSender.setPortletId(
			BladeNotificationPortletKeys.BLADE_NOTIFICATION);
		subscriptionSender.setReplyToAddress(fromAddress);
		subscriptionSender.setServiceContext(serviceContext);
		subscriptionSender.setSubject("Blade Notification Subject");

		subscriptionSender.addPersistedSubscribers(
			BladeNotificationPortlet.class.getName(), 0);

		subscriptionSender.flushNotificationsAsync();
	}

	@Reference(unbind = "-")
	protected void setSubscriptionLocalService(
		final SubscriptionLocalService subscriptionLocalService) {

		_subscriptionLocalService = subscriptionLocalService;
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(
		final UserLocalService userLocalService) {

		_userLocalService = userLocalService;
	}

	@Reference
	private Portal _portal;

	private SubscriptionLocalService _subscriptionLocalService;
	private UserLocalService _userLocalService;

}