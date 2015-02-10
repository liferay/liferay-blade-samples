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
package com.liferay.blade.service.hook.sample;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceWrapper;
import com.liferay.portal.service.UserLocalServiceWrapper;

/**
 * @author Gregory Amerson
 */
@Component(
		service = ServiceWrapper.class
)
public class UserLocalServiceHook extends UserLocalServiceWrapper {

	public UserLocalServiceHook() {
		super(null);
	}

	@Override
	public User getUser(long userId) throws PortalException {
		System.out.println("Getting user by id " + userId);
		return super.getUser(userId);
	}

}