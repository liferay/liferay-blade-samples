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

package com.liferay.blade.sample.notifications.notifications;

import com.liferay.blade.sample.notifications.constants.BladeNotificationPortletKeys;
import com.liferay.blade.sample.notifications.portlet.BladeNotificationPortlet;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Subscription;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.SubscriptionSender;

import java.util.List;

/**
 * @author Vilmos Papp
 */
public class BladeNotificationSubscriptionSender extends SubscriptionSender {

	@Override
	public void flushNotifications() throws Exception {
		List<Subscription> subscriptions =
			SubscriptionLocalServiceUtil.getSubscriptions(
				companyId, BladeNotificationPortlet.class.getName(), 0);

		for (Subscription subscription : subscriptions) {
			try {
				User user = UserLocalServiceUtil.fetchUserById(
					subscription.getUserId());

				sendNotification(user, true);
			}
			catch (Exception exception) {
				_log.error(
					"Unable to process subscription: " + subscription,
					exception);
			}
		}
	}

	public void setSender(String sender) {
		_sender = sender;
	}

	public void setValue(String value) {
		_value = value;
	}

	@Override
	protected void populateNotificationEventJSONObject(
		JSONObject notificationEventJSONObject) {

		super.populateNotificationEventJSONObject(notificationEventJSONObject);

		notificationEventJSONObject.put(
			BladeNotificationPortletKeys.SAMPLE_VALUE, _value);
		notificationEventJSONObject.put(
			BladeNotificationPortletKeys.SENDER, _sender);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BladeNotificationSubscriptionSender.class);

	private String _sender;
	private String _value;

}