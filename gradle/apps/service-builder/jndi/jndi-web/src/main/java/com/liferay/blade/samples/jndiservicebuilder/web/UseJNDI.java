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

package com.liferay.blade.samples.jndiservicebuilder.web;

import com.liferay.blade.samples.jndiservicebuilder.model.Region;
import com.liferay.blade.samples.jndiservicebuilder.service.RegionLocalService;

import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author jrao
 */
public class UseJNDI {

	public static List<Region> getRegions() {
		Bundle bundle = FrameworkUtil.getBundle(UseJNDI.class);

		BundleContext bundleContext = bundle.getBundleContext();

		ServiceTracker<RegionLocalService, RegionLocalService> tracker =
			new ServiceTracker<>(bundleContext, RegionLocalService.class, null);

		tracker.open();

		RegionLocalService regionLocalService = tracker.getService();

		try {
			List<Region> regions = regionLocalService.getRegions(
				0, getRegionsCount());

			return regions;
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		tracker.close();

		return null;
	}

	public static int getRegionsCount() {
		Bundle bundle = FrameworkUtil.getBundle(UseJNDI.class);

		BundleContext bundleContext = bundle.getBundleContext();

		ServiceTracker<RegionLocalService, RegionLocalService> tracker =
			new ServiceTracker<>(bundleContext, RegionLocalService.class, null);

		tracker.open();

		RegionLocalService regionLocalService = tracker.getService();

		try {
			int regionsCount = regionLocalService.getRegionsCount();

			return regionsCount;
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		tracker.close();

		return 0;
	}

	public static void useJNDI() {
		Bundle bundle = FrameworkUtil.getBundle(UseJNDI.class);

		BundleContext bundleContext = bundle.getBundleContext();

		ServiceTracker<RegionLocalService, RegionLocalService> tracker =
			new ServiceTracker<>(bundleContext, RegionLocalService.class, null);

		tracker.open();

		RegionLocalService regionLocalService = tracker.getService();

		try {
			regionLocalService.useJNDI();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		tracker.close();
	}

}