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

			credentials[0] = Long.toString(autoLoginUser.getUserId());
			credentials[1] = autoLoginUser.getPassword();
			credentials[2] = Boolean.toString(true);
		}

		return credentials;
	}

	@Reference
	private UserLocalService _userLocalService;

}