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