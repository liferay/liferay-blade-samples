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

import com.liferay.blade.basic.model.Foo;
import com.liferay.blade.basic.service.FooLocalService;
import com.liferay.blade.basic.service.FooLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Date;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Lawrence Lee
 */
@Component(immediate = true)
public class AddTestData {

	@Activate
	public void addTestData() {
		Foo foo = FooLocalServiceUtil.createFoo(0);

		foo.setField1("createFooEntryField1");
		foo.setField2(true);
		foo.setField3(1);
		foo.setField4(new Date());
		foo.setField5("createFooEntryField5");
		foo.isNew();

		Foo fooEntry = FooLocalServiceUtil.addFoo(foo);

		if (!fooEntry.getField2()) {
			_log.error("Test Failed");
		}

		FooLocalServiceUtil.deleteFoo(fooEntry);
	}

	private static final Log _log = LogFactoryUtil.getLog(AddTestData.class);

	@Reference
	private FooLocalService _fooLocalService;

}