package com.liferay.blade.samples.screenname.validator;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
<<<<<<< HEAD
 * Configuration class for the plugin validator.
=======
 * Provides a configuration class for the plugin's validator.
 *
>>>>>>> liferay/master
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
<<<<<<< HEAD
	/**
	 * Returns the reserved words configured in foundation settings.
	 * @return String[] default value  "admin|user"
=======

	/**
	 * Returns the reserved words configured in the Control Panel &rarr;
	 * Configuration &rarr; System Settings &rarr; Foundation &rarr; ScreenName
	 * Validator menu.
	 *
	 * @return the reserved words, which by default are <code>admin|user</code>
>>>>>>> liferay/master
	 */
	@Meta.AD(deflt = "admin|user", required = false)
	public String[] reservedWords();

}