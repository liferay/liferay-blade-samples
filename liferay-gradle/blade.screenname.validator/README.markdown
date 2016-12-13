Screen name validator 
=============================
This plugin sample provides a customization of screen name validation.When deploying the plugin 
liferay with use this validator to validate the screen name both client and server validation.

To modify this example you can modify the `com.liferay.blade.samples.screenname.validator.internal.CustomScreenNameValidator`. 
The plugin check if a screen name contains reserved words that are configured  in 
    
    Control Panel --> Configuration --> System Settings --> Foundation --> ScreenName Validator
    default value is  "admin|user"
    
It's possible to customize the configuration adding more properties in `com.liferay.blade.samples.screenname.validator.CustomScreenNameConfiguration`

Testing the validator
---------------------
   - Deploy the plugin 
   - Go to `Control Panel --> Users --> Users and Organizations`
   - Create a new user 
   - adding the screen name that contains the word `admin` or `user`


For more information on customizing the Validation sample to fit your
needs, see the Javadoc listed in this sample's
`CustomScreenNameValidator` class.