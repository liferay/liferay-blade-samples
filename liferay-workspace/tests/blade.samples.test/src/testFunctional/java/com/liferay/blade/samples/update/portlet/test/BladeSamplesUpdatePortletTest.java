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

package com.liferay.blade.samples.update.portlet.test;

import static org.junit.Assert.assertFalse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.gradle.testkit.runner.BuildTask;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.liferay.arquillian.portal.annotation.PortalURL;
import com.liferay.blade.samples.integration.test.BladeCLIUtil;
import com.liferay.blade.samples.integration.test.GradleRunnerUtil;

import aQute.lib.io.IO;

/**
 * @author Lawrence Lee
 */
@RunAsClient
@RunWith(Arquillian.class)
public class BladeSamplesUpdatePortletTest {
	
	@AfterClass
	public static void tearDownClass() throws Exception {
		if (BladeCLIUtil.bladeJar.exists()) {
			IO.delete(BladeCLIUtil.bladeJar);
			assertFalse(BladeCLIUtil.bladeJar.exists());
		}
	}
	
	@Deployment
	public static JavaArchive create() throws Exception {
		_testIntegrationDir = new File(
			System.getProperty("user.dir")).getParentFile();
		
		_projectPath = BladeCLIUtil.createProject(
			_testIntegrationDir, "mvc-portlet", "helloworld");
		
		File buildPath = new File(
			_projectPath.getParentFile().getParentFile().getAbsolutePath());
		
		BuildTask assembletask = GradleRunnerUtil.executeGradleRunner(
			buildPath, ":tests:helloworld:assemble");

		GradleRunnerUtil.verifyGradleRunnerOutput(assembletask);
		
		File buildOutput = new File(
			_projectPath + "/build/libs/helloworld-1.0.0.jar");
		
		return ShrinkWrap.createFromZipFile(JavaArchive.class, buildOutput);
	}

	@After
	public void tearDown() throws Exception {
		if (_testIntegrationDir.exists()) {
			IO.delete(_testIntegrationDir);
			assertFalse(_testIntegrationDir.exists());
		}
	}
	
	@Test
	public void testUpdateMVCPortletProject() throws Exception {	
		_webDriver.get(_portletURL.toExternalForm());

		Assert.assertTrue(
			"Portlet was not deployed", isVisible(_helloWorldPortlet));
		Assert.assertTrue(
			_portletTitle.getText(),
			_portletTitle.getText().contentEquals(
				"helloworld Portlet"));
		Assert.assertTrue(
			_portletBody.getText(),
			_portletBody.getText().contentEquals(
				"Hello from helloworld JSP!"));

		File file = new File(
			_projectPath +
			"/src/main/java/helloworld/portlet/HelloworldPortlet.java");

		List<String> lines = new ArrayList<>();
		String line = null;

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			while ((line = reader.readLine()) != null) {
				lines.add(line);

				if (line.equals(
						"public class HelloworldPortlet extends MVCPortlet {"))
					{

					String s =
						new StringBuilder().
							append("public void print() throws Exception {\n").
							append("System.out.println(\"Edited\");\n").
							append("}\n").toString();

					lines.add(s);
				}
			}
		}
		
		try (Writer writer = new FileWriter(file)) {
			for (String string : lines) {
				writer.write(string + "\n");
			}
		}
		
		_webDriver.navigate().refresh();
		
		Assert.assertTrue(
			_portletBody.getText(),
			_portletBody.getText().contentEquals(
				"Hello from BLADE Language Web!"));
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
	
	private static File _projectPath;
	private static File _testIntegrationDir;
	
	@FindBy(xpath = "//div[contains(@id,'_Helloworld')]")
	private WebElement _helloWorldPortlet;

	@FindBy(xpath = "//div[contains(@id,'_Helloworld')]//..//p")
	private WebElement _portletBody;

	@FindBy(xpath = "//div[contains(@id,'_Helloworld')]//..//h2")
	private WebElement _portletTitle;

	@PortalURL("Helloworld")
	private URL _portletURL;

	@Drone
	private WebDriver _webDriver;

}
