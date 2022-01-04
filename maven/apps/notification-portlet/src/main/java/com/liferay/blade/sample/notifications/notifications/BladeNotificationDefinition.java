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
import com.liferay.portal.kernel.model.UserNotificationDeliveryConstants;
import com.liferay.portal.kernel.notifications.UserNotificationDefinition;
import com.liferay.portal.kernel.notifications.UserNotificationDeliveryType;

import org.osgi.service.component.annotations.Component;

/**
 * @author Vilmos Papp
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + BladeNotificationPortletKeys.BLADE_NOTIFICATION,
	service = UserNotificationDefinition.class
)
public class BladeNotificationDefinition extends UserNotificationDefinition {

	public BladeNotificationDefinition() {
		super(
			BladeNotificationPortletKeys.BLADE_NOTIFICATION, 0,
			BladeNotificationType.NOTIFICATION_TYPE_BLADE,
			"receive-a-notification-from-blade-portlet");

		addUserNotificationDeliveryType(
			new UserNotificationDeliveryType(
				"email", UserNotificationDeliveryConstants.TYPE_EMAIL, true,
				true));
		addUserNotificationDeliveryType(
			new UserNotificationDeliveryType(
				"website", UserNotificationDeliveryConstants.TYPE_WEBSITE, true,
				true));
	}

}