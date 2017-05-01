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

package com.liferay.blade.samples.servicebuilder.web.test;

import com.liferay.blade.samples.servicebuilder.model.Foo;
import com.liferay.blade.samples.servicebuilder.service.FooLocalService;

import java.util.Date;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Liferay
 */
@Component(immediate = true)
public class AddTestData {

	@Activate
	public void addTestData() {
		int entries = 10;

		while (entries > 0) {
			Foo foo = _fooLocalService.createFoo(0);

			foo.setField1("new field1 entry" + entries);
			foo.setField2(true);
			foo.setField3(10);
			foo.setField4(new Date());
			foo.setField5("new field5 entry" + entries);
			foo.isNew();

			_fooLocalService.addFooWithoutId(foo);

			entries--;
		}
	}

	@Reference
	private FooLocalService _fooLocalService;

}