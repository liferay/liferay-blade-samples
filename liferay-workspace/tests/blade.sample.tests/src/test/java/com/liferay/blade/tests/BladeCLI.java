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

import static org.junit.Assert.assertTrue;

import aQute.bnd.osgi.Domain;
import aQute.bnd.version.Version;

import aQute.lib.io.IO;

import com.liferay.portal.kernel.util.StringUtil;

import java.io.File;
import java.io.InputStream;

import java.net.URL;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * @author Lawrence Lee
 */
public class BladeCLI {

	public static File bladeJar;

	public static File createProject(
			File testDir, String templateName, String bundleName,
			String...createArgs)
		throws Exception {

		String[] executeArgs = new String[createArgs.length + 6];

		executeArgs[0] = "create";
		executeArgs[1] = "-d";
		executeArgs[2] = testDir.getPath();
		executeArgs[3] = "-t";
		executeArgs[4] = templateName;
		System.arraycopy(createArgs, 0, executeArgs, 5, createArgs.length);
		executeArgs[createArgs.length + 5] = bundleName;

		execute(executeArgs);

		File projectPath = new File(testDir + "/" + bundleName);

		return projectPath;
	}

	public static String execute(File workingDir, String... bladeArgs)
		throws Exception {

		String bladeCLIJarPath = getLatestBladeCLIJar();

		List<String> command = new ArrayList<>();

		command.add("java");
		command.add("-jar");
		command.add(bladeCLIJarPath);

		for (String arg : bladeArgs) {
			command.add(arg);
		}

		Process process = new ProcessBuilder(
			command.toArray(new String[0])).directory(workingDir).start();

		process.waitFor();

		InputStream stream = process.getInputStream();

		String output = new String(IO.read(stream));

		InputStream errorStream = process.getErrorStream();

		String errors = new String(IO.read(errorStream));

		assertTrue(errors, errors == null || errors.isEmpty());

		output = StringUtil.toLowerCase(output);

		return output;
	}

	public static String execute(String... bladeArgs) throws Exception {
		return execute(null, bladeArgs);
	}

	public static String getLatestBladeCLIJar() throws Exception {
		if (bladeJar == null) {
			URL url = new URL(System.getProperty("bladeURL"));
			File file = new File("blade.jar");

			FileUtils.copyURLToFile(url, file);

			Domain jar = Domain.domain(file);

			int bundleVersion = new Version(jar.getBundleVersion()).getMajor();

			if (bundleVersion != 2) {
				throw new Exception(
					"Expecting bladejar with major version 2, found version: " +
						bundleVersion);
			}

			bladeJar = file;
		}

		return bladeJar.getCanonicalPath();
	}

	public static String installBundle(File file) throws Exception {
		String output = execute("sh", "install", file.toURI().toString());

		String bundleID = output.substring(output.length() - 3);

		if (output.contains("Failed") || output.contains("IOException")) {
			throw new Exception(output);
		}

		return bundleID;
	}

	public static String startBundle(String bundleID) throws Exception {
		String output = execute("sh", "start", bundleID);

		if (output.contains("Exception")) {
			throw new Exception(output);
		}

		return output;
	}

	public static String startServerWindows(
			File workingDir, String... bladeArgs)
		throws Exception {

		String bladeCLIJarPath = getLatestBladeCLIJar();

		List<String> command = new ArrayList<>();

		command.add("start");
		command.add("/b");
		command.add("java");
		command.add("-jar");
		command.add(bladeCLIJarPath);

		for (String arg : bladeArgs) {
			command.add(arg);
		}

		Process process = new ProcessBuilder(
			command.toArray(new String[0])).directory(workingDir).start();

		process.waitFor();

		InputStream stream = process.getInputStream();

		String output = new String(IO.read(stream));

		InputStream errorStream = process.getErrorStream();

		String errors = new String(IO.read(errorStream));

		assertTrue(errors, errors == null || errors.isEmpty());

		return output;
	}

	public static void uninstallBundle(String... bundleIDArgs)
		throws Exception {

		String[] executeArgs = new String[bundleIDArgs.length + 2];

		executeArgs[0] = "sh";
		executeArgs[1] = "uninstall";
		System.arraycopy(bundleIDArgs, 0, executeArgs, 2, bundleIDArgs.length);
		execute(executeArgs);
	}

}