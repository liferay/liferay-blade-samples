/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.blade.samples.servicebuilder.service.test;

import aQute.remote.util.JMXBundleDeployer;

import com.liferay.blade.samples.servicebuilder.model.Foo;
import com.liferay.blade.samples.servicebuilder.service.FooLocalServiceUtil;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.io.File;

import java.util.Date;
import java.util.List;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Lawrence Lee
 */
@RunWith(Arquillian.class)
public class BladeServiceBuilderIntegrationTest {

	@AfterClass
	public static void cleanUpDependencies() throws Exception {
		new JMXBundleDeployer().uninstall(_fooApiJarBSN);
	}

	@Deployment
	public static JavaArchive create() throws Exception {
		final File jarFile = new File(System.getProperty("jarFile"));

		final File fooApiJar = new File(System.getProperty("fooApiJarFile"));

		new JMXBundleDeployer().deploy(_fooApiJarBSN, fooApiJar);

		return ShrinkWrap.createFromZipFile(JavaArchive.class, jarFile);
	}

	@Before
	public void setUp() throws Exception {
		_cleanupFoos();
	}

	@After
	public void tearDown() {
		_cleanupFoos();
	}

	@Test
	public void testCreateFoo() throws PortalException {
		Foo foo = FooLocalServiceUtil.createFoo(
			CounterLocalServiceUtil.increment());

		foo.setField1("createFooEntryField1");
		foo.setField2(true);
		foo.setField3(1);

		Date createDate = new Date();

		foo.setField4(createDate);

		foo.setField5("createFooEntryField5");
		foo.isNew();

		Foo fooEntry = FooLocalServiceUtil.addFoo(foo);

		Assert.assertTrue(
			"Expected createFooEntryField1, but saw " + fooEntry.getField1(),
			fooEntry.getField1().contentEquals("createFooEntryField1"));
		Assert.assertTrue(
			"Expected true, but saw " + fooEntry.getField2(),
			fooEntry.getField2() == true);
		Assert.assertTrue(
			"Expected \"1\", but saw " + fooEntry.getField3(),
			fooEntry.getField3() == 1);
		Assert.assertTrue(
			"Expected " + createDate + "but saw, " + fooEntry.getField4(),
			fooEntry.getField4().compareTo(createDate) == 0);
		Assert.assertTrue(
			"Expected createFooEntryField5, but saw " + fooEntry.getField5(),
			fooEntry.getField5().contentEquals("createFooEntryField5"));
	}

	@Test
	public void testDeleteFoo() throws PortalException {
		long id = CounterLocalServiceUtil.increment();

		Foo foo = FooLocalServiceUtil.createFoo(id);

		foo.setField1("deleteFooEntryField1");
		foo.setField2(false);
		foo.setField3(2);

		Date createDate = new Date();

		foo.setField4(createDate);

		foo.setField5("deleteFooEntryField5");
		foo.isNew();

		Foo fooEntry = FooLocalServiceUtil.addFoo(foo);

		FooLocalServiceUtil.deleteFoo(fooEntry);

		Assert.assertTrue(
			"Expected \"0\", but saw " + FooLocalServiceUtil.getFoosCount(),
			FooLocalServiceUtil.getFoosCount() == 0);
	}

	@Test
	public void testUpdateFoo() throws PortalException {
		long id = CounterLocalServiceUtil.increment();

		Foo foo = FooLocalServiceUtil.createFoo(id);

		foo.setField1("updateFooEntryField1");
		foo.setField2(true);
		foo.setField3(3);

		Date createDate = new Date();

		foo.setField4(createDate);

		foo.setField5("updateFooEntryField5");
		foo.isNew();

		Foo fooEntry = FooLocalServiceUtil.addFoo(foo);

		fooEntry.setField1("updatedFooEntryField1");
		fooEntry.setField2(false);
		fooEntry.setField3(4);

		Date newDate = new Date();

		fooEntry.setField4(newDate);

		fooEntry.setField5("updatedFooEntryField5");

		FooLocalServiceUtil.updateFoo(foo);

		Assert.assertTrue(
			"Expected updatedFooEntryField1, but saw " + fooEntry.getField1(),
			fooEntry.getField1().contentEquals("updatedFooEntryField1"));
		Assert.assertTrue(
			"Expected false, but saw " + fooEntry.getField2(),
			fooEntry.getField2() == false);
		Assert.assertTrue(
			"Expected \"4\", but saw " + fooEntry.getField3(),
			fooEntry.getField3() == 4);
		Assert.assertTrue(
			"Expected " + newDate + "but saw, " + fooEntry.getField4(),
			fooEntry.getField4().compareTo(newDate) == 0);
		Assert.assertTrue(
			"Expected updatedFooEntryField5, but saw " + fooEntry.getField5(),
			fooEntry.getField5().contentEquals("updatedFooEntryField5"));
	}

	private void _cleanupFoos() {
		List<Foo> foos = FooLocalServiceUtil.getFoos(-1, -1);

		if (!foos.isEmpty()) {
			for (Foo foo : foos) {
				FooLocalServiceUtil.deleteFoo(foo);
			}
		}
	}

	private static String _fooApiJarBSN = "foo-api";

}