package com.liferay.blade.samples.screenname.validator;

import aQute.bnd.annotation.metatype.Meta;

import java.util.List;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * User: Romeo Sheshi <a href="mailto:rsheshi@gmail.com">Romeo Sheshi</a>
 * Date: 20/11/2016
 * Time: 12:18
 */

@ExtendedObjectClassDefinition(
        category = "foundation", scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
        id = "com.liferay.blade.samples.screenname.validator.CustomScreenNameConfiguration",
        localization = "content/Language", name = "custom.screen.name"

)
public interface CustomScreenNameConfiguration {

        @Meta.AD(deflt = "admin|user", required = false )
        public String[] reservedWords();


}
