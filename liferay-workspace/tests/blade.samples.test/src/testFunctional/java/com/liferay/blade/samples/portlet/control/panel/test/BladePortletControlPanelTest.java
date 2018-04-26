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

import com.liferay.blade.sample.test.functional.utils.BladeSampleFunctionalActionUtil;
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
import org.openqa.selenium.support.FindBy;

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

	@Test
	public void testBladePortletControlPanel()
		throws InterruptedException, MalformedURLException, PortalException {

		URL url = new URL(
			"http://localhost:8080/group/control_panel/manage?p_p_id=" +
				_portletName);

		BladeSampleFunctionalActionUtil.implicitWait(_webDriver);

		_webDriver.get(url.toExternalForm());

		Assert.assertTrue(
			"Portlet was not deployed",
			_controlPanelPortlet.isDisplayed());

		Assert.assertTrue(
			"Expected Control Panel Demo, but saw: " + _portletTitle.getText(),
			_portletTitle.getText().equals("Control Panel Demo"));

		Assert.assertTrue("Expected We are in the control panel, but saw: " + _portletBody.getText(),
			_portletBody.getText().equals("We are in the control panel"));
	}

	@FindBy(xpath = "//section[contains(@id,'ControlPanelAppPortlet')]")
	private WebElement _controlPanelPortlet;

	@FindBy(xpath = "//div[contains(@id,'ControlPanelAppPortlet')]//..//p")
	private WebElement _portletBody;

	private String _portletName =
		"com_liferay_blade_samples_portlet_controlpanel_ControlPanelAppPortlet";

	@FindBy(xpath = "//span[@data-qa-id='headerTitle']")
	private WebElement _portletTitle;

	@Drone
	private WebDriver _webDriver;

}