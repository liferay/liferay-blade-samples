package com.liferay.blade.samples.screenname.validator;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * Configuration class for the plugin validator.
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
	 * Returns the reserved words configured in foundation settings.
	 * @return String[] default value  "admin|user"
	 */
	@Meta.AD(deflt = "admin|user", required = false)
	public String[] reservedWords();

}