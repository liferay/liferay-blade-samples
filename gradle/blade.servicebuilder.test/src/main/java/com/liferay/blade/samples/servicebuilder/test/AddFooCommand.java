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

package com.liferay.blade.samples.servicebuilder.test;

import com.liferay.blade.samples.servicebuilder.model.Foo;
import com.liferay.blade.samples.servicebuilder.service.FooLocalService;

import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Liferay
 */
@Component(
	property = {"osgi.command.function=addfoo", "osgi.command.scope=blade"},
	service = Object.class
)
public class AddFooCommand {

	
	public int addfoo() {
		int retval = 0;
		
		Foo foo = _fooLocalService.createFoo(0);
		
		foo.setField1("field1");
		foo.setField2(true);
		foo.setField3(10);
		foo.setField4(new Date());
		foo.setField5("field5");
		foo.isNew();

		_fooLocalService.addFooWithoutId(foo);
		
		return retval;
	}

	@Reference
	private  FooLocalService _fooLocalService;

}