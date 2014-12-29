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
package sample.bundle.bndtools.ip.authpipeline.pre.internal;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.security.auth.AuthException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;


/**
 * @author Kamesh Sampath
 *
 */
public class AuthUtil {

	private AuthUtil(){
		
		_log.debug("Loading userauth.ini");
		
		Factory<SecurityManager> factory  = new IniSecurityManagerFactory(
						"classpath:userauth.ini");
		
		_securityManager = factory.getInstance();
		
		SecurityUtils.setSecurityManager(_securityManager);
	}
	
	public boolean authenticate(String username, String password) 
					throws AuthException{
		
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken
						(username, password);

		Subject currentUser = SecurityUtils.getSubject();
		
		try {
			
			currentUser.login(usernamePasswordToken);
			return currentUser.isAuthenticated();
		}
		catch (AuthenticationException e) {
			_log.error(e.getMessage(), e);
			throw new AuthException(e.getMessage(),e);
		}
	}
	
	public static AuthUtil getInstance() {
		
		if(_instance ==null){
			_instance = new AuthUtil();
		}
		
		return _instance;
	}
	
	private Log _log = LogFactoryUtil.getLog(AuthUtil.class);
	
	private static AuthUtil _instance;
	private SecurityManager _securityManager;
}
