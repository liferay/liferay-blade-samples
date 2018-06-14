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

package com.liferay.blade.samples.spring.mvc.portlet.test;

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
public class BladeSpringMVCPortletTest {

	@AfterClass
	public static void cleanUp() throws Exception {
		new JMXBundleDeployer().uninstall(_fooApiJarBSN);
		new JMXBundleDeployer().uninstall(_fooServiceJarBSN);
		new JMXBundleDeployer().uninstall(_testJarBSN);

		BladeCLIUtil.uninstallBundle(_springmvcbundleId);
	}

	@Deployment
	public static JavaArchive create() throws Exception {
		final File fooApiJar = new File(System.getProperty("fooApiJarFile"));
		final File fooServiceJar = new File(
			System.getProperty("fooServiceJarFile"));
		final File fooWebJar = new File(System.getProperty("fooWebJarFile"));
		final File jarFile = new File(System.getProperty("jarFile"));
		final File springmvcPortletWar = new File(
			System.getProperty("springmvcPortletWarFile"));

		new JMXBundleDeployer().deploy(_fooApiJarBSN, fooApiJar);
		new JMXBundleDeployer().deploy(_fooServiceJarBSN, fooServiceJar);
		new JMXBundleDeployer().deploy(_testJarBSN, jarFile);

		_springmvcbundleId = BladeCLIUtil.installBundle(springmvcPortletWar);

		BladeCLIUtil.startBundle(_springmvcbundleId);

		return ShrinkWrap.createFromZipFile(JavaArchive.class, fooWebJar);
	}

	@Test
	public void testSpringBasic() throws InterruptedException, PortalException {
		_webDriver.get(_portletURL.toExternalForm());

		String url = _webDriver.getCurrentUrl();

		BladeSampleFunctionalActionUtil.implicitWait(_webDriver);

		String windowHandler = _webDriver.getWindowHandle();

		Assert.assertTrue(
			"Service Builder Table does not contain aDeletableEntry" +
				_table.getText(),
			_table.getText().contains("aDeletableEntry"));

		Assert.assertTrue(
			"Liferay Icon menu is not visible",
			BladeSampleFunctionalActionUtil.isVisible(_webDriver, _lfrIconMenu));

		BladeSampleFunctionalActionUtil.mouseOverClick(_webDriver, _lfrIconMenu);

		Assert.assertTrue(
			"Liferay Menu Delete is not visible",
			BladeSampleFunctionalActionUtil.isVisible(_webDriver, _lfrMenuDelete));

		BladeSampleFunctionalActionUtil.mouseOverClick(_webDriver, _lfrMenuDelete);

		Assert.assertTrue(
			"Alert is not present!",
			BladeSampleFunctionalActionUtil.isAlertPresent(_webDriver));

		_webDriver.switchTo().window(windowHandler);

		Thread.sleep(5000);

		_webDriver.navigate().to(url);

		_webDriver.navigate().refresh();

		Assert.assertTrue(
			_table.getText(), !_table.getText().contains("aDeletableEntry"));
	}

	private static String _fooApiJarBSN = "blade.servicebuilder.api";
	private static String _fooServiceJarBSN = "blade.servicebuilder.svc";
	private static String _springmvcbundleId;
	private static String _testJarBSN = "blade.servicebuilder.test";

	@FindBy(xpath = "//span[@class='lfr-btn-label']")
	private WebElement _addButton;

	@FindBy(xpath = "//input[contains(@id,'field1')]")
	private WebElement _field1Form;

	@FindBy(xpath = "//input[contains(@id,'field5')]")
	private WebElement _field5Form;

	@FindBy(xpath = "//div[contains(@id,'bladespringmvc_WAR_bladespringmvc')]/table//..//tr/td[6]")
	private WebElement _firstRowField5;

	@FindBy(xpath = "//div[@class='btn-group lfr-icon-menu']/a")
	private WebElement _lfrIconMenu;

	@FindBy(xpath = "//ul[contains(@class,'dropdown-menu')]/li[2]/a[contains(.,'Delete')]")
	private WebElement _lfrMenuDelete;

	@FindBy(xpath = "//ul[contains(@class,'dropdown-menu')]/li[1]/a[contains(.,'Edit')]")
	private WebElement _lfrMenuEdit;

	@PortalURL("bladespringmvc_WAR_bladespringmvc")
	private URL _portletURL;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement _saveButton;

	@FindBy(xpath = "//div[contains(@id,'bladespringmvc_WAR_bladespringmvc')]/table//..//tr[2]/td[6]")
	private WebElement _secondRowField5;

	@FindBy(xpath = "//table[contains(@data-searchcontainerid,'foosSearchContainer')]")
	private WebElement _table;

	@Drone
	private WebDriver _webDriver;

}