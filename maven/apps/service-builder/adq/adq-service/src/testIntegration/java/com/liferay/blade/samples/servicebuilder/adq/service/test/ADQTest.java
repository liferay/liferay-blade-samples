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

package com.liferay.blade.samples.servicebuilder.adq.service.test;

import aQute.remote.util.JMXBundleDeployer;

import com.liferay.blade.samples.servicebuilder.adq.model.Bar;
import com.liferay.blade.samples.servicebuilder.adq.service.BarLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.io.File;

import java.util.Date;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Jesse Rao
 */
@RunWith(Arquillian.class)
public class ADQTest {

	@AfterClass
	public static void cleanUpDependencies() throws Exception {
		new JMXBundleDeployer().uninstall(_adqApiJarBSN);
	}

	@Deployment
	public static JavaArchive create() throws Exception {
		final File jarFile = new File(System.getProperty("jarFile"));

		final File adqApiJar = new File(System.getProperty("adqApiJarFile"));

		new JMXBundleDeployer().deploy(_adqApiJarBSN, adqApiJar);

		return ShrinkWrap.createFromZipFile(JavaArchive.class, jarFile);
	}

	@Test
	public void testMassUpdate() throws PortalException {

		// Set up

		Bar bar1 = BarLocalServiceUtil.createBar(
			CounterLocalServiceUtil.increment());

		bar1.setField1(_BAR1);
		bar1.setField2(false);
		bar1.setField3(0);
		bar1.setField4(_DATE);
		bar1.setField5(_TEST);

		bar1 = BarLocalServiceUtil.addBar(bar1);

		long bar1Id = bar1.getBarId();

		Bar bar2 = BarLocalServiceUtil.createBar(
			CounterLocalServiceUtil.increment());

		bar2.setField1(_BAR2);
		bar2.setField2(false);
		bar2.setField3(99);
		bar2.setField4(_DATE);
		bar2.setField5(_TEST);

		bar2 = BarLocalServiceUtil.addBar(bar2);

		long bar2Id = bar2.getBarId();

		Bar bar3 = BarLocalServiceUtil.createBar(
			CounterLocalServiceUtil.increment());

		bar3.setField1(_BAR3);
		bar3.setField2(false);
		bar3.setField3(100);
		bar3.setField4(_DATE);
		bar3.setField5(_TEST);

		bar3 = BarLocalServiceUtil.addBar(bar3);

		long bar3Id = bar3.getBarId();

		Bar bar4 = BarLocalServiceUtil.createBar(
			CounterLocalServiceUtil.increment());

		bar4.setField1(_BAR4);
		bar4.setField2(false);
		bar4.setField3(101);
		bar4.setField4(_DATE);
		bar4.setField5(_TEST);

		bar4 = BarLocalServiceUtil.addBar(bar4);

		long bar4Id = bar4.getBarId();

		Bar bar5 = BarLocalServiceUtil.createBar(
			CounterLocalServiceUtil.increment());

		bar5.setField1(_BAR5);
		bar5.setField2(false);
		bar5.setField3(200);
		bar5.setField4(_DATE);
		bar5.setField5(_TEST);

		bar5 = BarLocalServiceUtil.addBar(bar5);

		long bar5Id = bar5.getBarId();

		// Perform 1st mass update and make assertions

		BarLocalServiceUtil.massUpdate();

		bar1 = BarLocalServiceUtil.getBar(bar1Id);

		Assert.assertTrue(
			"Expected " + 1 + ", but saw " + bar1.getField3(),
			bar1.getField3() == 1);

		bar2 = BarLocalServiceUtil.getBar(bar2Id);

		Assert.assertTrue(
			"Expected " + 100 + ", but saw " + bar2.getField3(),
			bar2.getField3() == 100);

		bar3 = BarLocalServiceUtil.getBar(bar3Id);

		Assert.assertTrue(
			"Expected " + 100 + ", but saw " + bar3.getField3(),
			bar3.getField3() == 100);

		bar4 = BarLocalServiceUtil.getBar(bar4Id);

		Assert.assertTrue(
			"Expected " + 101 + ", but saw " + bar4.getField3(),
			bar4.getField3() == 101);

		bar5 = BarLocalServiceUtil.getBar(bar5Id);

		Assert.assertTrue(
			"Expected " + 200 + ", but saw " + bar5.getField3(),
			bar5.getField3() == 200);

		// Step 3: Perform 2nd mass update and make assertions

		BarLocalServiceUtil.massUpdate();

		bar1 = BarLocalServiceUtil.getBar(bar1Id);

		Assert.assertTrue(
			"Expected " + 1 + ", but saw " + bar1.getField3(),
			bar1.getField3() == 2);

		bar2 = BarLocalServiceUtil.getBar(bar2Id);

		Assert.assertTrue(
			"Expected " + 100 + ", but saw " + bar2.getField3(),
			bar2.getField3() == 100);

		bar3 = BarLocalServiceUtil.getBar(bar3Id);

		Assert.assertTrue(
			"Expected " + 100 + ", but saw " + bar3.getField3(),
			bar3.getField3() == 100);

		bar4 = BarLocalServiceUtil.getBar(bar4Id);

		Assert.assertTrue(
			"Expected " + 101 + ", but saw " + bar4.getField3(),
			bar4.getField3() == 101);

		bar5 = BarLocalServiceUtil.getBar(bar5Id);

		Assert.assertTrue(
			"Expected " + 200 + ", but saw " + bar5.getField3(),
			bar5.getField3() == 200);
	}

	private static final String _BAR1 = "bar1";

	private static final String _BAR2 = "bar2";

	private static final String _BAR3 = "bar3";

	private static final String _BAR4 = "bar4";

	private static final String _BAR5 = "bar5";

	private static final Date _DATE = new Date();

	private static final String _TEST = "test";

	private static String _adqApiJarBSN = "com.liferay.blade.adq.api";

}