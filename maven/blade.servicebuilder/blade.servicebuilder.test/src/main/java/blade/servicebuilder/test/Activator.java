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

import java.util.Date;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import blade.servicebuilder.model.Foo;
import blade.servicebuilder.service.FooLocalServiceUtil;
import blade.servicebuilder.service.FooServiceUtil;


public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		String localResult = FooLocalServiceUtil.fooLocal();
		System.out.println("FooLocalService Test: "+localResult);
		String remoteResult = FooServiceUtil.fooRemote();
		System.out.println("FooRemoteService Test: "+remoteResult);
		
		int count = FooLocalServiceUtil.getFoosCount();
		List<Foo> fooList = FooLocalServiceUtil.getFoos(0, count);
		for(Foo foo : fooList){
			System.out.println(foo.getFooId()+" "+foo.getField1());
		}
		//FooLocalServiceUtil.deleteFoo(111);
	/*	if( fooList == null || fooList.size()<=0 ){
			for(int i = 0 ; i < 9 ; i++){
				Foo foo = FooLocalServiceUtil.createFoo();
				foo.setField1("field1 String"+1);
				foo.setField2(true);
				foo.setField3(i);
				foo.setField4(new Date());
				foo.setField5("field5 String"+i);
				FooLocalServiceUtil.addFoo(foo);
			}
		}	*/		
		
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		
	}
}