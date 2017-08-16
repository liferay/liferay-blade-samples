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
import com.liferay.blade.samples.integration.test.utils.BladeCLIUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.io.File;

import java.net.URL;

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

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Liferay
 */
@RunAsClient
@RunWith(Arquillian.class)
public class BladeJspWarPortletTest {

	@AfterClass
	public static void cleanUpDependencies() throws Exception {
		new JMXBundleDeployer().uninstall(_fooApiJarBSN);
		new JMXBundleDeployer().uninstall(_fooServiceJarBSN);
		new JMXBundleDeployer().uninstall(_jspPortletWarBSN);
	}

	@Deployment
	public static JavaArchive create() throws Exception {
		final File jarFile = new File(System.getProperty("jarFile"));

		final File fooApiJar = new File(System.getProperty("fooApiJarFile"));
		final File fooServiceJar = new File(
			System.getProperty("fooServiceJarFile"));

		new JMXBundleDeployer().deploy(_fooApiJarBSN, fooApiJar);
		new JMXBundleDeployer().deploy(_fooServiceJarBSN, fooServiceJar);

		final File jspPortletWarFile = new File(System.getProperty("jspPortletWarFile"));

		String bundleID = BladeCLIUtil.installBundle(jspPortletWarFile);

		BladeCLIUtil.startBundle(bundleID);

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
	public void testCreateFoo() throws InterruptedException, PortalException {
		_webDriver.get(_portletURL.toExternalForm());

		customClick(_webDriver, _addButton);

		Assert.assertTrue("Field1 is not visible", isVisible(_field1Form));

		_field1Form.sendKeys("Hello");

		_field5Form.clear();

		_field5Form.sendKeys("World");

		customClick(_webDriver, _saveButton);

		Assert.assertTrue(
			"Service Builder Table is not visible", isVisible(_table));

		Assert.assertTrue(
			"Hello World is not present in table",
			_table.getText().contains("Hello"));

		Assert.assertTrue(
			"Hello World is not present in table",
			_table.getText().contains("World"));
	}

	@Test
	public void testDeleteFoo() throws InterruptedException, PortalException {
		_webDriver.get(_portletURL.toExternalForm());

		List<WebElement> rows = _webDriver.findElements(By.xpath(_tableRow));

		int originalRows = rows.size();

		Assert.assertTrue(
			"Liferay Icon Menus is not visible", isVisible(_lfrIconMenu));

		customClick(_webDriver, _lfrIconMenu);

		JavascriptExecutor javascriptExecutor = (JavascriptExecutor)_webDriver;

		Assert.assertTrue(
			"Action Menu Delete is not visible", isVisible(_lfrMenuDelete));

		String source = _webDriver.getPageSource();

		String executescript = source.substring(
			source.indexOf("item-remove") + 1,
			source.indexOf("foosSearchContainer__10__menu__delete"));

		String script = executescript.substring(
			executescript.indexOf("submitForm") - 1,
			executescript.indexOf("else") - 2);

		javascriptExecutor.executeScript(script);

		Thread.sleep(1000);

		_webDriver.navigate().refresh();

		Assert.assertTrue(
			"Service Builder Table is not visible", isVisible(_table));

		rows = _webDriver.findElements(By.xpath(_tableRow));

		int newRows = rows.size();

		int expectedFoos = originalRows - 1;

		Assert.assertTrue(
			"Expected " + expectedFoos + " foos, but saw " + newRows + " foos",
			newRows == expectedFoos);
	}

	@Test
	public void testReadFoo() throws PortalException {
		_webDriver.get(_portletURL.toExternalForm());

		Assert.assertTrue(
			"First Row Field 1 is not visible", isVisible(_firstRowField1));

		Assert.assertTrue(
			"First row field 1 does not contain entry",
			_firstRowField1.getText().contains("new field1 entry"));

		Assert.assertTrue(
			"Second row field 1 does not contain entry",
			_secondRowField1.getText().contains("new field1 entry"));
	}

	@Test
	public void testUpdateFoo() throws InterruptedException, PortalException {
		_webDriver.get(_portletURL.toExternalForm());

		Assert.assertTrue(
			"Liferay Icon menu is not visible", isClickable(_lfrIconMenu));

		customClick(_webDriver, _lfrIconMenu);

		Assert.assertTrue(
			"Liferay Menu Edit is not visible", isClickable(_lfrMenuEdit));

		customClick(_webDriver, _lfrMenuEdit);

		Assert.assertTrue(
			"Field 1 form is not visible", isVisible(_field1Form));

		_field1Form.clear();

		_field1Form.sendKeys("field1 with Updated Name");

		customClick(_webDriver, _saveButton);

		Assert.assertTrue(
			"Service Builder Table is not visible", isVisible(_table));

		Assert.assertTrue(
			"Service Builder Table does not contain Updated Name",
			_table.getText().contains("field1 with Updated Name"));
	}

	protected static boolean isAlertPresent(WebDriver webDriver) {
		WebDriverWait webDriverWait = new WebDriverWait(webDriver, 3);

		try {
			ExpectedCondition<Alert> alert =
				ExpectedConditions.alertIsPresent();

			webDriverWait.until(alert);

			return true;
		}
		catch (org.openqa.selenium.TimeoutException te) {
			return false;
		}
	}

	protected boolean isClickable(WebElement webelement) {
		WebDriverWait webDriverWait = new WebDriverWait(_webDriver, 15);

		try {
			webDriverWait.until(
				ExpectedConditions.elementToBeClickable(webelement));

			return true;
		}
		catch (org.openqa.selenium.TimeoutException te) {
			return false;
		}
	}

	protected boolean isVisible(WebElement webelement) {
		WebDriverWait webDriverWait = new WebDriverWait(_webDriver, 10);

		try {
			webDriverWait.until(ExpectedConditions.visibilityOf(webelement));

			return true;
		}
		catch (org.openqa.selenium.TimeoutException te) {
			return false;
		}
	}

	private static String _fooApiJarBSN = "com.liferay.blade.foo.api";
	private static String _fooServiceJarBSN = "com.liferay.blade.foo.service";
	private static String _jspPortletWarBSN = "jsp-war-portlet";

	@FindBy(xpath = "//span[@class='lfr-btn-label']")
	private WebElement _addButton;

	@FindBy(css = "input[id$='field1']")
	private WebElement _field1Form;

	@FindBy(css = "input[id$='field5']")
	private WebElement _field5Form;

	@FindBy(xpath = "//div[contains(@id,'jspwarportlet_WAR_jspwarportlet')]/table/tbody/tr/td[2]")
	private WebElement _firstRowField1;

	@FindBy(xpath = "//a[contains(@id,'foosSearchContainer')]")
	private WebElement _lfrIconMenu;

	@FindBy(xpath = "//ul[contains(@class,'dropdown-menu')]/li[2]/a[contains(.,'Delete')]")
	private WebElement _lfrMenuDelete;

	@FindBy(xpath = "//ul[contains(@class,'dropdown-menu')]/li[1]/a[contains(.,'Edit')]")
	private WebElement _lfrMenuEdit;

	@PortalURL("jspwarportlet_WAR_jspwarportlet")
	private URL _portletURL;

	@FindBy(css = "button[type=submit]")
	private WebElement _saveButton;

	@FindBy(xpath = "//div[contains(@id,'jspwarportlet_WAR_jspwarportlet')]/table/tbody/tr[2]/td[2]")
	private WebElement _secondRowField1;

	@FindBy(xpath = "//table[contains(@data-searchcontainerid,'foosSearchContainer')]")
	private WebElement _table;

	private String _tableRow = "//table[contains(@data-searchcontainerid,'foosSearchContainer')]/tbody/tr";

	@Drone
	private WebDriver _webDriver;

}
