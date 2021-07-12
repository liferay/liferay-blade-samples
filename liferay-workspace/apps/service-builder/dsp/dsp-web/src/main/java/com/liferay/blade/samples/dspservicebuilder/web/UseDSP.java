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

package com.liferay.blade.samples.dspservicebuilder.web;

import com.liferay.blade.samples.dspservicebuilder.model.Country;
import com.liferay.blade.samples.dspservicebuilder.service.CountryLocalService;

import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * @author Liferay
 */
public class UseDSP {

	public static List<Country> getCountries() {
		Bundle bundle = FrameworkUtil.getBundle(UseDSP.class);

		ServiceTracker<CountryLocalService, CountryLocalService> tracker =
			new ServiceTracker<>(
				bundle.getBundleContext(), CountryLocalService.class, null);

		tracker.open();

		CountryLocalService countryLocalService = tracker.getService();

		try {
			return countryLocalService.getCountries(0, getCountriesCount());
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}

		tracker.close();

		return null;
	}

	public static int getCountriesCount() {
		Bundle bundle = FrameworkUtil.getBundle(UseDSP.class);

		ServiceTracker<CountryLocalService, CountryLocalService> tracker =
			new ServiceTracker<>(
				bundle.getBundleContext(), CountryLocalService.class, null);

		tracker.open();

		CountryLocalService countryLocalService = tracker.getService();

		try {
			return countryLocalService.getCountriesCount();
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}

		tracker.close();

		return 0;
	}

	public static void useDSP() {
		Bundle bundle = FrameworkUtil.getBundle(UseDSP.class);

		ServiceTracker<CountryLocalService, CountryLocalService> tracker =
			new ServiceTracker<>(
				bundle.getBundleContext(), CountryLocalService.class, null);

		tracker.open();

		CountryLocalService countryLocalService = tracker.getService();

		try {
			countryLocalService.useDSP();
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}

		tracker.close();
	}

}