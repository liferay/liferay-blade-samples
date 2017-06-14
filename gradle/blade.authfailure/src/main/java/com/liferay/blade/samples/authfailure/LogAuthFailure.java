/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.blade.samples.authfailure;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.AuthFailure;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Liferay
 */
@Component(
	immediate = true, property = {"key=auth.failure"},
	service = AuthFailure.class
)
public class LogAuthFailure implements AuthFailure {

	@Override
	public void onFailureByEmailAddress(
			long companyId, String emailAddress,
			Map<String, String[]> headerMap, Map<String, String[]> parameterMap)
		throws AuthException {

		try {
			User user = UserLocalServiceUtil.getUserByEmailAddress(
				companyId, emailAddress);

			int failures = user.getFailedLoginAttempts();

			if (_log.isInfoEnabled()) {
				_log.info(
					"onFailureByEmailAddress: " + emailAddress +
						" has failed to login " + failures + " times");
			}
		}
		catch (PortalException pe) {
			_log.error(pe);
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

			int failures = user.getFailedLoginAttempts();

			if (_log.isInfoEnabled()) {
				_log.info(
					"onFailureByScreenName: " + screenName +
						" has failed to login " + failures + " times");
			}
		}
		catch (PortalException pe) {
			_log.error(pe);
		}
	}

	@Override
	public void onFailureByUserId(
			long companyId, long userId, Map<String, String[]> headerMap,
			Map<String, String[]> parameterMap)
		throws AuthException {

		try {
			User user = UserLocalServiceUtil.getUserById(userId);

			int failures = user.getFailedLoginAttempts();

			if (_log.isInfoEnabled()) {
				_log.info(
					"onFailureByUserId: userId " + userId +
						" has failed to login " + failures + " times");
			}
		}
		catch (PortalException pe) {
			_log.error(pe);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(LogAuthFailure.class);

}