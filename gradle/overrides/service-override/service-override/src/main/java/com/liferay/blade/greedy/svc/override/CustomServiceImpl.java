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

package com.liferay.blade.greedy.svc.override;

import com.liferay.blade.greedy.svc.api.SomeService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author James Hinkey
 */
@Component(
	immediate = true, property = {"service.ranking:Integer=100"},
	service = SomeService.class

)
public class CustomServiceImpl implements SomeService {

	@Override
	public String doSomething() {
		StringBuilder sb = new StringBuilder();

		Class<?> clazz = getClass();

		sb.append(clazz.getName());

		sb.append(", which delegates to ");
		sb.append(_defaultService.doSomething());

		return sb.toString();
	}

	@Reference (
		target = "(component.name=com.liferay.blade.greedy.svc.impl.SomeServiceImpl)",
		unbind = "-"
	)
	private SomeService _defaultService;

}