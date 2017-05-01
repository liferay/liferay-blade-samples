/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.blade.samples.corejsphook;

import com.liferay.portal.deploy.hot.CustomJspBag;
import com.liferay.portal.kernel.url.URLContainer;

import java.net.URL;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * Represents a custom JSP bag used to override Liferay's core JSPs. This class
 * sets the following properties:
 *
 * <ul>
 * <li>
 * <code>context.id</code>: the custom JSP bag class name
 * </li>
 * <li>
 * <code>context.name</code>: the human readable name for you service
 * </li>
 * <li>
 * <code>service.ranking</code>: the priority of your implementation. The
 * importance of your change dictates its service ranking. For example, if
 * another custom JSP bag implementation overriding the same JSP is deployed
 * with a service rank of <code>101</code>, it takes precedence over this one
 * because its service ranking is higher.
 * </li>
 * </ul>
 *
 * @author Liferay
 */
@Component(
	immediate = true,
	property = {
		"context.id=BladeCustomJspBag", "context.name=Test Custom JSP Bag",
		"service.ranking:Integer=100"
	}
)
public class BladeCustomJspBag implements CustomJspBag {

	/**
	 * Returns the directory path in the module JAR where the custom JSPs
	 * reside.
	 *
	 * @return the directory path in the module JAR where the custom JSPs reside
	 */
	@Override
	public String getCustomJspDir() {
		return "META-INF/jsps/";
	}

	/**
	 * Returns the custom JSP URL paths.
	 *
	 * @return the custom JSP URL paths
	 */
	@Override
	public List<String> getCustomJsps() {
		return _customJsps;
	}

	@Override
	public URLContainer getURLContainer() {
		return _urlContainer;
	}

	@Override
	public boolean isCustomJspGlobal() {
		return true;
	}

	/**
	 * Adds the URL paths for all custom core JSPs to a list when the module is
	 * activated.
	 *
	 * @param bundleContext the bundle context from which to get the custom
	 *        JSP's bundle
	 */
	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundle = bundleContext.getBundle();

		_customJsps = new ArrayList<>();

		Enumeration<URL> entries = _bundle.findEntries(
			getCustomJspDir(), "*.jsp", true);

		while (entries.hasMoreElements()) {
			URL url = entries.nextElement();

			_customJsps.add(url.getPath());
		}
	}

	private Bundle _bundle;
	private List<String> _customJsps;

	private final URLContainer _urlContainer = new URLContainer() {

		@Override
		public URL getResource(String name) {
			return _bundle.getEntry(name);
		}

		@Override
		public Set<String> getResources(String path) {
			Set<String> paths = new HashSet<>();

			for (String entry : _customJsps) {
				if (entry.startsWith(path)) {
					paths.add(entry);
				}
			}

			return paths;
		}

	};

}