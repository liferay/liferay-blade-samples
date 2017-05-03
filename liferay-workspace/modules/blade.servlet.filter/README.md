javax.servlet.Filter (Liferay InvokerFilterChain Filters) Example
===================================================================
The plugin is an example of servlet filter. It will log the request and the response at debug level 
 
    # [Request log][path :/js_bundle_config][parameters : [t : 32 ]]
    # [Response log][Status :200][Content type :text/javascript;charset=UTF-8][Char encoding :UTF-8]
    
Plugin configuration
--------------------
After the deploy go "Control Panel --> Configuration --> Server Administration --> Log Level --> Add Category"

    # add the package com.liferay.blade.samples.servlet.filter and the level debug


