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
package blade.servicebuilder.test;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import blade.servicebuilder.service.FooLocalServiceUtil;
import blade.servicebuilder.service.FooServiceUtil;


public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		String localResult = FooLocalServiceUtil.fooLocal();
		System.out.println("Foo LocalService Test: "+localResult);
		String remoteResult = FooServiceUtil.fooRemote();
		System.out.println("Foo RemoteService Test: "+remoteResult);
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		
	}
}