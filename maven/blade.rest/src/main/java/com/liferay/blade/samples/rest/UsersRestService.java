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

package com.liferay.blade.samples.rest;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;

import java.util.Collections;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Liferay
 */
@ApplicationPath("/blade.users")
@Component(
	immediate = true, property = {"jaxrs.application=true"},
	service = Application.class
)
public class UsersRestService extends Application {

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
			result.append(user.getFullName()).append(",\n");
		}

		return result.toString();
	}

	@Reference
	private volatile UserLocalService _userLocalService;

}