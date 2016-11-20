package com.liferay.blade.samples.screenname.configuration;

import aQute.bnd.annotation.metatype.Meta;
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
        id = "com.liferay.blade.samples.screenname.configuration.CustomScreenNameConfiguration",
        localization = "content/Language", name = "custom.screen.name"
)
public interface CustomScreenNameConfiguration {

        @Meta.AD(deflt = "admin,user", description = "custom.screen.name.reserved.words", required = true)
        String reservedWord();


}
