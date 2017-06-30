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

package com.liferay.blade.samples.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import aQute.bnd.osgi.Jar;

import aQute.lib.io.IO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;

import java.nio.file.Files;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import okhttp3.Request.Builder;

import org.gradle.testkit.runner.BuildTask;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Lawrence Lee
 */
public class BladeSamplesTest {

	@BeforeClass
	public static void setUpClass() throws Exception {
		OkHttpClient client = new OkHttpClient();
		Request request = new Builder().url("http://localhost:8080").build();

		boolean pingSucceeded = false;

		while (!pingSucceeded) {
			try {
				client.newCall(request).execute();
				pingSucceeded = true;
				break;
			}
			catch (Exception e) {
			}

			Thread.sleep(100);
		}
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		if (BladeCLIUtil.bladeJar.exists()) {
			IO.delete(BladeCLIUtil.bladeJar);
			assertFalse(BladeCLIUtil.bladeJar.exists());
		}
	}

	@Before
	public void setUp() throws Exception {
		_testDir = Files.createTempDirectory("bladetest").toFile();

		_testDir.deleteOnExit();
	}

	@After
	public void tearDown() throws Exception {
		if (_testDir.exists()) {
			IO.delete(_testDir);
			assertFalse(_testDir.exists());
		}
	}

	@Test
	public void testAllBladeSamples() throws Exception {
		List<String> bladeSampleOutputFiles = new ArrayList<>();
		Map<String, String> bundleIDAllMap = new HashMap<>();
		Map<String, String> bundleIDStartMap = new HashMap<>();

		String[] sampleOutputFiles = System.getProperty(
			"moduleOutputPaths").split(",");

		for (String file : sampleOutputFiles) {
			bladeSampleOutputFiles.add(file);
		}

		for (String sampleBundleFile : bladeSampleOutputFiles) {
			String installBundleOutput = BladeCLIUtil.installBundle(
				new File(sampleBundleFile));

			String printFileName = new File(sampleBundleFile).getName();

			bundleIDAllMap.put(installBundleOutput, printFileName);

			try (Jar jar = new Jar(sampleBundleFile, sampleBundleFile)) {
				if (jar.getManifest().getMainAttributes().getValue(
						"Fragment-Host") == null) {

					bundleIDStartMap.put(installBundleOutput, printFileName);
				}
			}
		}

		for (String startBundleIO : bundleIDStartMap.keySet()) {
			BladeCLIUtil.startBundle(startBundleIO);
		}

		for (String allBundleID : bundleIDAllMap.keySet()) {
			BladeCLIUtil.uninstallBundle(allBundleID);
		}
	}

	@Test
	public void testControlMenuEntryGradleTemplates() throws Exception {
		File projectPath = BladeCLIUtil.createProject(
			_testDir, "control-menu-entry", "helloworld");

		BuildTask buildtask = GradleRunnerUtil.executeGradleRunner(
			projectPath, "build");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildtask);

		File buildOutput = new File(
			projectPath + "/build/libs/helloworld-1.0.0.jar");

		assertTrue(buildOutput.exists());

		String bundleID = BladeCLIUtil.installBundle(buildOutput);

		BladeCLIUtil.startBundle(bundleID);

