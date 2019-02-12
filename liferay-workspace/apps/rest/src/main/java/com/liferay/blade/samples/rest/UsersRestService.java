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

package com.liferay.blade.samples.rest;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;

import java.util.Collections;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Liferay
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/users",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=Users"
	},
	service = Application.class
)
public class UsersRestService extends Application {

	@Activate
	public void activate() {
		if (_log.isInfoEnabled()) {
			_log.info("User Rest service activated");
		}
	}

	@Override
	public Set<Object> getSingletons() {
		return Collections.singleton((Object)this);
	}

	@GET
	@Path("/list")
	@Produces("text/plain")
	public String getUsers() {
		StringBuilder result = new StringBuilder();

		for (User user : _userLocalService.getUsers(-1, -1)) {
			result.append(user.isDefaultUser() ? "Guest" : user.getFullName());
			result.append("\n");
		}

		return result.toString();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UsersRestService.class);

	@Reference
	private volatile UserLocalService _userLocalService;

}