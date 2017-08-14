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

package com.liferay.blade.samples.verify;

import com.liferay.application.list.PanelApp;

import java.io.IOException;

import java.lang.reflect.Field;

import java.net.URL;

import java.util.Optional;
import java.util.jar.Manifest;
import java.util.stream.Stream;

import javax.portlet.Portlet;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Component;

/**
 * @author Liferay
 *
 * This class is not a required part of this sample and can be safely ignored.
 *
 * It is only used to add a gogo shell command to allow developers to verify
 * the sample is installed into the framework and working as expected.
 */
@Component(
	property = {
		"osgi.command.function=verifyControlPanelPortlet",
		"osgi.command.scope=blade"
	}
)
public class SelfVerify {

	public SelfVerify() {
		_messages = new StringBuilder();
	}

	public String verifyControlPanelPortlet() {
		Stream<Bundle> bundles = Stream.of(_bundleContext.getBundles());

		bundles.filter(
			bundle -> bundle.getSymbolicName().equals("control-panel-portlet")
		).findFirst(
		).ifPresent(
			this::_verify
		);

		return _messages.toString();
	}

	private String _bundleStateToString(int state) {
		Stream<Field> fields = Stream.of(Bundle.class.getFields());

		return fields.filter(
			field -> field.getType().equals(int.class)
		).filter(
			field -> {
				try {
					return field.get(null).equals(Integer.valueOf(state));
				}
				catch (Exception e) {
					return false;
				}
			}
		).map(
			field -> field.getName()
		).findFirst(
		).orElse(
			null
		);
	}

	private void _verify(Bundle bundle) {
		_messages.append("Verifying " + bundle.getSymbolicName() + "\n");

		try {
			_verifyBundleState(bundle);
			_verifyBundleComponents(bundle, Portlet.class.getName());
			_verifyBundleComponents(bundle, PanelApp.class.getName());
		}
		catch (IOException ioe) {
			_messages.append("ERROR: " + ioe.getMessage());
		}
	}

	private void _verifyBundleComponents(Bundle bundle, String type) {
		Stream<ServiceReference<?>> serviceReferences = Stream.of(
			bundle.getRegisteredServices());

		Optional<ServiceReference<?>> serviceReference =
			serviceReferences.filter(
				sr -> sr.getProperty("objectClass") instanceof String[]
			).filter(
				sr -> ((String[])sr.getProperty("objectClass"))[0].equals(type)
			).findAny();

		if (serviceReference.isPresent()) {
			ServiceReference<?> sr = serviceReference.get();

			Bundle[] usingBundles = Optional.ofNullable(
				sr.getUsingBundles()
			).orElseGet(
				() -> new Bundle[0]
			);

			if (usingBundles.length > 0) {
				_messages.append(
					"\t[\u001B[32m✓\u001B[0m]\tService{" + type + "} used by " +
						usingBundles[0].getSymbolicName() + "\n");
			}
			else {
				_messages.append("ERROR: no using bundles for {" + type + "\n");
			}
		}
		else {
			_messages.append("ERROR: no services with type " + type + "\n");
		}
	}

	private void _verifyBundleState(Bundle bundle) throws IOException {
		String state = _bundleStateToString(bundle.getState());

		switch (bundle.getState()) {
			case Bundle.ACTIVE:
				_messages.append(
					"\t[\u001B[32m✓\u001B[0m]\tBundle is " + state + "\n");
				break;
			case Bundle.RESOLVED:
				URL manifestURL = bundle.getEntry("META-INF/MANIFEST.MF");

				Manifest manifest = new Manifest(manifestURL.openStream());

				String fragmentHost =
					manifest.getMainAttributes().getValue("Fragment-Host");

				if (fragmentHost != null) {
					_messages.append(
						"\t[\u001B[32m✓\u001B[0m]\tBundle is a fragment with " +
							"host " + fragmentHost + " and is " + state + "\n");
				}
				else {
					_messages.append(
						"\tERROR: Bundle is not resolved. Check reason why " +
							"with g! diag " + bundle.getSymbolicName());
				}

				break;
			default:
				_messages.append(
					"\tERROR: unexpected bundle state = " + state + " \n");
		}
	}

	private static final BundleContext _bundleContext = FrameworkUtil.getBundle(
		SelfVerify.class).getBundleContext();

	private StringBuilder _messages;

}