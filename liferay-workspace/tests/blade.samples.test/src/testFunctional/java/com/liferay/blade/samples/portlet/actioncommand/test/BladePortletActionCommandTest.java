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

package com.liferay.blade.samples.portlet.actioncommand.test;

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
public class BladePortletActionCommandTest {

	@Deployment
	public static JavaArchive create() throws Exception {
		final File jarFile = new File(
			System.getProperty("actionCommandPortletJarFile"));

		return ShrinkWrap.createFromZipFile(JavaArchive.class, jarFile);
	}

	@Test
	public void testBladePortletActionCommand()
		throws InterruptedException, PortalException {

		_webDriver.get(_portletURL.toExternalForm());

		BladeSampleFunctionalActionUtil.implicitWait(_webDriver);

		Assert.assertTrue(
			"Portlet was not deployed",
			_bladeSampleActionCommandGreeterPortlet.isDisplayed());

		Assert.assertTrue(
			"Name Field is not visible",
			_nameField.isDisplayed());

		_nameField.clear();

		_nameField.sendKeys("tester");

		BladeSampleFunctionalActionUtil.mouseOverClick(_webDriver, _saveButton);

		Assert.assertTrue(
			"Expected Hello tester! Welcome to OSGi Hello from BLADE!, but saw " +
				_portletBody.getText(),
			_portletBody.getText().contains(
				"Hello tester! Welcome to OSGi Hello from BLADE!"));
	}

	@FindBy(xpath = "//div[contains(@id,'com_liferay_blade_samples_portlet_actioncommand_GreeterPortlet')]")
	private WebElement _bladeSampleActionCommandGreeterPortlet;

	@FindBy(xpath = "//input[@type='text' and contains(@id,'com_liferay_blade_samples_portlet_actioncommand_GreeterPortlet')]")
	private WebElement _nameField;

	@FindBy(xpath = "//div[contains(@id,'com_liferay_blade_samples_portlet_actioncommand_GreeterPortlet')]//..//div[@class='portlet-body']")
	private WebElement _portletBody;

	@PortalURL("com_liferay_blade_samples_portlet_actioncommand_GreeterPortlet")
	private URL _portletURL;

	@FindBy(xpath = "//button[@type='submit' and contains(.,'Save')]")
	private WebElement _saveButton;

	@Drone
	private WebDriver _webDriver;

}