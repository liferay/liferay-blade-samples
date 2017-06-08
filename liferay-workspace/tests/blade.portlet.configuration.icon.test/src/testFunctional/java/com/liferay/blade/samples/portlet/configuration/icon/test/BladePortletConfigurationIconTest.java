/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.blade.samples.portlet.configuration.icon.test;

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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Liferay
 */
@RunAsClient
@RunWith(Arquillian.class)
public class BladePortletConfigurationIconTest {

	@Deployment
	public static JavaArchive create() throws Exception {
		final File jarFile = new File(System.getProperty("jarFile"));

		return ShrinkWrap.createFromZipFile(JavaArchive.class, jarFile);
	}

	public void customClick(WebDriver webDriver, WebElement webElement) {
		Actions action = new Actions(webDriver);

		action.moveToElement(webElement).build().perform();

		WebDriverWait wait = new WebDriverWait(webDriver, 5);

		WebElement element = wait.until(
			ExpectedConditions.visibilityOf(webElement));

		element.click();
	}

	@Test
	public void testBladePortletConfigurationIcon()
		throws InterruptedException, PortalException {

		_webDriver.get(_portletURL.toExternalForm());

		Assert.assertTrue(
			"Portlet was not deployed", isVisible(_helloWorldPortlet));

		customClick(_webDriver, _verticalEllipsis);

		Assert.assertTrue(isVisible(_lfrMenuSampleLink));

		customClick(_webDriver, _lfrMenuSampleLink);

		Assert.assertTrue(
			"Expected: https://www.liferay.com/, but saw " +
				_webDriver.getCurrentUrl(),
			isPageLoaded("https://www.liferay.com/"));
	}

	protected boolean isPageLoaded(String string) {
		WebDriverWait webDriverWait = new WebDriverWait(_webDriver, 5);

		try {
			webDriverWait.until(ExpectedConditions.urlMatches(string));

			return true;
		}
		catch (org.openqa.selenium.TimeoutException te) {
			return false;
		}
	}

	protected boolean isVisible(WebElement webelement) {
		WebDriverWait webDriverWait = new WebDriverWait(_webDriver, 5);

		try {
			webDriverWait.until(ExpectedConditions.visibilityOf(webelement));

			return true;
		}
		catch (org.openqa.selenium.TimeoutException te) {
			return false;
		}
	}

	@FindBy(xpath = "//section[@id='portlet_com_liferay_hello_world_web_portlet_HelloWorldPortlet']")
	private WebElement _helloWorldPortlet;

	@FindBy(xpath = "//header[@id='banner']")
	private WebElement _lfrBanner;

	@FindBy(xpath = "//ul[contains(@class,'dropdown-menu')]/li[1]/a[contains(.,'Sample Link')]")
	private WebElement _lfrMenuSampleLink;

	@PortalURL("com_liferay_hello_world_web_portlet_HelloWorldPortlet")
	private URL _portletURL;

	@FindBy(xpath = "//span/*[name()='svg'][contains(@class,'icon-ellipsis')]")
	private WebElement _verticalEllipsis;

	@Drone
	private WebDriver _webDriver;

}