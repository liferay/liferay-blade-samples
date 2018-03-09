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
public class BladeAngularNpmPortletTest {

	@Deployment
	public static JavaArchive create() throws Exception {
		final File jarFile = new File(
			System.getProperty("angularNpmPortletJarFile"));

		return ShrinkWrap.createFromZipFile(JavaArchive.class, jarFile);
	}

	@Ignore //only seems to work in firefox, not in phantomjs
	@Test
	public void testBladeAngularNpm() throws InterruptedException {
		_webDriver.get(_portletURL.toExternalForm());

		_webDriver.navigate().refresh();

		Assert.assertTrue(
			"Portlet was not deployed",
			BladeSampleFunctionalActionUtil.isVisible(
				_webDriver, _bladeNpmAngularPortlet));

		BladeSampleFunctionalActionUtil.mouseOverClick(
			_webDriver, _bladeNpmAngularPortlet);

		_webDriver.navigate().refresh();

		_nameField.clear();

		_nameField.sendKeys("Hello from WebDriver!");

		Thread.sleep(500);

		Assert.assertTrue(
			"Expected: Tour of Heroes, but saw: " +
				_portletBodyHeader1.getText(),
			_portletBodyHeader1.getText().contentEquals("Tour of Heroes"));

		Assert.assertTrue(
			"Expected: Hello from WebDriver!, but saw: " +
				_portletBodyHeader2.getText(),
			_portletBodyHeader2.getText().contains("Hello from WebDriver!"));
	}

	@FindBy(xpath = "//section[contains(@id,'AngularPortlet')]")
	private WebElement _bladeNpmAngularPortlet;

	@FindBy(xpath = "//section[contains(@id,'AngularPortlet')]//../input")
	private WebElement _nameField;

	@FindBy(xpath = "//section[contains(@id,'AngularPortlet')]//..//div[@class='portlet-body']/div/h1")
	private WebElement _portletBodyHeader1;

	@FindBy(xpath = "//section[contains(@id,'AngularPortlet')]//..//div[@class='portlet-body']/div/h2")
	private WebElement _portletBodyHeader2;

	@PortalURL("com_liferay_blade_npm_angular_portlet_AngularPortlet")
	private URL _portletURL;

	@Drone
	private WebDriver _webDriver;

}