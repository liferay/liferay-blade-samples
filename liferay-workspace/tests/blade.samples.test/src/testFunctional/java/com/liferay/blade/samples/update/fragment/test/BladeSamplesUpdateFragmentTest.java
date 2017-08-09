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
import com.liferay.blade.samples.integration.test.utils.BladeCLIUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;

import java.net.URL;

import java.util.ArrayList;
import java.util.List;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
			System.getProperty("user.dir")).getParentFile().getParentFile();

		_overridesPath = new File(_overridesPath, "overrides");

		File moduleJspPath = new File(_overridesPath, "module-jsp-override");

		_projectPath = new File(_overridesPath, "module-jsp-override-samples");

		IO.copy(moduleJspPath, _projectPath);

		try {
			BladeCLIUtil.execute(_projectPath, "deploy");
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return ShrinkWrap.createFromZipFile(JavaArchive.class, jarFile);
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		try {
			BladeCLIUtil.uninstallBundle(_moduleJspOverrideJarBSN);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		if (_projectPath.exists()) {
			IO.delete(_projectPath);
			Assert.assertFalse(_projectPath.exists());
		}
	}

	@Test
	public void testUpdateModuleJSPFragmentProject() throws Exception {
		_webDriver.get(_portletURL.toExternalForm());

		Assert.assertTrue("Portlet was not deployed", isVisible(_loginPortlet));
		Assert.assertTrue(
			_portletTitle.getText(),
			_portletTitle.getText().contentEquals("Sign In"));
		Assert.assertTrue(
			"Portlet Body is not visible", isVisible(_portletBody));
		Assert.assertTrue(
			"Expected changed, but saw: " + _portletStyle.getText(),
			_portletStyle.getText().contentEquals("changed"));

		File staticFile = new File(
			_projectPath +
			"/src/main/resources/META-INF/resources/login.jsp");

		List<String> lines = new ArrayList<>();

		String line = null;

		try (BufferedReader reader =
		new BufferedReader(new FileReader(staticFile))) {

			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}

		}

		lines.set(17, "<p style=\"color: red\">samples work!</p>");

		try (Writer writer = new FileWriter(staticFile)) {
			for (String string : lines) {
				writer.write(string + "\n");
			}
		}

		try {
			BladeCLIUtil.execute(_projectPath, "deploy");
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		Thread.sleep(1000);

		_webDriver.get(_portletURL.toExternalForm());

		Assert.assertTrue("Portlet was not deployed", isVisible(_loginPortlet));
		Assert.assertTrue(
			_portletTitle.getText(),
			_portletTitle.getText().contentEquals("Sign In"));
		Assert.assertTrue(
			"Portlet Body is not visible", isVisible(_portletBody));
		Assert.assertTrue(
			"Expected samples work!, but saw: " + _portletStyle.getText(),
			_portletStyle.getText().contentEquals("samples work!"));
	}

	protected boolean isVisible(WebElement webelement) {
		WebDriverWait webDriverWait = new WebDriverWait(_webDriver, 60);

		try {
			webDriverWait.until(ExpectedConditions.visibilityOf(webelement));

			return true;
		}
		catch (org.openqa.selenium.TimeoutException te) {
			return false;
		}
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