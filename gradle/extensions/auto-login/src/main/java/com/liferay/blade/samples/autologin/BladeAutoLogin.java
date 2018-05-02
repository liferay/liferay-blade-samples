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

package com.liferay.blade.samples.autologin;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auto.login.AutoLogin;
import com.liferay.portal.kernel.security.auto.login.BaseAutoLogin;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.log.LogService;

/**
 * @author Marco Re
 */
@Component(immediate = true, service = AutoLogin.class)
public class BladeAutoLogin extends BaseAutoLogin {

	@Override
	protected String[] doLogin(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		String[] credentials = null;

		long companyId = PortalUtil.getCompanyId(request);

		User autoLoginUser = null;

		try {
			autoLoginUser = _userLocalService.getUserByEmailAddress(
				companyId, "auto.login@liferay.com");
		}
		catch (Exception e) {
		}

		if (autoLoginUser != null) {
			credentials = new String[3];

			credentials[0] = String.valueOf(autoLoginUser.getUserId());
			credentials[1] = autoLoginUser.getPassword();
			credentials[2] = Boolean.toString(true);
		}

		_log.log(
			LogService.LOG_INFO,
			"Logged in as" + autoLoginUser.getFullName() +
				"by Blade Auto Login");

		return credentials;
	}

	@Reference
	private LogService _log;

	@Reference
	private UserLocalService _userLocalService;

}