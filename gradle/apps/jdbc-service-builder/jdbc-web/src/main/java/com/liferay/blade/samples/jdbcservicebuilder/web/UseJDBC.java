package com.liferay.blade.samples.jdbcservicebuilder.web;

import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

import com.liferay.blade.samples.jdbcservicebuilder.model.Country;
import com.liferay.blade.samples.jdbcservicebuilder.service.CountryLocalService;

public class UseJDBC {

	public static void useJDBC() {
		BundleContext bundleContext = FrameworkUtil.getBundle(UseJDBC.class).getBundleContext();
		
		ServiceTracker<CountryLocalService, CountryLocalService> tracker =
				new ServiceTracker<CountryLocalService, CountryLocalService>(bundleContext, CountryLocalService.class, null);
		
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
	
	public static List<Country> getCountries() {
		BundleContext bundleContext = FrameworkUtil.getBundle(UseJDBC.class).getBundleContext();
		
		ServiceTracker<CountryLocalService, CountryLocalService> tracker =
				new ServiceTracker<CountryLocalService, CountryLocalService>(bundleContext, CountryLocalService.class, null);
		
		tracker.open();
		
		CountryLocalService countryLocalService = tracker.getService();
		
		try {
			List<Country> countries = countryLocalService.getCountries(0, getCountriesCount());
			
			return countries;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		tracker.close();
		
		return null;
	}

	public static int getCountriesCount() {
		BundleContext bundleContext = FrameworkUtil.getBundle(UseJDBC.class).getBundleContext();
		
		ServiceTracker<CountryLocalService, CountryLocalService> tracker =
				new ServiceTracker<CountryLocalService, CountryLocalService>(bundleContext, CountryLocalService.class, null);
		
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

}