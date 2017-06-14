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

package com.liferay.blade.samples.strutsaction;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.struts.StrutsAction;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Liferay
 */
@Component(
	immediate = true, property = {"path=/portal/blade"},
	service = StrutsAction.class
)
public class BladeStrutsAction extends BaseStrutsAction {

	public String execute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug("Processing path /c/portal/blade");
		}

		RequestDispatcher requestDispatcher =
			_servletContext.getRequestDispatcher("/html/portal/blade.jsp");

		requestDispatcher.forward(request, response);

		return null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BladeStrutsAction.class);

	@Reference(target = "(osgi.web.symbolicname=blade.strutsaction)")
	private volatile ServletContext _servletContext;

}