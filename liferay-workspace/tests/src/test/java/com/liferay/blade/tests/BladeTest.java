/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.liferay.blade.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import aQute.bnd.osgi.Jar;

import aQute.lib.io.IO;

import com.liferay.portal.kernel.util.StringUtil;

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
public class BladeTest {

	@BeforeClass
	public static void setUpClass() throws Exception {
		if (_isWindows()) {
			BladeCLI.startServerWindows(
				new File(System.getProperty("user.dir")).getParentFile(),
				"server", "start", "-b");
		}
		else {
			BladeCLI.execute(
				new File(System.getProperty("user.dir")).getParentFile(),
				"server", "start", "-b");
		}

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
		BladeCLI.execute("server", "stop");

		if (BladeCLI.bladeJar.exists()) {
			IO.delete(BladeCLI.bladeJar);
			assertFalse(BladeCLI.bladeJar.exists());
		}

		if (_bundleDir.exists()) {
			IO.delete(_bundleDir);
			assertFalse(_bundleDir.exists());
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
			String installBundleOutput = BladeCLI.installBundle(
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
			BladeCLI.startBundle(startBundleIO);
		}

		for (String allBundleID : bundleIDAllMap.keySet()) {
			BladeCLI.uninstallBundle(allBundleID);
		}
	}

	@Test
	public void testControlMenuEntryGradleTemplates() throws Exception {
		File projectPath = BladeCLI.createProject(
			_testDir, "control-menu-entry", "helloworld");

		BuildTask buildtask = GradleRunnerUtil.executeGradleRunner(
			projectPath, "build");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildtask);

		File buildOutput = new File(
			projectPath + "/build/libs/helloworld-1.0.0.jar");

		assertTrue(buildOutput.exists());

		String bundleID = BladeCLI.installBundle(buildOutput);

		BladeCLI.startBundle(bundleID);

		BladeCLI.uninstallBundle(bundleID);
	}

	@Test
	public void testMVCPortletGradleTemplates() throws Exception {
		File projectPath = BladeCLI.createProject(
			_testDir, "mvc-portlet", "helloworld");

		BuildTask buildtask = GradleRunnerUtil.executeGradleRunner(
			projectPath, "build");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildtask);

		File buildOutput = new File(
			projectPath + "/build/libs/helloworld-1.0.0.jar");

		assertTrue(buildOutput.exists());

		String bundleID = BladeCLI.installBundle(buildOutput);

		BladeCLI.startBundle(bundleID);

