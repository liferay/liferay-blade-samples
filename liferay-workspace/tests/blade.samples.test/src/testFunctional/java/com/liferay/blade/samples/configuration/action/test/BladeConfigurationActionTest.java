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

package com.liferay.blade.samples.configuration.action.test;

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

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Lawrence Lee
 */
@RunAsClient
@RunWith(Arquillian.class)
public class BladeConfigurationActionTest {

	@Deployment
	public static JavaArchive create() throws Exception {
		final File jarFile = new File(
			System.getProperty("configurationActionJarFile"));

		return ShrinkWrap.createFromZipFile(JavaArchive.class, jarFile);
	}

	@Test
	public void testBladeConfigurationAction()
		throws InterruptedException, PortalException {

		_webDriver.get(_portletURL.toExternalForm());

		BladeSampleFunctionalActionUtil.implicitWait(_webDriver);
		BladeSampleFunctionalActionUtil.implicitWait(_newWebDriverWindow);

		String url = _webDriver.getCurrentUrl();

		Assert.assertTrue(
			"Portlet was not deployed",
			_bladeMessagePortlet.isDisplayed());

		_bladeMessagePortlet.click();

		BladeSampleFunctionalActionUtil.mouseOverClick(
			_webDriver, _verticalEllipsis);

		WebElement configuration = _webDriver.findElement(
			By.linkText("Configuration"));

		String configurationLink = configuration.getAttribute("href");

		_newWebDriverWindow.get(configurationLink);

		BladeSampleFunctionalActionUtil.mouseOverClick(
			_newWebDriverWindow, _saveButton);

		Assert.assertTrue(
			"Success Message is not visible",
			_successMessage.isDisplayed());

		_webDriver.get(url);

		Assert.assertTrue(
			"Expected Blade Message Portlet, but saw: " +
				_portletTitle.getText(),
			BladeSampleFunctionalActionUtil.getTextToLowerCase(
				_portletTitle).equals("blade message portlet"));

		Assert.assertTrue(
			"Expected Hello from BLADE JSP!, but saw: " +
				_portletBody.getText(),
			BladeSampleFunctionalActionUtil.getTextToLowerCase(
				_portletBody).equals("hello from blade jsp!"));
	}

	@FindBy(xpath = "//section[contains(@id,'BladeMessagePortlet')]")
	private WebElement _bladeMessagePortlet;

	@FindBy(xpath = "//section[contains(@id,'BladeMessagePortlet')]//..//div[@class='portlet-body']")
	private WebElement _bodyWebElement;

	@FindBy(xpath = "//ul[contains(@class,'dropdown-menu')]/li[contains(@class,'configuration')]")
	private WebElement _lfrMenuConfiguration;

	@Drone
	private WebDriver _newWebDriverWindow;

	@FindBy(xpath = "//div[contains(@id,'BladeMessagePortlet')]//..//p")
	private WebElement _portletBody;

	@FindBy(xpath = "//div[contains(@id,'BladeMessagePortlet')]//..//h2")
	private WebElement _portletTitle;

	@PortalURL("BladeMessagePortlet")
	private URL _portletURL;

	@FindBy(css = "button[type=submit]")
	private WebElement _saveButton;

	@FindBy(xpath = "//div[contains(@class,'alert') and contains(@class,'alert-success')]")
	private WebElement _successMessage;

	@FindBy(xpath = "//*[contains(@id,'BladeMessagePortlet')]/div/a")
	private WebElement _verticalEllipsis;

	@Drone
	private WebDriver _webDriver;

}