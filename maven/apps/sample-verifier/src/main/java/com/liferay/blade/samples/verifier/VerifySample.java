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

package com.liferay.blade.samples.verifier;

import java.io.IOException;

import java.net.URL;

import java.util.Optional;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.stream.Stream;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
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
		"osgi.command.function=verifySample", "osgi.command.scope=blade"
	},
	service = Object.class
)
public class VerifySample {

	public VerifySample() {
		_messages = new StringBuilder();
	}

	public String verifySample(String bsn) {
		Stream.of(
			_bundleContext.getBundles()
		).filter(
			bundle -> bundle.getSymbolicName().equals(bsn)
		).findFirst(
		).ifPresent(
			this::_verify
		);

		return _messages.toString();
	}

	private String _bundleStateToString(int state) {
		return Stream.of(
			Bundle.class.getFields()
		).filter(
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
			_verifyBundleComponents(bundle);
		}
		catch (IOException ioe) {
			_messages.append("ERROR: " + ioe.getMessage());
		}
	}

	private void _verifyBundleComponents(Bundle bundle) {
		Stream.of(
			bundle.getRegisteredServices()
		).filter(
			sr -> sr.getProperty("objectClass") instanceof String[]
		).forEach(
			sr -> {
				String type = ((String[])sr.getProperty("objectClass"))[0];

				Bundle[] usingBundles = Optional.ofNullable(
					sr.getUsingBundles()
				).orElseGet(
					() -> new Bundle[0]
				);

				if (usingBundles.length > 0) {
					_messages.append(
						"\t" + _CHECK + "\tService{" + type + "} used by " +
							usingBundles[0].getSymbolicName() + "\n");
				}
				else {
					_messages.append(
						"\t" + _WARNING + "\tNo using bundles for following " +
							"service reference\n");

					Stream.of(sr.getPropertyKeys()).forEach(
						key -> {
							_messages.append("\t\t\t" + key + "=");
							_messages.append(sr.getProperty(key) + "\n");
						});
				}
			}
		);
	}

	private void _verifyBundleState(Bundle bundle) throws IOException {
		String state = _bundleStateToString(bundle.getState());

		switch (bundle.getState()) {
			case Bundle.ACTIVE:
				_messages.append("\t" + _CHECK + "\tBundle is " + state + "\n");

				break;
			case Bundle.RESOLVED:
				URL manifestURL = bundle.getEntry("META-INF/MANIFEST.MF");

				Manifest manifest = new Manifest(manifestURL.openStream());

				Attributes attributes = manifest.getMainAttributes();

				String fragmentHost = attributes.getValue("Fragment-Host");

				if (fragmentHost != null) {
					_messages.append(
						"\t" + _CHECK + "\tBundle is a fragment with host " +
							fragmentHost + " and is " + state + "\n");
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

	private static final String _ANSI_CLEAR = "\u001B[0m]";

	private static final String _ANSI_GREEN = "[\u001B[32m";

	private static final String _ANSI_YELLOW = "[\u001B[33m";

	private static final String _CHECK = _ANSI_GREEN + " \u2713 " + _ANSI_CLEAR;

	private static final String _WARNING =
		_ANSI_YELLOW + " \u26A0 " + _ANSI_CLEAR;

	private static final BundleContext _bundleContext = FrameworkUtil.getBundle(
		VerifySample.class).getBundleContext();

	private StringBuilder _messages;

}