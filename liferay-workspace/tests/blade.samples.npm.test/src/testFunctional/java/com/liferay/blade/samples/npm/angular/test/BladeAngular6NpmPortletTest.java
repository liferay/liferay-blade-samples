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

package com.liferay.blade.samples.npm.angular.test;

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
public class BladeAngular6NpmPortletTest {

	@Deployment
	public static JavaArchive create() throws Exception {
		final File jarFile = new File(
			System.getProperty("angular6NpmPortletJarFile"));

		return ShrinkWrap.createFromZipFile(JavaArchive.class, jarFile);
	}

	@Test
	public void testBladeAngular6Npm() throws InterruptedException {
		_webDriver.get(_portletURL.toExternalForm());

		Assert.assertTrue(
			"Portlet was not deployed",
			BladeSampleFunctionalActionUtil.isVisible(
				_webDriver, _bladeNpmAngularPortlet));

		BladeSampleFunctionalActionUtil.mouseOverClick(
			_webDriver, _bladeNpmAngularPortlet);

		Thread.sleep(1000);

		Assert.assertTrue(
			"Expected: ANGULAR 6 PORTLET, but saw: " +
				_portletTitle.getText(),
			_portletTitle.getText().toLowerCase().contentEquals("angular 6 portlet"));

		Assert.assertTrue(
			"Expected: Hello world from Angular 6!, but saw: " +
				_portletBodyBody.getText(),
			_portletBodyBody.getText().contains("Hello world from Angular 6!"));
	}

	@FindBy(xpath = "//section[contains(@id,'Angular6Portlet')]")
	private WebElement _bladeNpmAngularPortlet;

	@FindBy(xpath = "//section[contains(@id,'Angular6Portlet')]//..//div[@class='portlet-body']/div")
	private WebElement _portletBodyBody;

	@FindBy(xpath = "//section[contains(@id,'Angular6Portlet')]//..//div/h2")
	private WebElement _portletTitle;

	@PortalURL("com_liferay_blade_npm_angular6_portlet_Angular6Portlet")
	private URL _portletURL;

	@Drone
	private WebDriver _webDriver;

}