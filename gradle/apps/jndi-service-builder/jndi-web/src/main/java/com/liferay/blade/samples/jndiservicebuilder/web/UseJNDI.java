package com.liferay.blade.samples.jndiservicebuilder.web;

import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

import com.liferay.blade.samples.jndiservicebuilder.model.Region;
import com.liferay.blade.samples.jndiservicebuilder.service.RegionLocalService;

public class UseJNDI {

	public static void useJNDI() {
		BundleContext bundleContext = FrameworkUtil.getBundle(UseJNDI.class).getBundleContext();
		
		ServiceTracker<RegionLocalService, RegionLocalService> tracker =
				new ServiceTracker<RegionLocalService, RegionLocalService>(bundleContext, RegionLocalService.class, null);
		
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
	
	public static List<Region> getRegions() {
		BundleContext bundleContext = FrameworkUtil.getBundle(UseJNDI.class).getBundleContext();
		
		ServiceTracker<RegionLocalService, RegionLocalService> tracker =
				new ServiceTracker<RegionLocalService, RegionLocalService>(bundleContext, RegionLocalService.class, null);
		
		tracker.open();
		
		RegionLocalService regionLocalService = tracker.getService();
		
		try {
			List<Region> regions = regionLocalService.getRegions(0, getRegionsCount());
			
			return regions;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		tracker.close();
		
		return null;
	}

	public static int getRegionsCount() {
		BundleContext bundleContext = FrameworkUtil.getBundle(UseJNDI.class).getBundleContext();
		
		ServiceTracker<RegionLocalService, RegionLocalService> tracker =
				new ServiceTracker<RegionLocalService, RegionLocalService>(bundleContext, RegionLocalService.class, null);
		
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

}
