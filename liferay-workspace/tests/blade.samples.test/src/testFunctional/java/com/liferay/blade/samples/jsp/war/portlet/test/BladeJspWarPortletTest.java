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

package com.liferay.blade.samples.jsp.war.portlet.test;

import aQute.remote.util.JMXBundleDeployer;

import com.liferay.arquillian.portal.annotation.PortalURL;
import com.liferay.blade.sample.test.functional.utils.BladeSampleFunctionalActionUtil;
import com.liferay.blade.samples.integration.test.utils.BladeCLIUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.io.File;

import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Liferay
 */
@RunAsClient
@RunWith(Arquillian.class)
public class BladeJspWarPortletTest {

	@AfterClass
	public static void cleanUpDependencies() throws Exception {
		new JMXBundleDeployer().uninstall(_jspPortletWarBSN);
	}

	@Deployment
	public static JavaArchive create() throws Exception {
		final File jarFile = new File(System.getProperty("jspPortletJarFile"));

		final File jspPortletWarFile = new File(
			System.getProperty("jspPortletWarFile"));

		String bundleID = BladeCLIUtil.installBundle(jspPortletWarFile);

		BladeCLIUtil.startBundle(bundleID);

		return ShrinkWrap.createFromZipFile(JavaArchive.class, jarFile);
	}

	@Test
	public void testBladePortletJSPWar()
		throws InterruptedException, PortalException {

		_webDriver.get(_portletURL.toExternalForm());

		BladeSampleFunctionalActionUtil.implicitWait(_webDriver);

		Assert.assertTrue(
			"Portlet was not deployed", _bladeSampleJSPPortlet.isDisplayed());

		Assert.assertTrue(
			"Expected Liferay MVC, but saw " + _portletTitle.getText(),
			BladeSampleFunctionalActionUtil.getTextToLowerCase(
				_portletTitle).contains("liferay mvc"));

		Assert.assertTrue(
			"Hello from JSP War Portlet web!, but saw " +
				_portletBody.getText(),
			_portletBody.getText().contains("Hello from JSP War Portlet web!"));
	}

	private static String _jspPortletWarBSN = "jsp-war-portlet";

	@FindBy(xpath = "//div[contains(@id,'jspwarportlet_WAR_jspwarportlet')]")
	private WebElement _bladeSampleJSPPortlet;

	@FindBy(xpath = "//div[contains(@id,'jspwarportlet_WAR_jspwarportlet')]//..//p")
	private WebElement _portletBody;

	@FindBy(xpath = "//div[contains(@id,'jspwarportlet_WAR_jspwarportlet')]//..//h2")
	private WebElement _portletTitle;

	@PortalURL("jspwarportlet_WAR_jspwarportlet")
	private URL _portletURL;

	@Drone
	private WebDriver _webDriver;

}