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
	immediate = true, property = "path=/portal/blade",
	service = StrutsAction.class
)
public class BladeStrutsAction extends BaseStrutsAction {

	public String execute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Processing path /c/portal/blade");
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