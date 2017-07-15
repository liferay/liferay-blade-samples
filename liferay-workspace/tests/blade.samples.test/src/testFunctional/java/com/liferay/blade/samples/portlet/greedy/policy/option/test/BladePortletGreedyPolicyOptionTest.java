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

package com.liferay.blade.samples.portlet.greedy.policy.option.test;

import aQute.lib.io.IO;

import aQute.remote.util.JMXBundleDeployer;

import com.liferay.arquillian.portal.annotation.PortalURL;

import java.io.File;

import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import org.junit.After;
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
public class BladePortletGreedyPolicyOptionTest {

	@Deployment
	public static JavaArchive create() throws Exception {
		final File jarFile = new File(
			System.getProperty("greedyServiceReferenceJarFile"));

		return ShrinkWrap.createFromZipFile(JavaArchive.class, jarFile);
	}

	@After
	public void cleanUpDependencies() throws Exception {
		new JMXBundleDeployer().uninstall(_higherRankedServiceJarBSN);
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
	public void testBladePortletGreedy() throws Exception {
		_webDriver.get(_greedyPortletURL.toExternalForm());

		Assert.assertTrue(
			"Portlet was not deployed", isVisible(_bladeSampleGreedyPortlet));

		Assert.assertTrue(
			"Expected Greedy Portlet, but saw " +
				_greedyPortletTitle.getText(),
			_greedyPortletTitle.getText().contentEquals("Greedy Portlet"));

		Assert.assertTrue(
			"Expected SomeService says I am Default!, but saw " +
				_greedyPortletBody.getText(),
			_greedyPortletBody.getText().contentEquals(
				"SomeService says I am Default!"));

		final File higherRankedServiceJar = new File(
			System.getProperty("greedyHigherRankedServiceJarFile"));

		new JMXBundleDeployer().deploy(
			_higherRankedServiceJarBSN, higherRankedServiceJar);

		_webDriver.navigate().refresh();

		Assert.assertTrue(
			"Expected SomeService says I am better, use me!, but saw " +
				_greedyPortletBody.getText(),
			isTextVisible(
				_greedyPortletBody, "SomeService says I am better, use me!"));
	}

	@Test
	public void testBladePortletReluctant() throws Exception {
		_webDriver.get(_reluctantPortletURL.toExternalForm());

		Assert.assertTrue(
			"Portlet was not deployed",
			isVisible(_bladeSampleReluctantPortlet));

		Assert.assertTrue(
			"Expected Reluctant Portlet, but saw " +
				_reluctantPortletTitle.getText(),
			_reluctantPortletTitle.getText().contentEquals(
				"Reluctant Portlet"));

		Assert.assertTrue(
			"Expected SomeService says I am Default!, but saw " +
				_reluctantPortletBody.getText(),
			_reluctantPortletBody.getText().contentEquals(
				"SomeService says I am Default!"));

		final File higherRankedServiceJar = new File(
			System.getProperty("greedyHigherRankedServiceJarFile"));

		new JMXBundleDeployer().deploy(
			_higherRankedServiceJarBSN, higherRankedServiceJar);

		_webDriver.navigate().refresh();

		Assert.assertTrue(
			"Expected SomeService says I am Default!, but saw " +
				_reluctantPortletBody.getText(),
			isTextVisible(
				_reluctantPortletBody, "SomeService says I am Default!"));

		final File greedyServiceReferenceConfigFile = new File(
			System.getProperty("greedyServiceReferenceProjectdir"),
			"configs/com.liferay.blade.reluctant.vs.greedy.portlet.portlet." +
				"ReluctantPortlet.cfg");

		final File osgiConfigDir = new File(
			System.getProperty("liferayHome"), "osgi/configs");

		File configFile = new File(
			osgiConfigDir,
			"com.liferay.blade.reluctant.vs.greedy.portlet.portlet." +
				"ReluctantPortlet.cfg");

		IO.copy(greedyServiceReferenceConfigFile, configFile);
		
		Thread.sleep(10000);
		
		_webDriver.navigate().refresh();

		Assert.assertTrue(
			"Expected SomeService says I am better, use me!, but saw " +
				_reluctantPortletBody.getText(),
			isTextVisible(
				_reluctantPortletBody,
				"SomeService says I am better, use me!"));
	}

	protected boolean isTextVisible(WebElement webelement, String string) {
		WebDriverWait webDriverWait = new WebDriverWait(_webDriver, 30);

		try {
			webDriverWait.until(
				ExpectedConditions.textToBePresentInElement(
					webelement, string));

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

	private static String _higherRankedServiceJarBSN = "higher-ranked-service";

	@FindBy(xpath = "//div[contains(@id,'Greedy')]")
	private WebElement _bladeSampleGreedyPortlet;

	@FindBy(xpath = "//div[contains(@id,'Reluctant')]")
	private WebElement _bladeSampleReluctantPortlet;

	@FindBy(xpath = "//div[contains(@id,'Greedy')]//..//b")
	private WebElement _greedyPortletBody;

	@FindBy(xpath = "//div[contains(@id,'Greedy')]//..//h2")
	private WebElement _greedyPortletTitle;

	@PortalURL("Greedy")
	private URL _greedyPortletURL;

	@FindBy(xpath = "//div[contains(@id,'Reluctant')]//..//b")
	private WebElement _reluctantPortletBody;

	@FindBy(xpath = "//div[contains(@id,'Reluctant')]//..//h2")
	private WebElement _reluctantPortletTitle;

	@PortalURL("Reluctant")
	private URL _reluctantPortletURL;

	@Drone
	private WebDriver _webDriver;

}