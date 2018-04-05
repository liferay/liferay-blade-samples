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

package com.liferay.blade.samples.servicebuilder.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for Foo. This utility wraps
 * {@link com.liferay.blade.samples.servicebuilder.service.impl.FooServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see FooService
 * @see com.liferay.blade.samples.servicebuilder.service.base.FooServiceBaseImpl
 * @see com.liferay.blade.samples.servicebuilder.service.impl.FooServiceImpl
 * @generated
 */
@ProviderType
public class FooServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.blade.samples.servicebuilder.service.impl.FooServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* NOTE FOR DEVELOPERS:
	*
	* Never reference this class directly. Always use {@link FooServiceUtil} to access the foo remote service.
	*/
	public static java.lang.String fooRemote() {
		return getService().fooRemote();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static FooService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<FooService, FooService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(FooService.class);

		ServiceTracker<FooService, FooService> serviceTracker = new ServiceTracker<FooService, FooService>(bundle.getBundleContext(),
				FooService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}