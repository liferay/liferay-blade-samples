package com.liferay.blade.samples.autologin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.security.auto.login.AutoLogin;
import com.liferay.portal.kernel.security.auto.login.BaseAutoLogin;

/**
 * @author Marco Re
 */

@Component(immediate = true, service = AutoLogin.class)
public class BladeAutoLogin extends BaseAutoLogin {

	@Override
	protected String[] doLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String[] credentials = null;
		System.out.println("HERE YOU SHOULD DO SOMETHING TO MAKE AUTO LOGIN ...");
		return credentials;
	}

}
