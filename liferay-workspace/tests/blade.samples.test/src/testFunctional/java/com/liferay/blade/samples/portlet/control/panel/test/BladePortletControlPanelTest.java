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

package com.liferay.blade.samples.portlet.control.panel.test;

import com.liferay.portal.kernel.exception.PortalException;

import java.io.File;
import java.net.MalformedURLException;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Lawrence Lee
 */
@RunAsClient
@RunWith(Arquillian.class)
public class BladePortletControlPanelTest {

	@Deployment
	public static JavaArchive create() throws Exception {
		final File jarFile = new File(
			System.getProperty("controlPanelPortletJarFile"));

		return ShrinkWrap.createFromZipFile(JavaArchive.class, jarFile);
	}

	public void customClick(WebDriver webDriver, WebElement webElement) {
		Actions action = new Actions(webDriver);

		action.moveToElement(webElement).build().perform();

		WebDriverWait wait = new WebDriverWait(webDriver, 30);

		WebElement element = wait.until(
			ExpectedConditions.elementToBeClickable(webElement));

		element.click();
	}


	@Test
	public void testBladePortletControlPanel()
		throws InterruptedException, PortalException, MalformedURLException {

		URL url = new URL("http://localhost:8080/group/control_panel/manage?p_p_id=" + _portletName);

		_webDriver.get(url.toExternalForm());

		Assert.assertTrue(
			"Portlet was not deployed", isVisible(_controlPanelPortlet));

		Assert.assertTrue(
				"Expected Control Panel Demo, but saw: " + _portletTitle.getText(),
				_portletTitle.getText().contentEquals("Control Panel Demo"));

		Assert.assertTrue("Expected We are in the control panel, but saw: " + _portletBody.getText(),
				_portletBody.getText().contentEquals("We are in the control panel"));

	}

	protected boolean isVisible(WebElement webelement) {
		WebDriverWait webDriverWait = new WebDriverWait(_webDriver, 15);

		try {
			webDriverWait.until(ExpectedConditions.visibilityOf(webelement));

			return true;
		}
		catch (org.openqa.selenium.TimeoutException te) {
			return false;
		}
	}

	private String _portletName = "com_liferay_blade_samples_portlet_controlpanel_ControlPanelAppPortlet";

	@FindBy(xpath = "//section[contains(@id,'ControlPanelAppPortlet')]")
	private WebElement _controlPanelPortlet;;

	@FindBy(xpath = "//div[contains(@id,'ControlPanelAppPortlet')]//..//p")
	private WebElement _portletBody;

	@FindBy(xpath = "//div[contains(@id,'ControlPanelAppPortlet')]//..//span")
	private WebElement _portletTitle;

	@Drone
	private WebDriver _webDriver;

}
