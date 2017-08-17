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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

	public void customClick(WebDriver webDriver, WebElement webElement) {
		Assert.assertTrue("Element is not visible", isVisible(webElement));

		Actions action = new Actions(webDriver);

		action.moveToElement(webElement).build().perform();

		WebDriverWait wait = new WebDriverWait(webDriver, 30);

		WebElement element = wait.until(
			ExpectedConditions.elementToBeClickable(webElement));

		element.click();
	}

	@Test
	public void testBladeConfigurationAction()
		throws InterruptedException, PortalException {

		_webDriver.get(_portletURL.toExternalForm());

		String url = _webDriver.getCurrentUrl();

		Assert.assertTrue(
			"Portlet was not deployed", isVisible(_bladeMessagePortlet));

		_bodyWebElement.click();

		customClick(_webDriver, _verticalEllipsis);

		WebElement configuration = _webDriver.findElement(
			By.linkText("Configuration"));

		String configurationLink = configuration.getAttribute("href");

		_newWebDriverWindow.get(configurationLink);

		customClick(_newWebDriverWindow, _saveButton);

		Assert.assertTrue(
			"Success Message is not visible", isVisible(_successMessage));

		_webDriver.get(url);

		Assert.assertTrue(
				"Expected Blade Message Portlet, but saw: " + _portletTitle.getText(),
				_portletTitle.getText().contentEquals("Blade Message Portlet"));

		Assert.assertTrue("Expected Hello from BLADE JSP!, but saw: " + _portletBody.getText(),
			_portletBody.getText().contentEquals("Hello from BLADE JSP!"));
	}

	protected boolean isVisible(WebElement webelement) {
		WebDriverWait webDriverWait = new WebDriverWait(_webDriver, 30);

		try {
			webDriverWait.until(ExpectedConditions.visibilityOf(webelement));

			return true;
		}
		catch (org.openqa.selenium.TimeoutException te) {
			return false;
		}
	}

	@FindBy(xpath = "//section[contains(@id,'BladeMessagePortlet')]")
	private WebElement _bladeMessagePortlet;

	@FindBy(xpath = "//body")
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

	@FindBy(xpath = "//section[contains(@id,'BladeMessagePortlet')]//..//span/*[name()='svg'][contains(@class,'icon-ellipsis')]")
	private WebElement _verticalEllipsis;

	@Drone
	private WebDriver _webDriver;

}