		BladeCLI.uninstallBundle(bundleID);
	}

	@Test
	public void testPanelAppGradleTemplates() throws Exception {
		File projectPath = BladeCLI.createProject(
			_testDir, "panel-app", "helloworld");

		BuildTask buildtask = GradleRunnerUtil.executeGradleRunner(
			projectPath, "build");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildtask);

		File buildOutput = new File(
			projectPath + "/build/libs/helloworld-1.0.0.jar");

		assertTrue(buildOutput.exists());

		String bundleID = BladeCLI.installBundle(buildOutput);

		BladeCLI.startBundle(bundleID);

		BladeCLI.uninstallBundle(bundleID);
	}

	@Test
	public void testPortletGradleTemplates() throws Exception {
		File projectPath = BladeCLI.createProject(
			_testDir, "portlet", "helloworld");

		BuildTask buildtask = GradleRunnerUtil.executeGradleRunner(
			projectPath, "build");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildtask);

		File buildOutput = new File(
			projectPath + "/build/libs/helloworld-1.0.0.jar");

		assertTrue(buildOutput.exists());

		String bundleID = BladeCLI.installBundle(buildOutput);

		BladeCLI.startBundle(bundleID);

		BladeCLI.uninstallBundle(bundleID);
	}

	@Test
	public void testPortletProviderGradleTemplates() throws Exception {
		File projectPath = BladeCLI.createProject(
			_testDir, "portlet-provider", "helloworld");

		BuildTask buildtask = GradleRunnerUtil.executeGradleRunner(
			projectPath, "build");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildtask);

		File buildOutput = new File(
			projectPath + "/build/libs/helloworld-1.0.0.jar");

		assertTrue(buildOutput.exists());

		String bundleID = BladeCLI.installBundle(buildOutput);

		BladeCLI.startBundle(bundleID);

		BladeCLI.uninstallBundle(bundleID);
	}

	@Test
	public void testServiceBuilderBladeSample() throws Exception {
		File projectPath = new File(
			System.getProperty("user.dir")).getParentFile();

		File serviceProperties = new File(
			projectPath,
			"modules/blade.servicebuilder.svc/src/main/resources" +
				"/service.properties");

		File servicePropertiesBackup = new File("service.properties.bak");

		IO.copy(serviceProperties, servicePropertiesBackup);

		BuildTask buildService = GradleRunnerUtil.executeGradleRunner(
			projectPath, ":modules:blade.servicebuilder.svc:buildService");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildService);

		IO.copy(servicePropertiesBackup, serviceProperties);
		IO.delete(servicePropertiesBackup);

		BuildTask cleanTask = GradleRunnerUtil.executeGradleRunner(
			projectPath, ":modules:blade.servicebuilder.api:clean");

		GradleRunnerUtil.verifyGradleRunnerOutput(cleanTask);

		BuildTask buildApiTask = GradleRunnerUtil.executeGradleRunner(
			projectPath, ":modules:blade.servicebuilder.api:build");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildApiTask);

		cleanTask = GradleRunnerUtil.executeGradleRunner(
			projectPath, ":modules:blade.servicebuilder.svc:clean");

		GradleRunnerUtil.verifyGradleRunnerOutput(cleanTask);

		BuildTask buildSvcTask = GradleRunnerUtil.executeGradleRunner(
			projectPath, ":modules:blade.servicebuilder.svc:build");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildSvcTask);

		cleanTask = GradleRunnerUtil.executeGradleRunner(
			projectPath, ":modules:blade.servicebuilder.test:clean");

		GradleRunnerUtil.verifyGradleRunnerOutput(cleanTask);

		BuildTask buildTestTask = GradleRunnerUtil.executeGradleRunner(
			projectPath, ":modules:blade.servicebuilder.test:build");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildTestTask);

		File buildApiOutput = new File(
			projectPath + "/modules/blade.servicebuilder.api/build/libs" +
				"/blade.servicebuilder.api-1.0.0.jar");
		File buildServiceOutput = new File(
			projectPath + "/modules/blade.servicebuilder.svc/build/libs" +
				"/blade.servicebuilder.svc-1.0.0.jar");
		File buildTestOutput = new File(
			projectPath + "/modules/blade.servicebuilder.test/build/libs" +
				"/blade.servicebuilder.test-1.0.0.jar");

		assertTrue(buildApiOutput.exists());
		assertTrue(buildServiceOutput.exists());
		assertTrue(buildTestOutput.exists());

		String bundleIDApi = BladeCLI.installBundle(buildApiOutput);
		String bundleIDService = BladeCLI.installBundle(buildServiceOutput);
		String bundleIDTest = BladeCLI.installBundle(buildTestOutput);

		BladeCLI.startBundle(bundleIDApi);
		BladeCLI.startBundle(bundleIDService);
		BladeCLI.startBundle(bundleIDTest);

		BladeCLI.execute("sh", "addfoo");
		assertTrue(
			"Unable to add",
			BladeCLI.execute("sh", "listfoo").contains("field1=field1"));

		BladeCLI.execute("sh", "updatefoo");

		assertTrue(
			"Unable to update",
			BladeCLI.execute("sh", "listfoo").contains("updated field"));

		BladeCLI.execute("sh", "deletefoo");
		assertTrue(
			"Unable to delete",
			BladeCLI.execute("sh", "listfoo").equals("listfoo"));

		BladeCLI.uninstallBundle(bundleIDApi, bundleIDService, bundleIDTest);
	}

	@Test
	public void testServiceBuilderGradleTemplate() throws Exception {
		File projectPath = BladeCLI.createProject(
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

		String bundleIDApi = BladeCLI.installBundle(buildApiOutput);
		String bundleIDService = BladeCLI.installBundle(buildServiceOutput);

		BladeCLI.startBundle(bundleIDApi);
		BladeCLI.startBundle(bundleIDService);

		BladeCLI.uninstallBundle(bundleIDApi, bundleIDService);
	}

	@Test
	public void testServiceGradleTemplate() throws Exception {
		BladeCLI.createProject(
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

				if (line.equals("import com.liferay.portal.kernel.events.LifecycleAction;")) {
					lines.add("import com.liferay.portal.kernel.events.LifecycleEvent;");
					lines.add("import com.liferay.portal.kernel.events.ActionException;");
				}

				if (line.equals(
						"public class FooAction implements LifecycleAction {")) {

					String s =
						new StringBuilder().append("@Override\n").
							append("public void processLifecycleEvent(LifecycleEvent lifecycleEvent)\n").
							append("throws ActionException {\n").
							append("System.out.println(\"login.event.pre=\" + lifecycleEvent);\n").
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

		String bundleID = BladeCLI.installBundle(buildOutput);

		BladeCLI.startBundle(bundleID);

		BladeCLI.uninstallBundle(bundleID);
	}

	@Test
	public void testServiceWrapperGradleTemplate() throws Exception {
		File projectPath = BladeCLI.createProject(
			_testDir, "service-wrapper", "serviceoverride", "-s",
			"com.liferay.portal.kernel.service.UserLocalServiceWrapper");

		BuildTask buildtask = GradleRunnerUtil.executeGradleRunner(
			projectPath, "build");

		GradleRunnerUtil.verifyGradleRunnerOutput(buildtask);

		File buildOutput = new File(
			projectPath + "/build/libs/serviceoverride-1.0.0.jar");

		assertTrue(buildOutput.exists());

		String bundleID = BladeCLI.installBundle(buildOutput);

		BladeCLI.startBundle(bundleID);

		BladeCLI.uninstallBundle(bundleID);
	}

	private static boolean _isWindows() {
		String systemProp = System.getProperty("os.name");

		StringUtil.toLowerCase(systemProp);

		return systemProp.contains("windows");
	}

	private static final File _bundleDir = IO.getFile("../bundles");

	private File _testDir;

}