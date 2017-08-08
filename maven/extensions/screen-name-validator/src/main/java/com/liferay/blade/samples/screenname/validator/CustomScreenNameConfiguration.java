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

package com.liferay.blade.samples.screenname.validator;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * Provides a configuration class for the plugin's validator.
 *
 * @author Romeo Sheshi
 */
@ExtendedObjectClassDefinition(
	category = "foundation", scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	id = "com.liferay.blade.samples.screenname.validator.CustomScreenNameConfiguration",
	localization = "content/Language", name = "custom-screen-name"
)
public interface CustomScreenNameConfiguration {

	/**
	 * Returns the reserved words configured in the Control Panel &rarr;
	 * Configuration &rarr; System Settings &rarr; Foundation &rarr; ScreenName
	 * Validator menu.
	 *
	 * @return the reserved words, which by default are <code>admin|user</code>
	 */
	@Meta.AD(deflt = "admin|user", required = false)
	public String[] reservedWords();

}