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

package com.liferay.blade.samples.portlet.actioncommand.test;

import com.liferay.arquillian.portal.annotation.PortalURL;
import com.liferay.portal.kernel.exception.PortalException;

import java.io.File;

import java.net.URL;
import java.util.concurrent.TimeUnit;

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
public class BladePortletActionCommandTest {

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
	public void testBladePortletActionCommand()
		throws InterruptedException, PortalException {

		_webDriver.get(_portletURL.toExternalForm());

		Assert.assertTrue(
			"Portlet was not deployed",
			isVisible(_bladeSampleActionCommandGreeterPortlet));

		Assert.assertTrue(isVisible(_nameField));

		_nameField.clear();

		_nameField.sendKeys("tester");

		customClick(_webDriver, _saveButton);

		_webDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		Assert.assertTrue(isVisible(_portletBody));

		Assert.assertTrue(_portletBody.getText() +
			" does not equal Hello tester! Welcome to OSGi Hello from BLADE!",
			_portletBody.getText().contains(
				"Hello tester! Welcome to OSGi Hello from BLADE!"));
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

	@FindBy(xpath = "//div[contains(@id,'com_liferay_blade_samples_portlet_actioncommand_GreeterPortlet')]")
	private WebElement _bladeSampleActionCommandGreeterPortlet;

	@FindBy(xpath = "//form[contains(@id,'com_liferay_blade_samples_portlet_actioncommand_GreeterPortlet')]/div/input")
	private WebElement _nameField;

	@FindBy(xpath = "//div[contains(@id,'com_liferay_blade_samples_portlet_actioncommand_GreeterPortlet')]/section/div/div/div")
	private WebElement _portletBody;

	@PortalURL("com_liferay_blade_samples_portlet_actioncommand_GreeterPortlet")
	private URL _portletURL;

	@FindBy(css = "button[type=submit]")
	private WebElement _saveButton;

	@Drone
	private WebDriver _webDriver;

}