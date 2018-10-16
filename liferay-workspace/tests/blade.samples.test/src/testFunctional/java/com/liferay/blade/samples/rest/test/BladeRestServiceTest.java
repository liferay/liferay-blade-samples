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

package com.liferay.blade.samples.rest.test;

import com.liferay.arquillian.portal.annotation.PortalURL;
import com.liferay.blade.sample.test.functional.utils.BladeSampleFunctionalActionUtil;

import java.io.File;

import java.net.MalformedURLException;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.openqa.selenium.WebDriver;

/**
 * @author Lawrence Lee
 */
@RunAsClient
@RunWith(Arquillian.class)
public class BladeRestServiceTest {

	@Deployment
	public static JavaArchive create() throws Exception {
		final File jarFile = new File(System.getProperty("restJarFile"));

		return ShrinkWrap.createFromZipFile(JavaArchive.class, jarFile);
	}

	@Test
	public void testBladeRestService() throws MalformedURLException {
		_webDriver.get(_portletURL.toExternalForm());

		URL url = new URL("http://localhost:8080/o/users/list");

		BladeSampleFunctionalActionUtil.implicitWait(_webDriver);

		_webDriver.get(url.toExternalForm());

		String pageContent = _webDriver.getPageSource();

		Assert.assertTrue(pageContent, pageContent.contains("Test Test"));
	}

	@PortalURL("com_liferay_hello_world_web_portlet_HelloWorldPortlet")
	private URL _portletURL;

	@Drone
	private WebDriver _webDriver;

}