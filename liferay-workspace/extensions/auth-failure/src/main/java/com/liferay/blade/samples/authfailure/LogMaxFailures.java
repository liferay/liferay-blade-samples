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

package com.liferay.blade.samples.authfailure;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.AuthFailure;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.log.LogService;

/**
 * @author Liferay
 */
@Component(
	immediate = true, property = "key=auth.max.failures",
	service = AuthFailure.class
)
public class LogMaxFailures implements AuthFailure {

	@Override
	public void onFailureByEmailAddress(
			long companyId, String emailAddress,
			Map<String, String[]> headerMap, Map<String, String[]> parameterMap)
		throws AuthException {

		try {
			User user = UserLocalServiceUtil.getUserByEmailAddress(
				companyId, emailAddress);

			boolean lockout = user.isLockout();

			_log.log(
				LogService.LOG_INFO,
				"onFailureByEmailAddress: " + emailAddress + " is " +
					(lockout ? "" : "not") + " locked out.");
		}
		catch (PortalException pe) {
			_log.log(LogService.LOG_ERROR, pe.getMessage(), pe);
		}
	}

	@Override
	public void onFailureByScreenName(
			long companyId, String screenName, Map<String, String[]> headerMap,
			Map<String, String[]> parameterMap)
		throws AuthException {

		try {
			User user = UserLocalServiceUtil.getUserByScreenName(
				companyId, screenName);

			boolean lockout = user.isLockout();

			_log.log(
				LogService.LOG_INFO,
				"onFailureByScreenName: " + screenName + " is " +
					(lockout ? "" : "not") + " locked out.");
		}
		catch (PortalException pe) {
			_log.log(LogService.LOG_ERROR, pe.getMessage(), pe);
		}
	}

	@Override
	public void onFailureByUserId(
			long companyId, long userId, Map<String, String[]> headerMap,
			Map<String, String[]> parameterMap)
		throws AuthException {

		try {
			User user = UserLocalServiceUtil.getUserById(userId);

			boolean lockout = user.isLockout();

			_log.log(
				LogService.LOG_INFO,
				"onFailureById: userId " + userId + " is " +
					(lockout ? "" : "not") + " locked out.");
		}
		catch (PortalException pe) {
			_log.log(LogService.LOG_ERROR, pe.getMessage(), pe);
		}
	}

	@Reference
	private LogService _log;

}