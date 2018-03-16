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

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

		final File jspPortletWarFile = new File(
			System.getProperty("jspPortletWarFile"));

		String bundleID = BladeCLIUtil.installBundle(jspPortletWarFile);

		BladeCLIUtil.startBundle(bundleID);

		return ShrinkWrap.createFromZipFile(JavaArchive.class, jarFile);
	}

	@Test
	public void testCreateFoo() throws InterruptedException, PortalException {
		_webDriver.get(_portletURL.toExternalForm());

		BladeSampleFunctionalActionUtil.mouseOverClick(_webDriver, _addButton);

		Assert.assertTrue(
			"Field1 is not visible",
			BladeSampleFunctionalActionUtil.isVisible(_webDriver, _field1Form));

		_field1Form.sendKeys("JSPWarPortletTest");

		_field5Form.clear();

		_field5Form.sendKeys("field5");

		BladeSampleFunctionalActionUtil.mouseOverClick(_webDriver, _saveButton);

		Assert.assertTrue(
			"Service Builder Table is not visible",
			BladeSampleFunctionalActionUtil.isVisible(_webDriver, _table));

		Assert.assertTrue(
			"JSPWarPortletTest is not present in table",
			_table.getText().contains("JSPWarPortletTest"));
	}

	@Test
	public void testDeleteFoo() throws InterruptedException, PortalException {
		_webDriver.get(_portletURL.toExternalForm());

		BladeSampleFunctionalActionUtil.implicitWait(_webDriver);

		List<WebElement> rows = _webDriver.findElements(By.xpath(_tableRow));

		int originalRows = rows.size();

		Assert.assertTrue(
			"Liferay Icon Menus is not visible",
			BladeSampleFunctionalActionUtil.isVisible(_webDriver, _lfrIconMenu));

		BladeSampleFunctionalActionUtil.mouseOverClick(_webDriver, _lfrIconMenu);

		JavascriptExecutor javascriptExecutor = (JavascriptExecutor)_webDriver;

		Assert.assertTrue(
			"Action Menu Delete is not clickable",
			BladeSampleFunctionalActionUtil.isClickable(_webDriver, _lfrMenuDelete));

		BladeSampleFunctionalActionUtil.mouseOverClick(_webDriver, _lfrMenuDelete);

		String source = _webDriver.getPageSource();

		String executescript = source.substring(
				source.indexOf("item-remove") + 1,
				source.indexOf("<span class=\"taglib-text-icon\">Delete</span>"));

		String script = executescript.substring(
			executescript.indexOf("submitForm") - 1,
			executescript.indexOf("else") - 2);

		javascriptExecutor.executeScript(script);

		Thread.sleep(1000);

		_webDriver.navigate().refresh();

		Assert.assertTrue(
			"Service Builder Table is not visible",
			BladeSampleFunctionalActionUtil.isVisible(_webDriver, _table));

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
			"Service Builder Table is not visible",
			BladeSampleFunctionalActionUtil.isVisible(_webDriver, _table));

		Assert.assertTrue(
			"new field5 entry is not present in table",
			_table.getText().contains("new field5 entry"));
	}

	@Test
	public void testUpdateFoo() throws InterruptedException, PortalException {
		_webDriver.get(_portletURL.toExternalForm());

		BladeSampleFunctionalActionUtil.implicitWait(_webDriver);

		Assert.assertTrue(
			"Liferay Icon menu is not visible",
			BladeSampleFunctionalActionUtil.isClickable(_webDriver, _lfrIconMenu));

		BladeSampleFunctionalActionUtil.mouseOverClick(_webDriver, _lfrIconMenu);

		Assert.assertTrue(
			"Liferay Menu Edit is not visible",
			BladeSampleFunctionalActionUtil.isClickable(_webDriver, _lfrMenuEdit));

		BladeSampleFunctionalActionUtil.mouseOverClick(_webDriver, _lfrMenuEdit);

		Assert.assertTrue(
			"Field 1 form is not visible",
			BladeSampleFunctionalActionUtil.isClickable(_webDriver, _field1Form));

		_field1Form.clear();

		_field1Form.sendKeys("field1 with Updated Name");

		BladeSampleFunctionalActionUtil.mouseOverClick(_webDriver, _saveButton);

		Assert.assertTrue(
			"Service Builder Table is not visible",
			BladeSampleFunctionalActionUtil.isVisible(_webDriver, _table));

		Assert.assertTrue(
			"Service Builder Table does not contain Updated Name",
			_table.getText().contains("field1 with Updated Name"));
	}

	private static String _fooApiJarBSN = "com.liferay.blade.basic.api";
	private static String _fooServiceJarBSN = "com.liferay.blade.basic.service";
	private static String _jspPortletWarBSN = "jsp-war-portlet";

	@FindBy(xpath = "//span[@class='lfr-btn-label']")
	private WebElement _addButton;

	@FindBy(css = "input[id$='field1']")
	private WebElement _field1Form;

	@FindBy(css = "input[id$='field5']")
	private WebElement _field5Form;

	@FindBy(xpath = "//div[contains(@id,'jspwarportlet_WAR_jspwarportlet')]/table//..//tr/td[2]")
	private WebElement _firstRowField5;

	@FindBy(xpath = "//div[@class='btn-group lfr-icon-menu']/a")
	private WebElement _lfrIconMenu;

	@FindBy(xpath = "//ul[contains(@class,'dropdown-menu')]/li[2]/a[contains(.,'Delete')]")
	private WebElement _lfrMenuDelete;

	@FindBy(xpath = "//ul[contains(@class,'dropdown-menu')]/li[1]/a[contains(.,'Edit')]")
	private WebElement _lfrMenuEdit;

	@PortalURL("jspwarportlet_WAR_jspwarportlet")
	private URL _portletURL;

	@FindBy(css = "button[type=submit]")
	private WebElement _saveButton;

	@FindBy(xpath = "//div[contains(@id,'jspwarportlet_WAR_jspwarportlet')]/table//..//tr[2]/td[2]")
	private WebElement _secondRowField5;

	@FindBy(xpath = "//table[contains(@data-searchcontainerid,'foosSearchContainer')]")
	private WebElement _table;

	private String _tableRow =
		"//table[contains(@data-searchcontainerid,'foosSearchContainer')]/tbody/tr";

	@Drone
	private WebDriver _webDriver;

}
