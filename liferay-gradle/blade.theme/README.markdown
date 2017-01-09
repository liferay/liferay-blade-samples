# Theme

The Theme sample provides the base files for a theme, using the Theme 
Builder Gradle plugin to build the theme. For more information on the Theme 
Builder Gradle plugin, see the [Theme Builder Gradle Plugin reference doc](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/theme-builder-gradle-plugin). 

When deploying this sample with no customizations, a theme based off of the 
`_styled` base theme is created. For more information on themes, visit the 
[Introduction to Themes](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/introduction-to-themes)
tutorial.

![Figure 1: A theme based off of the Styled base theme is created when the Theme Blade sample is deployed to Liferay.](https://github.com/mwilliams2014/liferay-docs/blob/LRDOCS-3150-theme-blade-sample-image/blade-sample-images/develop/tutorials/blade-images/theme.png)

To modify this sample, add the `images`, `js`, or `templates` directory, along 
with your modified files, to the `src/main/webapp` directory. The sample already 
provides the `src/main/resources/resources-importer`, `src/main/webapp/WEB-INF`, 
and `CSS` directories for you. Add your style modifications to the provided 
`CSS/_custom.scss` file. For a complete explanation of the theme's files, see 
the [Theme Reference Guide](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/theme-reference-guide).
