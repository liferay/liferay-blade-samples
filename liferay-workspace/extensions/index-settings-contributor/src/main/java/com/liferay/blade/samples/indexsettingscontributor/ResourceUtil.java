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

package com.liferay.blade.samples.indexsettingscontributor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.nio.charset.StandardCharsets;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Liferay
 */
public class ResourceUtil {

	public static String readResouceAsString(String filename)
		throws UnsupportedEncodingException {

		InputStream inputStream = null;
		BufferedReader bufferedReader = null;

		try {
			ClassLoader classLoader = ResourceUtil.class.getClassLoader();

			inputStream = classLoader.getResourceAsStream(filename);

			InputStreamReader inputStreamReader = new InputStreamReader(
				inputStream, StandardCharsets.UTF_8.name());

			bufferedReader = new BufferedReader(inputStreamReader);

			Stream<String> stream = bufferedReader.lines();

			return stream.collect(Collectors.joining(System.lineSeparator()));
		}
		catch (UnsupportedEncodingException uee) {
			throw uee;
		}
		finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			}
			catch (IOException ioe) {
			}

			try {
				if (inputStream != null) {
					inputStream.close();
				}
			}
			catch (IOException ioe) {
			}
		}
	}

}