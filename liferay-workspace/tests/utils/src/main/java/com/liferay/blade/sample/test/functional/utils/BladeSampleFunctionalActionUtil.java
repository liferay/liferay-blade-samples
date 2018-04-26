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

package com.liferay.blade.sample.test.functional.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Lawrence Lee
 */
public class BladeSampleFunctionalActionUtil {

	public static void customClick(WebDriver webDriver, WebElement webElement) {
		Actions action = new Actions(webDriver);

		Actions actionMoveTo = action.moveToElement(webElement);

		Action buildActionMoveTo = actionMoveTo.build();

		buildActionMoveTo.perform();

		WebDriverWait wait = new WebDriverWait(webDriver, 30);

		WebElement element = wait.until(
			ExpectedConditions.elementToBeClickable(webElement));

		element.click();
	}

	public static void mouseOverClick(WebDriver webDriver, WebElement webElement) {
		Actions action = new Actions(webDriver);

		Actions actionMoveTo = action.moveToElement(webElement);

		Actions actionOffset = actionMoveTo.moveByOffset(1, 1);

		Actions revertOffset = actionOffset.moveByOffset(-1, -1);

		Actions clickElement = revertOffset.click();

		clickElement.perform();
	}

	public static void twoPointClick(WebDriver webDriver, WebElement webElement, WebElement weblElement2) {
		Actions action = new Actions(webDriver);

		Actions actionBody = action.moveToElement(weblElement2);

		Actions actionBodyClick = actionBody.click();

		Actions actionMoveTo = actionBodyClick.moveToElement(webElement);

		Actions actionClick = actionMoveTo.click();

		Action buildActionbuild = actionClick.build();

		buildActionbuild.perform();
	}

	public static void twoPointDoubleClick(WebDriver webDriver, WebElement webElement, WebElement weblElement2) {
		Actions action = new Actions(webDriver);

		Actions actionBody = action.moveToElement(weblElement2);

		Actions actionBodyClick = actionBody.doubleClick();

		Actions actionMoveTo = actionBodyClick.moveToElement(webElement);

		Actions actionClick = actionMoveTo.doubleClick();

		Action buildActionbuild = actionClick.build();

		buildActionbuild.perform();
	}

	public static WebDriver implicitWait(WebDriver webDriver) {
		Options webDriverOptions = webDriver.manage();

		Timeouts timeouts = webDriverOptions.timeouts();

		timeouts.implicitlyWait(30, TimeUnit.SECONDS);

		return webDriver;
	}

	public static boolean isClickable(
		WebDriver webDriver, WebElement webelement) {

		WebDriverWait webDriverWait = new WebDriverWait(webDriver, 30);

		try {
			webDriverWait.until(
				ExpectedConditions.elementToBeClickable(webelement));

			return true;
		}
		catch (org.openqa.selenium.TimeoutException te) {
			return false;
		}
	}

	public static boolean isPageLoaded(WebDriver webDriver, String string) {
		WebDriverWait webDriverWait = new WebDriverWait(webDriver, 30);

		try {
			webDriverWait.until(ExpectedConditions.urlMatches(string));

			return true;
		}
		catch (org.openqa.selenium.TimeoutException te) {
			return false;
		}
	}

	public static boolean isTextPresent(
		WebDriver webDriver, WebElement webelement, String string) {

		WebDriverWait webDriverWait = new WebDriverWait(webDriver, 30);

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

	public static boolean isVisible(
		WebDriver webDriver, WebElement webelement) {

		WebDriverWait webDriverWait = new WebDriverWait(webDriver, 30);

		try {
			webDriverWait.until(ExpectedConditions.visibilityOf(webelement));

			return true;
		}
		catch (org.openqa.selenium.TimeoutException te) {
			return false;
		}
	}

	public static String getPortalVersion() {
		String portalVersion = System.getProperty("portalVersion");

		if (portalVersion.contains("7.0")) {
			return "7.0";
		}

		if (portalVersion.contains("7.1")) {
			return "7.1";
		}

		if (portalVersion.contains("master")) {
			return "master";
		}

		else {
			return portalVersion;
		}
	}

	public static String getTextToLowerCase(WebElement webelement) {
		String text = webelement.getText();

		String textToLowerCase = text.toLowerCase();

		return textToLowerCase;
	}

}