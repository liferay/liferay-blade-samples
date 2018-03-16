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

package com.liferay.blade.samples.npm.react.test;

import com.liferay.arquillian.portal.annotation.PortalURL;
import com.liferay.blade.sample.test.functional.utils.BladeSampleFunctionalActionUtil;

import java.io.File;

import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import org.junit.Assert;
import org.junit.Ignore;
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
public class BladeReactNpmPortletTest {

	@Deployment
	public static JavaArchive create() throws Exception {
		final File jarFile = new File(
			System.getProperty("reactNpmPortletJarFile"));

		return ShrinkWrap.createFromZipFile(JavaArchive.class, jarFile);
	}

	@Ignore //react doesn't seem to work with phantomjs, only firefox
	@Test
	public void testBladeReactNpm() throws InterruptedException {
		_webDriver.get(_portletURL.toExternalForm());

		Assert.assertTrue(
			"Portlet was not deployed",
			BladeSampleFunctionalActionUtil.isVisible(
				_webDriver, _bladeNpmReactPortlet));

		BladeSampleFunctionalActionUtil.mouseOverClick(
			_webDriver, _bladeNpmReactPortlet);

		Assert.assertTrue(
			"Expected: React Portlet, but saw: " + _portletTitle.getText(),
			_portletTitle.getText().contentEquals("React Portlet"));

		BladeSampleFunctionalActionUtil.mouseOverClick(
			_webDriver, _portletBody);

		Thread.sleep(1000);

		Assert.assertTrue(
			_webDriver.getPageSource(),
			_webDriver.getPageSource().contains("game-board"));

		BladeSampleFunctionalActionUtil.mouseOverClick(
			_webDriver, _portletGameBoardBoardRow);

		Thread.sleep(500);

		Assert.assertTrue(
			"Expected Next player: O, but saw: " + _portletGameBoardStatus.getText(),
			_portletGameBoardStatus.getText().contentEquals("Next player: O"));
	}

	@FindBy(xpath = "//section[contains(@id,'ReactPortlet')]")
	private WebElement _bladeNpmReactPortlet;

	@FindBy(xpath = "//section[contains(@id,'ReactPortlet')]//..//div[@class='portlet-body']")
	private WebElement _portletBody;

	@FindBy(xpath = "//div[@class='game-board']//..//button")
	private WebElement _portletGameBoardBoardRow;

	@FindBy(xpath = "//div[@class='game-board']/div/div")
	private WebElement _portletGameBoardStatus;

	@FindBy(xpath = "//section[contains(@id,'ReactPortlet')]/div/h2")
	private WebElement _portletTitle;

	@PortalURL("com_liferay_blade_npm_react_portlet_ReactPortlet")
	private URL _portletURL;

	@Drone
	private WebDriver _webDriver;

}