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

package com.liferay.blade.samples.servicebuilder.adq.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for Bar. This utility wraps
 * {@link com.liferay.blade.samples.servicebuilder.adq.service.impl.BarServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see BarService
 * @see com.liferay.blade.samples.servicebuilder.adq.service.base.BarServiceBaseImpl
 * @see com.liferay.blade.samples.servicebuilder.adq.service.impl.BarServiceImpl
 * @generated
 */
@ProviderType
public class BarServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.blade.samples.servicebuilder.adq.service.impl.BarServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static BarService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<BarService, BarService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(BarService.class);

		ServiceTracker<BarService, BarService> serviceTracker = new ServiceTracker<BarService, BarService>(bundle.getBundleContext(),
				BarService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}