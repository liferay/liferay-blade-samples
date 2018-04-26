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

package com.liferay.blade.samples.language.web.test;

import aQute.remote.util.JMXBundleDeployer;

import com.liferay.arquillian.portal.annotation.PortalURL;
import com.liferay.blade.sample.test.functional.utils.BladeSampleFunctionalActionUtil;
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
 * @author Lawrence Lee
 */
@RunAsClient
@RunWith(Arquillian.class)
public class BladeLanguageWebTest {

	@AfterClass
	public static void cleanUpDependencies() throws Exception {
		new JMXBundleDeployer().uninstall(_languageJarBSN);
	}

	@Deployment
	public static JavaArchive create() throws Exception {
		final File jarFile = new File(System.getProperty("languageWebJarFile"));

		final File languageJar = new File(
			System.getProperty("languageJarFile"));

		new JMXBundleDeployer().deploy(_languageJarBSN, languageJar);

		return ShrinkWrap.createFromZipFile(JavaArchive.class, jarFile);
	}

	@Test
	public void testBladeSamplesLanguage() throws PortalException {
		_webDriver.get(_portletURL.toExternalForm());

		BladeSampleFunctionalActionUtil.implicitWait(_webDriver);

		Assert.assertTrue(
			"Portlet was not deployed",
			 _bladeSampleLanguagePortlet.isDisplayed());
		Assert.assertTrue(
			_languageKeyFirst.getText(),
			_languageKeyFirst.getText().equals(
				"Hello from BLADE Language Web!"));
		Assert.assertTrue(
			_languageKeySecond.getText(),
			_languageKeySecond.getText().equals(
				"Hello from the BLADE Language Module!"));
		Assert.assertTrue(
			_languageKeyThird.getText(),
			_languageKeyThird.getText().equals(
				"I have overridden the key from BLADE Language Module!"));
	}

	private static String _languageJarBSN = "com.liferay.blade.language";

	@FindBy(xpath = "//div[contains(@id,'_com_liferay_blade_samples_language_web')]")
	private WebElement _bladeSampleLanguagePortlet;

	@FindBy(xpath = "//div[@class='portlet-body']/p[1]")
	private WebElement _languageKeyFirst;

	@FindBy(xpath = "//div[@class='portlet-body']/p[2]")
	private WebElement _languageKeySecond;

	@FindBy(xpath = "//div[@class='portlet-body']/p[3]")
	private WebElement _languageKeyThird;

	@PortalURL("com_liferay_blade_samples_language_web")
	private URL _portletURL;

	@Drone
	private WebDriver _webDriver;

}