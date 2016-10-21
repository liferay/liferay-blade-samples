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
import com.liferay.blade.samples.servicebuilder.service.FooLocalServiceUtil;
import com.liferay.blade.samples.servicebuilder.service.FooServiceUtil;

import java.util.List;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Liferay
 */
@Component(immediate = true)
public class BladeServiceBuilderTest {

	@Activate
	public void activate(ComponentContext context) throws Exception {
		String localResult = _fooLocalService.fooLocal();

		System.out.println("FooLocalService Test: " + localResult);

		String remoteResult = FooServiceUtil.fooRemote();

		System.out.println("FooRemoteService Test: " + remoteResult);

		int count = _fooLocalService.getFoosCount();

		List<Foo> fooList = FooLocalServiceUtil.getFoos(0, count);

		for (Foo foo : fooList) {
			System.out.println(foo.getFooId() + " " + foo.getField1());
		}
	}

	@Reference
	private FooLocalService _fooLocalService;

}