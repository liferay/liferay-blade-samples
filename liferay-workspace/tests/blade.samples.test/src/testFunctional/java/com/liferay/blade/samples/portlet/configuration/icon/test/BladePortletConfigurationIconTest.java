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

package com.liferay.blade.samples.portlet.configuration.icon.test;

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
public class BladePortletConfigurationIconTest {

	@Deployment
	public static JavaArchive create() throws Exception {
		final File jarFile = new File(
			System.getProperty("portletConfigurationIconJarFile"));

		return ShrinkWrap.createFromZipFile(JavaArchive.class, jarFile);
	}

	@Test
	public void testBladePortletConfigurationIcon()
		throws InterruptedException, PortalException {

		_webDriver.get(_portletURL.toExternalForm());

		BladeSampleFunctionalActionUtil.implicitWait(_webDriver);

		Assert.assertTrue(
			"Portlet was not deployed",
			_helloWorldPortlet.isDisplayed());

		_helloWorldPortlet.click();

		BladeSampleFunctionalActionUtil.mouseOverClick(
			_webDriver, _verticalEllipsis);

		BladeSampleFunctionalActionUtil.mouseOverClick(
			_webDriver, _lfrMenuSampleLink);

		Assert.assertTrue(
			"Expected: https://www.liferay.com/, but saw " +
				_webDriver.getCurrentUrl(),
			_webDriver.getCurrentUrl().equals("https://www.liferay.com/"));

	}

	@FindBy(xpath = "//section[contains(@id,'HelloWorld')]//..//div[@class='portlet-body']")
	private WebElement _bodyWebElement;

	@FindBy(xpath = "//section[@id='portlet_com_liferay_hello_world_web_portlet_HelloWorldPortlet']")
	private WebElement _helloWorldPortlet;

	@FindBy(xpath = "//header[@id='banner']")
	private WebElement _lfrBanner;

	@FindBy(xpath = "//ul[contains(@class,'dropdown-menu')]/li[1]/a[contains(.,'Sample Link')]")
	private WebElement _lfrMenuSampleLink;

	@PortalURL("com_liferay_hello_world_web_portlet_HelloWorldPortlet")
	private URL _portletURL;

	@FindBy(xpath = "//*[contains(@id,'HelloWorldPortlet')]/div/a")
	private WebElement _verticalEllipsis;

	@Drone
	private WebDriver _webDriver;

}