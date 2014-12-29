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
package sample.bundle.maven.ip.authpipeline.pre.internal;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.security.auth.AuthException;
import com.liferay.portal.security.auth.Authenticator;

import java.util.Map;

import org.osgi.service.component.annotations.Component;


/**
 * @author Kamesh Sampath
 *
 */
@Component(service=Authenticator.class, property={"key=auth.pipeline.pre"})
public class BladeAuthenticatorPipelinePre implements Authenticator {

	public int authenticateByEmailAddress(
		long companyId, String emailAddress, String password, 
		Map<String, String[]> headerMap, Map<String, String[]> parameterMap) 
						throws AuthException {

		_log.info("authenticateByEmailAddress");

		AuthUtil authUtil =   AuthUtil.getInstance();

		boolean isAuthenticated = authUtil.authenticate(emailAddress, password);

		if(isAuthenticated){
			return 1;
		}else{
			return 0;
		}
	}

	public int authenticateByScreenName(
		long companyId, String screenName, String password, 
		Map<String, String[]> headerMap, Map<String, String[]> parameterMap) 
						throws AuthException{
		_log.info("authenticateByScreenName  - not implemented ");
		return 0;
	}


	public int authenticateByUserId(
		long companyId, long userId, String password, 
		Map<String, String[]> headerMap, Map<String, String[]> parameterMap) 
						throws AuthException{
		_log.info("authenticateByScreenName  - not implemented ");
		return 0;
	}

	private Log _log = LogFactoryUtil.getLog(
		BladeAuthenticatorPipelinePre.class);

}
