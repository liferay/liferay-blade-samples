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

package com.liferay.blade.samples.integration.test;

import aQute.bnd.osgi.Jar;

import aQute.lib.io.IO;

import com.liferay.blade.sample.test.functional.utils.BladeSampleFunctionalActionUtil;
import com.liferay.blade.samples.integration.test.utils.BladeCLIUtil;
import com.liferay.blade.samples.integration.test.utils.GradleRunnerUtil;

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
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import okhttp3.Request.Builder;

import org.gradle.testkit.runner.BuildTask;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Assume;
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
			Assert.assertFalse(BladeCLIUtil.bladeJar.exists());
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
			Assert.assertFalse(_testDir.exists());
		}
	}

	@Test
	public void testAllBladeSamples() throws Exception {
		Assume.assumeTrue(
			BladeSampleFunctionalActionUtil.getPortalVersion().equals("7.0") &&
			!System.getProperty("portalVersion").contains("master"));

		List<String> bladeSampleOutputFiles = new ArrayList<>();
		Map<String, String> bundleIDAllMap = new HashMap<>();
		Map<String, String> bundleIDStartMap = new HashMap<>();

		String[] sampleOutputFiles = System.getProperty(
			"moduleOutputPaths").split(",");

		for (String file : sampleOutputFiles) {
			bladeSampleOutputFiles.add(file);
		}

		for (String sampleBundleFile : bladeSampleOutputFiles) {
			String printFileName;
			String bundleID;

			File file = new File(sampleBundleFile);

			if (file.exists()) {
				printFileName = new File(sampleBundleFile).getName();

				printFileName = printFileName.substring(
					0, printFileName.lastIndexOf('.'));

				bundleID = BladeCLIUtil.installBundle(file);

				bundleIDAllMap.put(bundleID, printFileName);

				if (file.getName().endsWith(".jar")) {
					try (Jar jar =
							new Jar(sampleBundleFile, sampleBundleFile)) {

						Manifest manifest = jar.getManifest();

						Attributes mainAttributes =
							manifest.getMainAttributes();

						if (mainAttributes.getValue("Fragment-Host") == null) {
							bundleIDStartMap.put(bundleID, printFileName);
						}
					}
				}
				else {
					bundleIDStartMap.put(bundleID, printFileName);
				}
			}
		}

		List<String> startErrorList = new ArrayList<>();

		for (String startBundleID : bundleIDStartMap.keySet()) {
			startErrorList.add(BladeCLIUtil.startBundle(startBundleID));
		}

		for (String allBundleID : bundleIDAllMap.keySet()) {
			BladeCLIUtil.uninstallBundle(allBundleID);
		}

		if (startErrorList.toString().toLowerCase().contains("exception")) {
			throw new Exception(startErrorList.toString());
		}
	}

	@Test
	public void testControlMenuEntryGradleTemplates() throws Exception {
		if (!BladeSampleFunctionalActionUtil.getPortalVersion().equals("7.0")) {
			_projectPath = BladeCLIUtil.createProject(
				_testDir, "control-menu-entry", "cmehelloworld", "-v", "7.1");
		}
		else {
			_projectPath = BladeCLIUtil.createProject(
				_testDir, "control-menu-entry", "cmehelloworld");
		}

		BuildTask buildtask = GradleRunnerUtil.executeGradleRunner(
			_projectPath, "build");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildtask);

		File buildOutput = new File(
			_projectPath + "/build/libs/cmehelloworld-1.0.0.jar");

		Assert.assertTrue(buildOutput.exists());

		String bundleID = BladeCLIUtil.installBundle(buildOutput);

		BladeCLIUtil.startBundle(bundleID);

		BladeCLIUtil.uninstallBundle(bundleID);
	}

	@Test
	public void testMVCPortletGradleTemplates() throws Exception {
		_projectPath = BladeCLIUtil.createProject(
			_testDir, "mvc-portlet", "mvcphelloworld");

		BuildTask buildtask = GradleRunnerUtil.executeGradleRunner(
			_projectPath, "build");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildtask);

		File buildOutput = new File(
			_projectPath + "/build/libs/mvcphelloworld-1.0.0.jar");

		Assert.assertTrue(buildOutput.exists());

		String bundleID = BladeCLIUtil.installBundle(buildOutput);

		BladeCLIUtil.startBundle(bundleID);

		BladeCLIUtil.uninstallBundle(bundleID);
	}

	@Test
	public void testPanelAppGradleTemplates() throws Exception {
		_projectPath = BladeCLIUtil.createProject(
			_testDir, "panel-app", "pahelloworld");

		BuildTask buildtask = GradleRunnerUtil.executeGradleRunner(
			_projectPath, "build");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildtask);

		File buildOutput = new File(
			_projectPath + "/build/libs/pahelloworld-1.0.0.jar");

		Assert.assertTrue(buildOutput.exists());

		String bundleID = BladeCLIUtil.installBundle(buildOutput);

		BladeCLIUtil.startBundle(bundleID);

		BladeCLIUtil.uninstallBundle(bundleID);
	}

	@Test
	public void testPortletGradleTemplates() throws Exception {
		_projectPath = BladeCLIUtil.createProject(
			_testDir, "portlet", "phelloworld");

		BuildTask buildtask = GradleRunnerUtil.executeGradleRunner(
			_projectPath, "build");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildtask);

		File buildOutput = new File(
			_projectPath + "/build/libs/phelloworld-1.0.0.jar");

		Assert.assertTrue(buildOutput.exists());

		String bundleID = BladeCLIUtil.installBundle(buildOutput);

		BladeCLIUtil.startBundle(bundleID);

		BladeCLIUtil.uninstallBundle(bundleID);
	}

	@Test
	public void testPortletProviderGradleTemplates() throws Exception {
		if (!BladeSampleFunctionalActionUtil.getPortalVersion().equals("7.0")) {
			_projectPath = BladeCLIUtil.createProject(
				_testDir, "portlet-provider", "pphelloworld", "-v", "7.1");
		}
		else {
			_projectPath = BladeCLIUtil.createProject(
				_testDir, "portlet-provider", "pphelloworld");
		}

		BuildTask buildtask = GradleRunnerUtil.executeGradleRunner(
			_projectPath, "build");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildtask);

		File buildOutput = new File(
			_projectPath + "/build/libs/pphelloworld-1.0.0.jar");

		Assert.assertTrue(buildOutput.exists());

		String bundleID = BladeCLIUtil.installBundle(buildOutput);

		BladeCLIUtil.startBundle(bundleID);

		BladeCLIUtil.uninstallBundle(bundleID);
	}

	@Test
	public void testServiceBuilderBladeSample() throws Exception {
		_projectPath = new File(
			System.getProperty("user.dir")).getParentFile().getParentFile();

		File serviceProperties = new File(
			_projectPath,
			"apps/service-builder/basic/basic-service/src/main/resources" +
				"/service.properties");

		File servicePropertiesBackup = new File("service.properties.bak");

		IO.copy(serviceProperties, servicePropertiesBackup);

		BuildTask buildService = GradleRunnerUtil.executeGradleRunner(
			_projectPath,
			":apps:service-builder:basic:basic-service:buildService");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildService);

		IO.copy(servicePropertiesBackup, serviceProperties);
		IO.delete(servicePropertiesBackup);

		BuildTask cleanTask = GradleRunnerUtil.executeGradleRunner(
			_projectPath, ":apps:service-builder:basic:basic-api:clean");

		GradleRunnerUtil.verifyGradleRunnerOutput(cleanTask);

		BuildTask buildApiTask = GradleRunnerUtil.executeGradleRunner(
			_projectPath, ":apps:service-builder:basic:basic-api:build");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildApiTask);

		cleanTask = GradleRunnerUtil.executeGradleRunner(
			_projectPath, ":apps:service-builder:basic:basic-service:clean");

		GradleRunnerUtil.verifyGradleRunnerOutput(cleanTask);

		BuildTask buildServiceTask = GradleRunnerUtil.executeGradleRunner(
			_projectPath, ":apps:service-builder:basic:basic-service:assemble");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildServiceTask);

		File buildApiOutput = new File(
			_projectPath + "/apps/service-builder/basic/basic-api/build/libs" +
				"/com.liferay.blade.basic.api-1.0.0.jar");
		File buildServiceOutput = new File(
			_projectPath + "/apps/service-builder/basic/basic-service/build/libs" +
				"/com.liferay.blade.basic.service-1.0.0.jar");

		Assert.assertTrue(buildApiOutput.exists());
		Assert.assertTrue(buildServiceOutput.exists());

		String bundleIDApi = BladeCLIUtil.installBundle(buildApiOutput);
		String bundleIDService = BladeCLIUtil.installBundle(buildServiceOutput);

		BladeCLIUtil.startBundle(bundleIDApi);
		BladeCLIUtil.startBundle(bundleIDService);

		BladeCLIUtil.uninstallBundle(bundleIDApi, bundleIDService);
	}

	@Test
	public void testServiceBuilderGradleTemplate() throws Exception {
		if (!BladeSampleFunctionalActionUtil.getPortalVersion().equals("7.0")) {
			_projectPath = BladeCLIUtil.createProject(
				_testDir, "service-builder", "guestbook", "-p",
				"com.liferay.docs.guestbook", "-v", "7.1");
		}
		else {
			_projectPath = BladeCLIUtil.createProject(
				_testDir, "service-builder", "guestbook", "-p",
				"com.liferay.docs.guestbook");
		}

		BuildTask buildService = GradleRunnerUtil.executeGradleRunner(
			_projectPath, "buildService");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildService);

		BuildTask buildtask = GradleRunnerUtil.executeGradleRunner(
			_projectPath, "build");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildtask);

		File buildApiOutput = new File(
			_projectPath + "/guestbook-api/build/libs" +
				"/com.liferay.docs.guestbook.api-1.0.0.jar");
		File buildServiceOutput = new File(
			_projectPath + "/guestbook-service/build/libs" +
				"/com.liferay.docs.guestbook.service-1.0.0.jar");

		Assert.assertTrue(buildApiOutput.exists());
		Assert.assertTrue(buildServiceOutput.exists());

		String bundleIDApi = BladeCLIUtil.installBundle(buildApiOutput);
		String bundleIDService = BladeCLIUtil.installBundle(buildServiceOutput);

		BladeCLIUtil.startBundle(bundleIDApi);
		BladeCLIUtil.startBundle(bundleIDService);

		BladeCLIUtil.uninstallBundle(bundleIDApi, bundleIDService);
	}

	@Test
	public void testServiceGradleTemplate() throws Exception {
		BladeCLIUtil.createProject(
			_testDir, "service", "shelloworld", "-s",
			"com.liferay.portal.kernel.events.LifecycleAction", "-c",
			"FooAction");

		_projectPath = new File(_testDir + "/shelloworld");

		File file = new File(
			_projectPath + "/src/main/java/shelloworld/FooAction.java");

		List<String> lines = new ArrayList<>();
		String line = null;

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			while ((line = reader.readLine()) != null) {
				lines.add(line);

				if (line.startsWith(
						"import com.liferay.portal.kernel.events." +
							"LifecycleAction;")) {

					lines.add(
						"import com.liferay.portal.kernel.events." +
							"LifecycleEvent;");
					lines.add(
						"import com.liferay.portal.kernel.events." +
							"ActionException;");
				}

				if (line.equals(
						"public class FooAction implements LifecycleAction " +
							"{")) {

					StringBuilder sb = new StringBuilder();

					sb.append(
						"@Override\n"
					).append(
						"public void processLifecycleEvent"
					).append(
						"(LifecycleEvent lifecycleEvent)\n"
					).append(
						"throws ActionException {\n"
					).append(
						"System.out.println"
					).append(
						"(\"login.event.pre=\" + lifecycleEvent);\n"
					).append(
						"}\n"
					);

					lines.add(sb.toString());
				}
			}
		}

		try (Writer writer = new FileWriter(file)) {
			for (String string : lines) {
				writer.write(string + "\n");
			}
		}

		BuildTask buildtask = GradleRunnerUtil.executeGradleRunner(
			_projectPath, "build");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildtask);

		File buildOutput = new File(
			_projectPath + "/build/libs/shelloworld-1.0.0.jar");

		Assert.assertTrue(buildOutput.exists());

		String bundleID = BladeCLIUtil.installBundle(buildOutput);

		BladeCLIUtil.startBundle(bundleID);

		BladeCLIUtil.uninstallBundle(bundleID);
	}

	@Test
	public void testServiceWrapperGradleTemplate() throws Exception {
		_projectPath = BladeCLIUtil.createProject(
			_testDir, "service-wrapper", "serviceoverride", "-s",
			"com.liferay.portal.kernel.service.UserLocalServiceWrapper");

		BuildTask buildtask = GradleRunnerUtil.executeGradleRunner(
			_projectPath, "build");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildtask);

		File buildOutput = new File(
			_projectPath + "/build/libs/serviceoverride-1.0.0.jar");

		Assert.assertTrue(buildOutput.exists());

		String bundleID = BladeCLIUtil.installBundle(buildOutput);

		BladeCLIUtil.startBundle(bundleID);

		BladeCLIUtil.uninstallBundle(bundleID);
	}

	private File _projectPath;
	private File _testDir;

}