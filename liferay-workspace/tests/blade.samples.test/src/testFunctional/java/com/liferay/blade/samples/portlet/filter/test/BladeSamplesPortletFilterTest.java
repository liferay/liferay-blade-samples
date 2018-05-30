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

package com.liferay.blade.samples.portlet.filter.test;

import com.liferay.arquillian.portal.annotation.PortalURL;
import com.liferay.blade.sample.test.functional.utils.BladeSampleFunctionalActionUtil;
import com.liferay.portal.kernel.exception.PortalException;

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
public class BladeSamplesPortletFilterTest {

	@Deployment
	public static JavaArchive create() throws Exception {
		final File jarFile = new File(
			System.getProperty("filterPortletJarFile"));

		return ShrinkWrap.createFromZipFile(JavaArchive.class, jarFile);
	}

	@Test
	public void testBladePortletFilter()
		throws InterruptedException, PortalException {

		_webDriver.get(_portletURL.toExternalForm());

		BladeSampleFunctionalActionUtil.implicitWait(_webDriver);

		Assert.assertTrue(
			"Portlet was not deployed",
			_bladeSampleFilterPortlet.isDisplayed());

		Assert.assertTrue(
			"Expected Example Filter Portlet, but saw " +
				_portletTitle.getText(),
			BladeSampleFunctionalActionUtil.getTextToLowerCase(
				_portletTitle).equals("example filter portlet"));

		Assert.assertTrue(
			"Expected Custom Attribute = My Custom Attribute Value, but saw " +
				_portletBody.getText(),
			_portletBody.getText().equals(
				"Custom Attribute = My Custom Attribute Value"));
	}

	@FindBy(xpath = "//div[contains(@id,'blade_portlet_filter_ExamplePortlet')]")
	private WebElement _bladeSampleFilterPortlet;

	@FindBy(xpath = "//div[contains(@id,'blade_portlet_filter_ExamplePortlet')]//..//div[@class='portlet-body']")
	private WebElement _portletBody;

	@FindBy(xpath = "//div[contains(@id,'blade_portlet_filter_ExamplePortlet')]//..//h2")
	private WebElement _portletTitle;

	@PortalURL("blade_portlet_filter_ExamplePortlet")
	private URL _portletURL;

	@Drone
	private WebDriver _webDriver;

}