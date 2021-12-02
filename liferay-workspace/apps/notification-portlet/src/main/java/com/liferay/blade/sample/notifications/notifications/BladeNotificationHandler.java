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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.notifications.BaseUserNotificationHandler;
import com.liferay.portal.kernel.notifications.UserNotificationFeedEntry;
import com.liferay.portal.kernel.notifications.UserNotificationHandler;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.StringUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Vilmos Papp
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + BladeNotificationPortletKeys.BLADE_NOTIFICATION,
	service = UserNotificationHandler.class
)
public class BladeNotificationHandler extends BaseUserNotificationHandler {

	public BladeNotificationHandler() {
		setPortletId(BladeNotificationPortletKeys.BLADE_NOTIFICATION);
	}

	@Override
	public UserNotificationFeedEntry interpret(
			UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws PortalException {

		try {
			UserNotificationFeedEntry userNotificationFeedEntry = doInterpret(
				userNotificationEvent, serviceContext);

			if (userNotificationFeedEntry != null) {
				userNotificationFeedEntry.setOpenDialog(isOpenDialog());
				userNotificationFeedEntry.setPortletId(getPortletId());
			}
			else {
				String body = getBody(userNotificationEvent, serviceContext);

				userNotificationFeedEntry = new UserNotificationFeedEntry(
					false, body, "", false);
			}

			return userNotificationFeedEntry;
		}
		catch (Exception exception) {
			_log.error("Unable to interpret notification", exception);
		}

		return null;
	}

	@Override
	protected String getBody(
			UserNotificationEvent userNotificationEvent,
			ServiceContext serviceContext)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			userNotificationEvent.getPayload());

		String value = jsonObject.getString(
			BladeNotificationPortletKeys.SAMPLE_VALUE);
		String sender = jsonObject.getString(
			BladeNotificationPortletKeys.SENDER);

		String title = LanguageUtil.get(serviceContext.getLocale(), _TITLE_KEY);

		String body = LanguageUtil.format(
			serviceContext.getLocale(), _BODY_KEY,
			new Object[] {sender, value});

		return StringUtil.replace(
			_BODY_TEMPLATE, _BODY_REPLACEMENTS, new String[] {title, body});
	}

	@Reference(unbind = "-")
	protected void setCompanyLocalService(
		final CompanyLocalService companyLocalService) {

		_companyLocalService = companyLocalService;
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(
		final UserLocalService userLocalService) {

		_userLocalService = userLocalService;
	}

	private static final String _BODY_KEY =
		"{0} has sent you a Blade Notification with value: {1}";

	private static final String[] _BODY_REPLACEMENTS = {
		"[$TITLE$]", "[$BODY$]"
	};

	private static final String _BODY_TEMPLATE =
		"<div class=\"title\">[$TITLE$]</div><div class=\"body\">[$BODY$]" +
			"</div>";

	private static final String _TITLE_KEY = "Blade Notification";

	private static final Log _log = LogFactoryUtil.getLog(
		BladeNotificationHandler.class);

	private CompanyLocalService _companyLocalService;
	private UserLocalService _userLocalService;

}