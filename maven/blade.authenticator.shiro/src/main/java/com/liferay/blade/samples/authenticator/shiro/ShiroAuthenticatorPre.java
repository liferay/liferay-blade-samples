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