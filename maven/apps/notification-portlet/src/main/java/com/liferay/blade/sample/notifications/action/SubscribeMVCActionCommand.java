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
import com.liferay.blade.sample.notifications.portlet.BladeNotificationPortlet;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.SubscriptionLocalService;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Vilmos Papp
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + BladeNotificationPortletKeys.BLADE_NOTIFICATION,
		"mvc.command.name=/update_subscription"
	},
	service = MVCActionCommand.class
)
public class SubscribeMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (Validator.isNull(cmd)) {
			return;
		}

		long userId = _portal.getUserId(actionRequest);

		if (Constants.SUBSCRIBE.equals(cmd)) {
			_subscriptionLocalService.addSubscription(
				userId, 0, BladeNotificationPortlet.class.getName(), 0);
		}
		else if (Constants.UNSUBSCRIBE.equals(cmd)) {
			_subscriptionLocalService.deleteSubscription(
				userId, BladeNotificationPortlet.class.getName(), 0);
		}
	}

	@Reference(unbind = "-")
	protected void setSubscriptionLocalService(
		final SubscriptionLocalService subscriptionLocalService) {

		_subscriptionLocalService = subscriptionLocalService;
	}

	@Reference
	private Portal _portal;

	private SubscriptionLocalService _subscriptionLocalService;

}