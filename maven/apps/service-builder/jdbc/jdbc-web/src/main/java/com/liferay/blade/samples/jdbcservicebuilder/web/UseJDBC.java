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

package com.liferay.blade.samples.jdbcservicebuilder.web;

import com.liferay.blade.samples.jdbcservicebuilder.model.Country;
import com.liferay.blade.samples.jdbcservicebuilder.service.CountryLocalService;

import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Liferay
 */
public class UseJDBC {

	public static List<Country> getCountries() {
		Bundle bundle = FrameworkUtil.getBundle(UseJDBC.class);

		BundleContext bundleContext = bundle.getBundleContext();

		ServiceTracker<CountryLocalService, CountryLocalService> tracker =
			new ServiceTracker<>(
				bundleContext, CountryLocalService.class, null);

		tracker.open();

		CountryLocalService countryLocalService = tracker.getService();

		try {
			List<Country> countries = countryLocalService.getCountries(
				0, getCountriesCount());

			return countries;
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		tracker.close();

		return null;
	}

	public static int getCountriesCount() {
		Bundle bundle = FrameworkUtil.getBundle(UseJDBC.class);

		BundleContext bundleContext = bundle.getBundleContext();

		ServiceTracker<CountryLocalService, CountryLocalService> tracker =
			new ServiceTracker<>(
				bundleContext, CountryLocalService.class, null);

		tracker.open();

		CountryLocalService countryLocalService = tracker.getService();

		try {
			int regionsCount = countryLocalService.getCountriesCount();

			return regionsCount;
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		tracker.close();

		return 0;
	}

	public static void useJDBC() {
		Bundle bundle = FrameworkUtil.getBundle(UseJDBC.class);

		BundleContext bundleContext = bundle.getBundleContext();

		ServiceTracker<CountryLocalService, CountryLocalService> tracker =
			new ServiceTracker<>(
				bundleContext, CountryLocalService.class, null);

		tracker.open();

		CountryLocalService countryLocalService = tracker.getService();

		try {
			countryLocalService.useJDBC();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		tracker.close();
	}

}