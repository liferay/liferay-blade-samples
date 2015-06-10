# Bootstrap Liferay Advanced Developer Environments(**BLADE**)

[![Build Status (Work in Progress)](https://travis-ci.org/rotty3000/blade.svg?branch=master)](https://travis-ci.org/rotty3000/blade)
[![Join the chat at https://gitter.im/rotty3000/blade](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/rotty3000/blade?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

This small experiment aims to provide bootstrap project environments for all the major build tools in common use for Java projects so that Liferay development can start quickly and easily.

Software/Tools/Specifications | Version 
------------------------------ |-------
 OSGI Core | 5.0
 OSGI Compendium | 5.0
 OSGI Annotations | 6.0
 Liferay Portal | 7.x(Development)
 Bndtools | 2.4.0.M2

# Projects
The template projects are categorized based on the build tools,

* `maven` - these are set of Liferay projects that can be bootstrapped on to `maven` development environment
* `bndtools` - these are set of Liferay projects that can be bootstrapped on to `bndtools` based development environment
* `gradle` - these are set of Liferay projects that can be bootstrapped on to `gradle` based development environment

The projects also demonstrate how to use various frameworks like:

* Blueprint
* Declarative Services(**DS**)
* OSGI API

## Maven

* `/maven/sample-maven-blueprint`
  
> A maven project which uses Blueprint for registering a portlet.

* `/maven/sample-maven-ds` 

> A maven project which uses the DS (Declarative Services) for registering a portlet.

* `/maven/sample-maven-osgiapi`

>A maven project which uses the raw OSGI APIs for registering a portlet.

## Bndtools

* `cnf`

> The Bndtools configuration project

* `/bndtools/sample.bundle.bndtools.blueprint`
  
> A bndtools project which uses Blueprint for registering a portlet.

* `/bndtools/sample.bundle.bndtools.ds` 

> A bndtools project which uses the DS (Declarative Services) for registering a portlet.

* `/bndtools/sample.bundle.bndtools.osgiapi`

>A bndtools project which uses the raw OSGI APIs for registering a portlet.


# Integration Points
The following are the list of Liferay Integration points that are demonstrated as part of these projects,

- [x] javax.portlet.Portlet
- [x] com.liferay.portal.kernel.portlet.bridges.mvc.ActionCommand
	  The project `sample.bundle.bndtools.ip.actioncommand` demonstrates this integation point.  It integrates the action command named `greet` with portlet `greeter`.

	  __NOTE:__ To see how this example works, a portlet plugin with a portlet named **greeter** (javax.portlet.name='greeter') is to be deployed.  The command adds a key `greeting_message` to Liferay SessionMessages, along with a session attribute `GREETER_MESSAGE`. You can independently deploy the bundle `sample.bundle.bndtools.ip.actioncommand` a.k.a refresh the bundle without the need to redeploy the Portlet plugin.

- [ ] java.util.ResourceBundle

# License
[License](/LICENSE.txt)

# Contribution

These templates can be copied freely and contributions are welcome.

# References

[OSGI R5](http://www.osgi.org/Release5/HomePage)

[OSGI Compendium R5](http://www.osgi.org/Download/File?url=/download/r5/osgi.cmpn-5.0.0.pdf)

[Blueprint](http://www.eclipse.org/gemini/blueprint/)

[Bndtools](http://www.bndtools.org)