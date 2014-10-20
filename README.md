# Bootstrap Liferay Advanced Developer Environments(**BLADE**)

This small experiment aims to provide bootstrap project environments for all the major build tools in common use for Java projects so that Liferay development can start quickly and easily.

Software/Tools/Specifications | Version 
------------------------------ |-------
 OSGI Core | 5.0
 OSGI Compendium | 5.0
 OSGI Annotations | 6.0
 Liferay Portal | 7.x(Development)

# Projects
The template projects are categorized based on the build tools,

* `maven` - these are set of Liferay projects that can be bootstrapped on to `maven` development environment
* `bndtools` - these are set of Liferay projects that can be bootstrapped on to `bndtools` based development environment
* `gradle` - these are set of Liferay projects that can be bootstrapped on to `gradle` based development environment

The projects also demonstrate how to use various frameworks like:

* Blueprint
* Declarative Services(**DS**)
* OSGI API

# Integation Points
The following are the list of Liferay Integration points that are demonstrated as part of these projects,

- [x] javax.portlet.Portlet
- [x] com.liferay.portal.kernel.portlet.bridges.mvc.ActionCommand
- [x] java.util.ResourceBundle

## Maven

* `/maven/sample-maven-blueprint`
  
> A maven project which uses Blueprint for registering a portlet.

* `/maven/sample-maven-ds` 

> A maven project which uses the DS (Declarative Services) for registering a portlet.

* `/maven/sample-maven-osgiapi`

>A maven project which uses the raw OSGI APIs for registering a portlet.

## BndTools

*WIP*

# License
[License](/LICENSE.txt)

# Contribution

These templates can be copied freely and contributions are welcome.

# References

[OSGI R5](http://www.osgi.org/Release5/HomePage)

[OSGI Compendium R5](http://www.osgi.org/Download/File?url=/download/r5/osgi.cmpn-5.0.0.pdf)

[Blueprint](http://www.eclipse.org/gemini/blueprint/)
