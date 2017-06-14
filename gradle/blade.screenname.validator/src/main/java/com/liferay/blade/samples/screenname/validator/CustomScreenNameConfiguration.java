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
	localization = "content/Language", name = "custom.screen.name"
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