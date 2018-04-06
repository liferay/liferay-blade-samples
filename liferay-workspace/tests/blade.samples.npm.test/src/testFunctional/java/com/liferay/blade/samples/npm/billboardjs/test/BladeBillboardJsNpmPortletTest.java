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

package com.liferay.blade.samples.npm.billboardjs.test;

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
import org.junit.Assume;
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
public class BladeBillboardJsNpmPortletTest {

	@Deployment
	public static JavaArchive create() throws Exception {
		final File jarFile = new File(
			System.getProperty("billboardjsNpmPortletJarFile"));

		return ShrinkWrap.createFromZipFile(JavaArchive.class, jarFile);
	}

	@Test
	public void testBladeBillboardJsNpm() throws InterruptedException {
		Assume.assumeTrue(
			BladeSampleFunctionalActionUtil.getPortalVersion().equals("7.0") &&
			!System.getProperty("portalVersion").contains("master"));

		_webDriver.get(_portletURL.toExternalForm());

		Assert.assertTrue(
			"Portlet was not deployed",
			BladeSampleFunctionalActionUtil.isVisible(
				_webDriver, _bladeNpmBillboardPortlet));

		BladeSampleFunctionalActionUtil.mouseOverClick(
			_webDriver, _bladeNpmBillboardPortlet);

		Assert.assertTrue(
			"Expected: Billboard.js Portlet, but saw: " +
				_portletTitle.getText(),
			_portletTitle.getText().contentEquals("Billboard.js Portlet"));

		Assert.assertTrue(
			"Expected: An example from billboard.js, but saw: " +
				_portletBodyHeader1.getText(),
			_portletBodyHeader1.getText().contentEquals(
				"An example from billboard.js"));

		Assert.assertTrue(
			"Expected: Default charts, but saw: " +
				_portletBodyHeader2.getText(),
			_portletBodyHeader2.getText().contentEquals("Default charts"));
	}

	@Test
	public void testBladeBillboardJsNpmMaster() throws InterruptedException {
		Assume.assumeTrue(
			BladeSampleFunctionalActionUtil.getPortalVersion().equals("master"));

		_webDriver.get(_portletURL.toExternalForm());

		Assert.assertTrue(
			"Portlet was not deployed",
			BladeSampleFunctionalActionUtil.isVisible(
				_webDriver, _bladeNpmBillboardPortlet));

		BladeSampleFunctionalActionUtil.mouseOverClick(
			_webDriver, _bladeNpmBillboardPortlet);

		Assert.assertTrue(
			"Expected: Billboard.js Portlet, but saw: " +
				_portletTitleMaster.getText(),
			_portletTitleMaster.getText().contentEquals(
				"Billboard.js Portlet"));

		Assert.assertTrue(
			"Expected: An example from billboard.js, but saw: " +
				_portletBodyHeader1.getText(),
			_portletBodyHeader1.getText().contentEquals(
				"An example from billboard.js"));

		Assert.assertTrue(
			"Expected: Default charts, but saw: " +
				_portletBodyHeader2.getText(),
			_portletBodyHeader2.getText().contentEquals("Default charts"));
	}

	@FindBy(xpath = "//section[contains(@id,'BillboardjsPortlet')]")
	private WebElement _bladeNpmBillboardPortlet;

	@FindBy(xpath = "//section[contains(@id,'BillboardjsPortlet')]//..//div[@class='portlet-body']/div/h1")
	private WebElement _portletBodyHeader1;

	@FindBy(xpath = "//section[contains(@id,'BillboardjsPortlet')]//..//div[@class='portlet-body']/div/h2")
	private WebElement _portletBodyHeader2;

	@FindBy(xpath = "//section[contains(@id,'BillboardjsPortlet')]/div/h2")
	private WebElement _portletTitle;

	@FindBy(xpath = "//section[contains(@id,'BillboardjsPortlet')]/div/div/div/h2")
	private WebElement _portletTitleMaster;

	@PortalURL("com_liferay_blade_npm_billboardjs_portlet_BillboardjsPortlet")
	private URL _portletURL;

	@Drone
	private WebDriver _webDriver;

}