		BladeCLIUtil.uninstallBundle(bundleID);
	}

	@Test
	public void testMVCPortletGradleTemplates() throws Exception {
		File projectPath = BladeCLIUtil.createProject(
			_testDir, "mvc-portlet", "helloworld");

		BuildTask buildtask = GradleRunnerUtil.executeGradleRunner(
			projectPath, "build");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildtask);

		File buildOutput = new File(
			projectPath + "/build/libs/helloworld-1.0.0.jar");

		assertTrue(buildOutput.exists());

		String bundleID = BladeCLIUtil.installBundle(buildOutput);

		BladeCLIUtil.startBundle(bundleID);

		BladeCLIUtil.uninstallBundle(bundleID);
	}

	@Test
	public void testPanelAppGradleTemplates() throws Exception {
		File projectPath = BladeCLIUtil.createProject(
			_testDir, "panel-app", "helloworld");

		BuildTask buildtask = GradleRunnerUtil.executeGradleRunner(
			projectPath, "build");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildtask);

		File buildOutput = new File(
			projectPath + "/build/libs/helloworld-1.0.0.jar");

		assertTrue(buildOutput.exists());

		String bundleID = BladeCLIUtil.installBundle(buildOutput);

		BladeCLIUtil.startBundle(bundleID);

		BladeCLIUtil.uninstallBundle(bundleID);
	}

	@Test
	public void testPortletGradleTemplates() throws Exception {
		File projectPath = BladeCLIUtil.createProject(
			_testDir, "portlet", "helloworld");

		BuildTask buildtask = GradleRunnerUtil.executeGradleRunner(
			projectPath, "build");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildtask);

		File buildOutput = new File(
			projectPath + "/build/libs/helloworld-1.0.0.jar");

		assertTrue(buildOutput.exists());

		String bundleID = BladeCLIUtil.installBundle(buildOutput);

		BladeCLIUtil.startBundle(bundleID);

		BladeCLIUtil.uninstallBundle(bundleID);
	}

	@Test
	public void testPortletProviderGradleTemplates() throws Exception {
		File projectPath = BladeCLIUtil.createProject(
			_testDir, "portlet-provider", "helloworld");

		BuildTask buildtask = GradleRunnerUtil.executeGradleRunner(
			projectPath, "build");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildtask);

		File buildOutput = new File(
			projectPath + "/build/libs/helloworld-1.0.0.jar");

		assertTrue(buildOutput.exists());

		String bundleID = BladeCLIUtil.installBundle(buildOutput);

		BladeCLIUtil.startBundle(bundleID);

		BladeCLIUtil.uninstallBundle(bundleID);
	}

	@Test
	public void testServiceBuilderBladeSample() throws Exception {
		File projectPath = new File(
			System.getProperty("user.dir")).getParentFile().getParentFile();

		File serviceProperties = new File(
			projectPath,
			"apps/service-builder/foo-service/src/main/resources" +
				"/service.properties");

		File servicePropertiesBackup = new File("service.properties.bak");

		IO.copy(serviceProperties, servicePropertiesBackup);

		BuildTask buildService = GradleRunnerUtil.executeGradleRunner(
			projectPath, ":apps:service-builder:foo-service:buildService");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildService);

		IO.copy(servicePropertiesBackup, serviceProperties);
		IO.delete(servicePropertiesBackup);

		BuildTask cleanTask = GradleRunnerUtil.executeGradleRunner(
			projectPath, ":apps:service-builder:foo-api:clean");

		GradleRunnerUtil.verifyGradleRunnerOutput(cleanTask);

		BuildTask buildApiTask = GradleRunnerUtil.executeGradleRunner(
			projectPath, ":apps:service-builder:foo-api:build");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildApiTask);

		cleanTask = GradleRunnerUtil.executeGradleRunner(
			projectPath, ":apps:service-builder:foo-service:clean");

		GradleRunnerUtil.verifyGradleRunnerOutput(cleanTask);

		BuildTask buildServiceTask = GradleRunnerUtil.executeGradleRunner(
			projectPath, ":apps:service-builder:foo-service:assemble");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildServiceTask);

		File buildApiOutput = new File(
			projectPath + "/apps/service-builder/foo-api/build/libs" +
				"/foo-api-1.0.0.jar");
		File buildServiceOutput = new File(
			projectPath + "/apps/service-builder/foo-service/build/libs" +
				"/foo-service-1.0.0.jar");

		assertTrue(buildApiOutput.exists());
		assertTrue(buildServiceOutput.exists());

		String bundleIDApi = BladeCLIUtil.installBundle(buildApiOutput);
		String bundleIDService = BladeCLIUtil.installBundle(buildServiceOutput);

		BladeCLIUtil.startBundle(bundleIDApi);
		BladeCLIUtil.startBundle(bundleIDService);

		BladeCLIUtil.uninstallBundle(bundleIDApi, bundleIDService);
	}

	@Test
	public void testServiceBuilderGradleTemplate() throws Exception {
		File projectPath = BladeCLIUtil.createProject(
			_testDir, "service-builder", "guestbook", "-p",
			"com.liferay.docs.guestbook");

		BuildTask buildService = GradleRunnerUtil.executeGradleRunner(
			projectPath, "buildService");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildService);

		BuildTask buildtask = GradleRunnerUtil.executeGradleRunner(
			projectPath, "build");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildtask);

		File buildApiOutput = new File(
			projectPath + "/guestbook-api/build/libs" +
				"/com.liferay.docs.guestbook.api-1.0.0.jar");
		File buildServiceOutput = new File(
			projectPath + "/guestbook-service/build/libs" +
				"/com.liferay.docs.guestbook.service-1.0.0.jar");

		assertTrue(buildApiOutput.exists());
		assertTrue(buildServiceOutput.exists());

		String bundleIDApi = BladeCLIUtil.installBundle(buildApiOutput);
		String bundleIDService = BladeCLIUtil.installBundle(buildServiceOutput);

		BladeCLIUtil.startBundle(bundleIDApi);
		BladeCLIUtil.startBundle(bundleIDService);

		BladeCLIUtil.uninstallBundle(bundleIDApi, bundleIDService);
	}

	@Test
	public void testServiceGradleTemplate() throws Exception {
		BladeCLIUtil.createProject(
			_testDir, "service", "helloworld", "-s",
			"com.liferay.portal.kernel.events.LifecycleAction", "-c",
			"FooAction");

		File projectPath = new File(_testDir + "/helloworld");

		File file = new File(
			projectPath + "/src/main/java/helloworld/FooAction.java");

		List<String> lines = new ArrayList<>();
		String line = null;

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			while ((line = reader.readLine()) != null) {
				lines.add(line);

				if (line.equals(
						"import com.liferay.portal.kernel.events." +
							"LifecycleAction;"))
					{

					lines.add(
						"import com.liferay.portal.kernel.events." +
							"LifecycleEvent;");
					lines.add(
						"import com.liferay.portal.kernel.events." +
							"ActionException;");
				}

				if (line.equals(
						"public class FooAction implements LifecycleAction {"))
					{

					String s =
						new StringBuilder().append("@Override\n").
							append(
								"public void processLifecycleEvent(" +
									"LifecycleEvent lifecycleEvent)\n").
							append("throws ActionException {\n").
							append(
								"System.out.println(\"login.event.pre=\" +" +
									" lifecycleEvent);\n").
							append("}\n").toString();

					lines.add(s);
				}
			}
		}

		try (Writer writer = new FileWriter(file)) {
			for (String string : lines) {
				writer.write(string + "\n");
			}
		}

		BuildTask buildtask = GradleRunnerUtil.executeGradleRunner(
			projectPath, "build");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildtask);

		File buildOutput = new File(
			projectPath + "/build/libs/helloworld-1.0.0.jar");

		assertTrue(buildOutput.exists());

		String bundleID = BladeCLIUtil.installBundle(buildOutput);

		BladeCLIUtil.startBundle(bundleID);

		BladeCLIUtil.uninstallBundle(bundleID);
	}

	@Test
	public void testServiceWrapperGradleTemplate() throws Exception {
		File projectPath = BladeCLIUtil.createProject(
			_testDir, "service-wrapper", "serviceoverride", "-s",
			"com.liferay.portal.kernel.service.UserLocalServiceWrapper");

		BuildTask buildtask = GradleRunnerUtil.executeGradleRunner(
			projectPath, "build");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildtask);

		File buildOutput = new File(
			projectPath + "/build/libs/serviceoverride-1.0.0.jar");

		assertTrue(buildOutput.exists());

		String bundleID = BladeCLIUtil.installBundle(buildOutput);

		BladeCLIUtil.startBundle(bundleID);

		BladeCLIUtil.uninstallBundle(bundleID);
	}

	private File _testDir;

}