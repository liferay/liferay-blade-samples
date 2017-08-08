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

package com.liferay.blade.samples.portlet.osgiapi;

import java.util.Dictionary;
import java.util.Hashtable;

import javax.portlet.Portlet;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 * @author Liferay
 */
public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		Dictionary<String, Object> properties = new Hashtable<>();

		properties.put(
			"com.liferay.portlet.display-category", "category.sample");
		properties.put("com.liferay.portlet.instanceable", "true");
		properties.put("javax.portlet.display-name", "OSGi API Portlet");
		properties.put(
			"javax.portlet.security-role-ref",
			new String[] {"power-user", "user"});

		_serviceRegistration = bundleContext.registerService(
			Portlet.class, new OSGiAPIPortlet(), properties);
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		_serviceRegistration.unregister();
	}

	private ServiceRegistration<Portlet> _serviceRegistration;

}