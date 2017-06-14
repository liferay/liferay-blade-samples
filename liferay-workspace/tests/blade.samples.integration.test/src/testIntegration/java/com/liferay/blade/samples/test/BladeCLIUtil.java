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

		if (errorStream != null) {
			errorList.add(new String(IO.read(errorStream)));
		}

		List<String> filteredErrorList = new ArrayList<>();

		for (String string : errorList) {
			if (!string.isEmpty() &&
				!string.contains("Picked up JAVA_TOOL_OPTIONS:")) {

				filteredErrorList.add(string);
			}
		}

		assertTrue(filteredErrorList.toString(), filteredErrorList.isEmpty());

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

		String bundleID = output.substring(
			output.indexOf("bundle id:") + 11,
			output.indexOf("\n", output.indexOf("bundle id:")));

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

	public static void uninstallBundle(String... bundleIDArgs)
		throws Exception {

		String[] executeArgs = new String[bundleIDArgs.length + 2];

		executeArgs[0] = "sh";
		executeArgs[1] = "uninstall";
		System.arraycopy(bundleIDArgs, 0, executeArgs, 2, bundleIDArgs.length);
		execute(executeArgs);
	}

}