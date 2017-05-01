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

package com.liferay.blade.samples.authenticator.shiro;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.Authenticator;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Liferay
 */
@Component(
	immediate = true, property = {"key=auth.pipeline.pre"},
	service = Authenticator.class
)
public class ShiroAuthenticatorPre implements Authenticator {

	@Activate
	public void activate() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(
			"classpath:userauth.ini");

		SecurityUtils.setSecurityManager(factory.getInstance());

		if (_log.isInfoEnabled()) {
			_log.info("activate");
		}
	}

	@Override
	public int authenticateByEmailAddress(
			long companyId, String emailAddress, String password,
			Map<String, String[]> headerMap, Map<String, String[]> parameterMap)
		throws AuthException {

		if (_log.isInfoEnabled()) {
			_log.info("authenticateByEmailAddress");
		}

		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
			emailAddress, password);

		Subject currentUser = SecurityUtils.getSubject();

		try {
			currentUser.login(usernamePasswordToken);

			boolean authenticated = currentUser.isAuthenticated();

			if (authenticated) {
				if (_log.isInfoEnabled()) {
					_log.info("authenticated");
				}

				return SKIP_LIFERAY_CHECK;
			}
			else {
				return FAILURE;
			}
		}
		catch (AuthenticationException ae) {
			_log.error(ae.getMessage(), ae);
			throw new AuthException(ae.getMessage(), ae);
		}
	}

	@Override
	public int authenticateByScreenName(
			long companyId, String screenName, String password,
			Map<String, String[]> headerMap, Map<String, String[]> parameterMap)
		throws AuthException {

		if (_log.isInfoEnabled()) {
			_log.info("authenticateByScreenName  - not implemented ");
		}

		return SUCCESS;
	}

	@Override
	public int authenticateByUserId(
			long companyId, long userId, String password,
			Map<String, String[]> headerMap, Map<String, String[]> parameterMap)
		throws AuthException {

		if (_log.isInfoEnabled()) {
			_log.info("authenticateByScreenName  - not implemented ");
		}

		return SUCCESS;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ShiroAuthenticatorPre.class);

}