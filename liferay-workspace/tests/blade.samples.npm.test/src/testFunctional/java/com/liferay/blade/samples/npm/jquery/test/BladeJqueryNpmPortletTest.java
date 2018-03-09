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

package com.liferay.blade.samples.npm.jquery.test;

import com.liferay.arquillian.portal.annotation.PortalURL;
import com.liferay.blade.sample.test.functional.utils.BladeSampleFunctionalActionUtil;

import java.io.File;

import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Lawrence Lee
 */
@RunAsClient
@RunWith(Arquillian.class)
public class BladeJqueryNpmPortletTest {

	@Deployment
	public static JavaArchive create() throws Exception {
		final File jarFile = new File(
			System.getProperty("jqueryNpmPortletJarFile"));

		return ShrinkWrap.createFromZipFile(JavaArchive.class, jarFile);
	}

	@Ignore
	@Test
	public void testBladeJQueryNpm() throws InterruptedException {
		_webDriver.get(_portletURL.toExternalForm());

		Assert.assertTrue(
			"Portlet was not deployed",
			BladeSampleFunctionalActionUtil.isVisible(
				_webDriver, _bladeNpmJQueryPortlet));

		BladeSampleFunctionalActionUtil.mouseOverClick(
			_webDriver, _bladeNpmJQueryPortlet);

		Thread.sleep(1000);

		Assert.assertTrue(
			"Expected: Liferay NPM jQuery Example, but saw: " +
				_portletTitle.getText(),
			_portletTitle.getText().contentEquals("Liferay NPM jQuery Example"));

		Assert.assertTrue(
			"Expected: Hello from jQuery!..., but saw: " +
				_portletBodyBody.getText(),
			_portletBodyBody.getText().contains("Hello from jQuery!"));
	}

	@FindBy(xpath = "//section[contains(@id,'JQueryPortlet')]")
	private WebElement _bladeNpmJQueryPortlet;

	@FindBy(xpath = "//section[contains(@id,'JQueryPortlet')]/div/div/div")
	private WebElement _portletBodyBody;

	@FindBy(xpath = "//section[contains(@id,'JQueryPortlet')]/header/div/span")
	private WebElement _portletTitle;

	@PortalURL("com_liferay_blade_npm_jquery_portlet_JQueryPortlet")
	private URL _portletURL;

	@Drone
	private WebDriver _webDriver;

}