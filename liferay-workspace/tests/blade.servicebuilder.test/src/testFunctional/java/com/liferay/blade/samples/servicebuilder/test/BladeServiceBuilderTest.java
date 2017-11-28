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

package com.liferay.blade.samples.servicebuilder.test;

import aQute.remote.util.JMXBundleDeployer;

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

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
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
public class BladeServiceBuilderTest {

	@AfterClass
	public static void cleanUpDependencies() throws Exception {
		new JMXBundleDeployer().uninstall(_fooApiJarBSN);
		new JMXBundleDeployer().uninstall(_fooServiceJarBSN);
	}

	@Deployment
	public static JavaArchive create() throws Exception {
		final File fooApiJar = new File(System.getProperty("fooApiJarFile"));
		final File fooServiceJar = new File(
			System.getProperty("fooServiceJarFile"));
		final File fooWebJar = new File(System.getProperty("fooWebJarFile"));

		new JMXBundleDeployer().deploy(_fooApiJarBSN, fooApiJar);
		new JMXBundleDeployer().deploy(_fooServiceJarBSN, fooServiceJar);

		return ShrinkWrap.createFromZipFile(JavaArchive.class, fooWebJar);
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
	public void testSBBasic() throws InterruptedException, PortalException {
		_webDriver.get(_portletURL.toExternalForm());

		String url = _webDriver.getCurrentUrl();

		_webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		String windowHandler = _webDriver.getWindowHandle();

		customClick(_webDriver, _addButton);

		Assert.assertTrue("Field1 is not visible", isVisible(_field1Form));

		_field1Form.sendKeys("aSBDeletableEntry");

		_field5Form.clear();

		_field5Form.sendKeys("aSBDeletableEntryfield5");

		customClick(_webDriver, _saveButton);

		Thread.sleep(1000);

		_webDriver.navigate().to(url);

		Assert.assertTrue(
			"Service Builder Table does not contain aSBDeletableEntry",
			_table.getText().contains("aSBDeletableEntry"));

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

		Thread.sleep(1000);

		_webDriver.navigate().to(url);

		Assert.assertTrue(
			"Service Builder Table is not visible", isVisible(_table));

		Assert.assertTrue(
			"Service Builder Table does not contain Updated Name",
			_table.getText().contains("field1 with Updated Name"));

		Assert.assertTrue(
				"Liferay Icon menu is not visible", isClickable(_lfrIconMenu));

		customClick(_webDriver, _lfrIconMenu);

		Assert.assertTrue(
			"Liferay Menu Delete is not visible", isClickable(_lfrMenuDelete));

		customClick(_webDriver, _lfrMenuDelete);

		Assert.assertTrue(
				"Alert is not present!",
				isAlertPresent());

		_webDriver.switchTo().window(windowHandler);

		Thread.sleep(1000);

		_webDriver.navigate().to(url);

		Assert.assertTrue(
			_table.getText(), !_table.getText().contains("aSBDeletableEntry"));
	}

	protected boolean isAlertPresent() {
		try{
			WebDriverWait webDriverWait = new WebDriverWait(_webDriver, 15);

			Alert alert = webDriverWait.until(
				ExpectedConditions.alertIsPresent());

			if(alert != null) {
				_webDriver.switchTo().alert().accept();

				return true;
			}

			else{
				throw new NoAlertPresentException();
			}
		}
		catch (NoAlertPresentException e) {
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
		WebDriverWait webDriverWait = new WebDriverWait(_webDriver, 30);

		try {
			webDriverWait.until(ExpectedConditions.visibilityOf(webelement));

			return true;
		}
		catch (org.openqa.selenium.TimeoutException te) {
			return false;
		}
	}

	private static String _fooApiJarBSN = "blade.servicebuilder.api";
	private static String _fooServiceJarBSN = "blade.servicebuilder.svc";

	@FindBy(xpath = "//span[@class='lfr-btn-label']")
	private WebElement _addButton;

	@FindBy(css = "input[id$='field1']")
	private WebElement _field1Form;

	@FindBy(css = "input[id$='field5']")
	private WebElement _field5Form;

	@FindBy(xpath = "//div[contains(@id,'_com_liferay_blade_samples_servicebuilder_web')]/table//..//tr/td[6]")
	private WebElement _firstRowField5;

	@FindBy(xpath = "//div[@class='btn-group lfr-icon-menu']/a")
	private WebElement _lfrIconMenu;

	@FindBy(xpath = "//ul[contains(@class,'dropdown-menu')]/li[2]/a[contains(.,'Delete')]")
	private WebElement _lfrMenuDelete;

	@FindBy(xpath = "//ul[contains(@class,'dropdown-menu')]/li[1]/a[contains(.,'Edit')]")
	private WebElement _lfrMenuEdit;

	@PortalURL("com_liferay_blade_samples_servicebuilder_web")
	private URL _portletURL;

	@FindBy(css = "button[type=submit]")
	private WebElement _saveButton;

	@FindBy(xpath = "//div[contains(@id,'_com_liferay_blade_samples_servicebuilder_web')]/table//..//tr[2]/td[6]")
	private WebElement _secondRowField5;

	@FindBy(xpath = "//table[contains(@data-searchcontainerid,'foosSearchContainer')]")
	private WebElement _table;

	@Drone
	private WebDriver _webDriver;

}