# Theme Contributor

The Theme Contributor sample lets you package UI resources (CSS and JS) 
independent of a theme to include on a Liferay Portal page. For more information 
on Theme Contributors, visit the
[Theme Contributors](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/theme-contributors)
tutorial.

This sample provides examples of JS, and UI updates for the Body, Control Menu, 
Product Menu, and Simulation Panel. To modify this sample, replace the 
corresponding `JS` or `SCSS` file with the JavaScript or styles that you want, 
or add your own `SCSS` or `JS` files. For example, the default Theme Contributor 
sample provides an update to the Control Menu's `background-color` in its 
`src/main/resources/META-INF/resources/css/blade.theme.contributor/_control_menu.scss` 
file:

    body {
            .control-menu {
                    background-color: darkkhaki;
            }
    }

All of the `SCSS` files are imported into the main
`blade.theme.contributor.scss` 
file:

    @import "bourbon";
    @import "mixins";

    @import "blade.theme.contributor/body";
    @import "blade.theme.contributor/control_menu";
    @import "blade.theme.contributor/product_menu";
    @import "blade.theme.contributor/simulation_panel";

If you add your own `SCSS` files, you must add them to the list of imports in 
the `blade.theme.contributor.scss` file.
    
The sample
[`blade.theme.contributor.js` file](https://raw.githubusercontent.com/liferay/liferay-blade-samples/master/liferay-gradle/blade.theme.contributor/src/main/resources/META-INF/resources/js/blade.theme.contributor.js) 
logs a message to the console window, but you can write whatever JavaScript you 
want:

    console.log('Hello Blade Theme Contributor!');
