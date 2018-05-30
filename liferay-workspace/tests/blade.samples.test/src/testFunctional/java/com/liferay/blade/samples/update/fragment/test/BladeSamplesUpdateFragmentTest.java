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

package com.liferay.blade.samples.update.fragment.test;

import aQute.lib.io.IO;

import com.liferay.arquillian.portal.annotation.PortalURL;
import com.liferay.blade.sample.test.functional.utils.BladeSampleFunctionalActionUtil;
import com.liferay.blade.samples.integration.test.utils.BladeCLIUtil;

import java.io.File;

import java.net.URL;

import java.nio.file.Files;

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
 * @author Lawrence Lee
 */
@RunAsClient
@RunWith(Arquillian.class)
public class BladeSamplesUpdateFragmentTest {

	@Deployment
	public static JavaArchive create() throws Exception {
		final File jarFile = new File(System.getProperty("jspPortletJarFile"));

		_overridesPath = new File(
			System.getProperty("projectDir"), "overrides");

		File moduleJspPath = new File(
			System.getProperty("projectDir"), "overrides/module-jsp-override");

		_projectPath = new File(_overridesPath, "module-jsp-override-samples");

		IO.copy(moduleJspPath, _projectPath);

		BladeCLIUtil.execute(_projectPath, "deploy");

		return ShrinkWrap.createFromZipFile(JavaArchive.class, jarFile);
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		BladeCLIUtil.uninstallBundle(_moduleJspOverrideJarBSN);

		if (_projectPath.exists()) {
			IO.delete(_projectPath);
			Assert.assertFalse(_projectPath.exists());
		}
	}

	@Test
	public void testUpdateModuleJSPFragmentProject() throws Exception {
		_webDriver.get(_portletURL.toExternalForm());

		BladeSampleFunctionalActionUtil.implicitWait(_webDriver);

		Assert.assertTrue(
			"Portlet was not deployed",
			_loginPortlet.isDisplayed());
		Assert.assertTrue(
			_portletTitle.getText(),
			BladeSampleFunctionalActionUtil.getTextToLowerCase(
				_portletTitle).contentEquals("sign in"));
		Assert.assertTrue(
			"Portlet Body is not visible",
			_portletBody.isDisplayed());
		Assert.assertTrue(
			"Expected changed, but saw: " + _portletStyle.getText(),
			_portletStyle.getText().equals("changed"));

		File staticFile = new File(
			_projectPath + "/src/main/resources/META-INF/resources/login.jsp");

		String content = new String(Files.readAllBytes(staticFile.toPath()));

		String newContent = content.replaceFirst("changed", "samples work!");

		Files.write(staticFile.toPath(), newContent.getBytes());

		BladeCLIUtil.execute(_projectPath, "deploy");

		Thread.sleep(5000);

		_webDriver.get(_portletURL.toExternalForm());

		Assert.assertTrue(
			"Portlet was not deployed",
			_loginPortlet.isDisplayed());
		Assert.assertTrue(
			_portletTitle.getText(),
			BladeSampleFunctionalActionUtil.getTextToLowerCase(
				_portletTitle).contentEquals("sign in"));
		Assert.assertTrue(
			"Portlet Body is not visible",
			_portletBody.isDisplayed());
		Assert.assertTrue(
			"Expected samples work!, but saw: " + _portletStyle.getText(),
			_portletStyle.getText().equals("samples work!"));
	}

	private static String _moduleJspOverrideJarBSN =
		"module-jsp-override-samples";
	private static File _overridesPath;
	private static File _projectPath;

	@FindBy(xpath = "//div[contains(@id,'LoginPortlet')]")
	private WebElement _loginPortlet;

	@FindBy(xpath = "//div[contains(@id,'LoginPortlet')]//..//div[@class='portlet-body']")
	private WebElement _portletBody;

	@FindBy(xpath = "//div[contains(@id,'LoginPortlet')]//..//p")
	private WebElement _portletStyle;

	@FindBy(xpath = "//div[contains(@id,'LoginPortlet')]//..//h2")
	private WebElement _portletTitle;

	@PortalURL("com_liferay_login_web_portlet_LoginPortlet")
	private URL _portletURL;

	@Drone
	private WebDriver _webDriver;

}