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

package com.liferay.blade.samples.integration.test.utils;

import aQute.bnd.osgi.Domain;
import aQute.bnd.version.Version;

import aQute.lib.io.IO;

import java.io.File;
import java.io.InputStream;

import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import org.junit.Assert;

/**
 * @author Lawrence Lee
 */
public class BladeCLIUtil {

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

		List<String> errorList = new ArrayList<>();

		String stringStream = null;

		if (errorStream != null) {
			stringStream = new String(IO.read(errorStream));

			errorList.add(stringStream);
		}

		List<String> filteredErrorList = new ArrayList<>();

		for (String string : errorList) {
			String exclusion = "(.*setlocale.*)";

			Pattern p = Pattern.compile(exclusion, Pattern.DOTALL);

			Matcher m = p.matcher(string);

			while (m.find()) {
				filteredErrorList.add(string);
			}

			if (string.contains("Picked up JAVA_TOOL_OPTIONS:")) {
				filteredErrorList.add(string);
			}
		}

		errorList.removeAll(filteredErrorList);

		Assert.assertTrue(errorList.toString(), errorList.size() <= 1);

		if (errorList.size() == 1) {
			Assert.assertTrue(errorList.get(0), errorList.get(0).isEmpty());
		}

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

			if (bundleVersion != 3) {
				throw new Exception(
					"Expecting bladejar with major version 3, found version: " +
						bundleVersion);
			}

			bladeJar = file;
		}

		return bladeJar.getCanonicalPath();
	}

	public static String installBundle(File file) throws Exception {
		String printFileName;
		String bundleID;
		String output;

		if (file.getName().endsWith(".war")) {
			printFileName = file.getName();

			printFileName = printFileName.substring(
				0, printFileName.lastIndexOf('.'));

			output = execute(
				"sh", "install",
				"webbundle:" + file.toURI().toASCIIString() + "?Web-ContextPath=/" +
					printFileName);

			bundleID = output.substring(
				output.indexOf("ID:") + 4, output.lastIndexOf("\n"));
		}
		else {
			output = execute("sh", "install", file.toURI().toASCIIString());

			bundleID = output.substring(
				output.indexOf("ID:") + 4, output.lastIndexOf("\n"));
		}

		if (output.toLowerCase().contains("failed") ||
			output.toLowerCase().contains("exception")) {

			throw new Exception(output);
		}

		return bundleID;
	}

	public static String startBundle(String bundleID) throws Exception {
		String output = execute("sh", "start", bundleID);

		if (output.toLowerCase().contains("exception")) {
			throw new Exception(output);
		}

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