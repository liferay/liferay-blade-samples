# Bootstrap Liferay Advanced Developer Environments(**BLADE**)

[![Build Status](https://travis-ci.org/rotty3000/blade.svg?branch=master)](https://travis-ci.org/rotty3000/blade)
[![Join the chat at https://gitter.im/rotty3000/blade](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/rotty3000/blade?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

This small experiment aims to provide bootstrap project environments for all the major build tools in common use for Java projects so that Liferay development can start quickly and easily.

# Projects
The template projects are categorized based on the build tools,

* `gradle` - these are set of Liferay projects that can be bootstrapped on to `gradle` based development environment
* `liferay-gradle` - these are set of Liferay projects that can be bootstrapped on to `com.liferay.plugin`(a gradle plugin) based development environment
* `maven` - these are set of Liferay projects that can be bootstrapped on to `maven` development environment
* `bndtools` - these are set of Liferay projects that can be bootstrapped on to `bndtools` based development environment


The projects also demonstrate how to use various frameworks like:

* Blueprint
* Declarative Services(**DS**)
* OSGI API

## A note on Blueprint

Liferay does not provide a blueprint implementation out of the box. To use the blueprint modules provided in blade, you must deploy a blueprint implementation such as [Apache Aries - Blueprint](http://aries.apache.org/modules/blueprint.html). Three bundles are needed:

* [Apache Aries Blueprint Bundle](http://mvnrepository.com/artifact/org.apache.aries.blueprint/org.apache.aries.blueprint/1.1.0)
* [Apache Aries Blueprint Annotation API](http://mvnrepository.com/artifact/org.apache.aries.blueprint/org.apache.aries.blueprint.annotation.api/1.0.1)
* [Apache Aries Proxy Bundle](http://mvnrepository.com/artifact/org.apache.aries.proxy/org.apache.aries.proxy/1.0.1)

Simply download the bundles from mvnrepository and drop them in your osgi/modules folder as usual before deploying blueprint bundles.

## Gradle

* `/gradle/blade.authenticator.shiro`

> A gradle project which uses Apache Shiro for hooking auth.pipeline.pre.

* `/gradle/blade.authfailure`

> A gradle project which demonstrates a hook for auth.failure and auth.max.failures.

* `/gradle/blade.configurationaction`

> A gradle project which demonstrates integration point of ConfigurationAction.

* `/gradle/blade.gogo`

> A gradle project which contributes to felix gogo commands, and demonstrates consuming Liferay services through DS (Declarative Services).

* `/gradle/blade.hook.jsp`

> A gradle project which demonstrates a jsp hook for login.jsp in com.liferay.login.web bundle via fragment bundle.

* `/gradle/blade.indexerpostprocessor`

> A gradle project which contributes to IndexerPostProcessor.

* `/gradle/blade.lifecycle.loginpreaction`

> A gradle project which makes a hook for login.events.pre.

* `/gradle/blade.pollprocessor`

> A gradle project which make a hook for PollerProcessor.

* `/gradle/blade.portlet.actioncommand`

> A gradle project which demonstrates integration point of MVCActionCommand.

* `/gradle/blade.portlet.blueprint`

> A gradle project which uses Blueprint for registering a portlet.

* `/gradle/blade.portlet.ds`

> A gradle project which uses the DS (Declarative Services) for registering a portlet.

* `/gradle/blade.portlet.filter`

> A gradle project which demonstrates integration point of PortletFilter.

* `/gradle/blade.portlet.jsp`

> A gradle project which demonstrates a simple jsp portlet.

* `/gradle/blade.portlet.osgiapi`

>A gradle project which uses the raw OSGI APIs for registering a portlet.

* `/gradle/blade.service.hook.user`

> A gradle project which makes a hook for UserLocalServiceWrapper.

* `/gradle/blade.servicebuilder.api`
* `/gradle/blade.servicebuilder.svc`
* `/gradle/blade.servicebuilder.test`
* `/gradle/blade.servicebuilder.web`

>ServiceBuilder based project, separated to 4 bundles, api bundle for interface, svc bundle for implementation, test bundle for tesing, web bundle is a portlet calling generated services. 

* `/gradle/blade.strutsaction`

> A gradle project which demonstrates integration point of StrutsAction.

* `/gradle/blade.strutsportletaction`

> A gradle project which demonstrates integration point of StrutsPortletAction.

##liferay-gradle

all the projects' description in 'liferay-gradle' directory are the same as the ones in 'liferay' directory.

## Maven

* `/maven/blade.authenticator.shiro`

> A maven project which uses Apache Shiro for hooking auth.pipeline.pre.

* `/maven/blade.authfailure`

> A maven project which demonstrates a hook for auth.failure and auth.max.failures.

* `/maven/blade.configurationaction`

> A maven project which demonstrates integration point of ConfigurationAction.

* `/maven/blade.gogo`

> A maven project which contributes to felix gogo commands, and demonstrates consuming Liferay services through DS (Declarative Services).

* `/maven/blade.hook.jsp`

> A maven project which demonstrates a jsp hook for login.jsp in com.liferay.login.web bundle via fragment bundle.

* `/maven/blade.indexerpostprocessor`

> A maven project which contributes to IndexerPostProcessor.

* `/maven/blade.jsf.portlet.ds`

> A maven project which uses the DS (Declarative Services) for registering a jsf portlet.

* `/maven/blade.lifecycle.loginpreaction`

> A maven project which makes a hook for login.events.pre.

* `/maven/blade.pollprocessor`

> A maven project which make a hook for PollerProcessor.

* `/maven/blade.portlet.actioncommand`

> A maven project which demonstrates integration point of MVCActionCommand.

* `/maven/blade.portlet.blueprint`

> A maven project which uses Blueprint for registering a portlet.

* `/maven/blade.portlet.ds`

> A maven project which uses the DS (Declarative Services) for registering a portlet.

* `/maven/blade.portlet.filter`

> A maven project which demonstrates integration point of PortletFilter.

* `/maven/blade.portlet.jsp`

> A maven project which demonstrates a simple jsp portlet.

* `/maven/blade.portlet.osgiapi`

>A maven project which uses the raw OSGI APIs for registering a portlet.

* `/maven/blade.service.hook.user`

> A maven project which makes a hook for UserLocalServiceWrapper.

* `/maven/blade.servicebuilder`

>ServiceBuilder based project, separated to 4 bundles(subprojects), api bundle for interface, svc bundle for implementation, test bundle for tesing, web bundle is a portlet calling generated services.

* `/maven/blade.strutsaction`

> A maven project which demonstrates integration point of StrutsAction.

* `/maven/blade.strutsportletaction`

> A maven project which demonstrates integration point of StrutsPortletAction.

## Bndtools

* `/bndtools/cnf`

> The Bndtools configuration project

* `/bndtools/blade.authenticator.shiro`

> A bndtools project which uses Apache Shiro for hooking auth.pipeline.pre.

* `/bndtools/blade.authfailure`

> A bndtools project which demonstrates a hook for auth.failure and auth.max.failures.

* `/bndtools/blade.hook.jsp`

> A bndtools project which demonstrates a jsp hook for login.jsp in com.liferay.login.web bundle via fragment bundle.

* `/bndtools/blade.portlet.actioncommand`

> A bndtools project which demonstrates integration point of MVCActionCommand.

* `/bndtools/blade.portlet.blueprint`

> A bndtools project which uses Blueprint for registering a portlet.

* `/bndtools/blade.portlet.ds`

> A bndtools project which uses the DS (Declarative Services) for registering a portlet.

* `/bndtools/blade.portlet.filter`

> A bndtools project which demonstrates integration point of PortletFilter.

* `/bndtools/blade.portlet.osgiapi`

> A bndtools project which uses the raw OSGI APIs for registering a portlet.

* `/bndtools/blade.run`

> A bndtools project which can push bundles into an osgi container and run them, need biz.aQute.remote.agent-X.X.X.jar deployed first.

* `/bndtools/blade.service.hook.user`

> A bndtools project which makes a hook for UserLocalServiceWrapper.

* `/bndtools/blade.servicebuilder.api`
* `/bndtools/blade.servicebuilder.svc`
* `/bndtools/blade.servicebuilder.test`
* `/bndtools/blade.servicebuilder.web`

> ServiceBuilder based project, separated to 4 bundles, api bundle for interface, svc bundle for implementation, test bundle for tesing, web bundle is a portlet calling generated services.

# Integration Points
The following are the list of Liferay Integration points that are demonstrated as part of these projects,

- [x] javax.portlet.Portlet
- [x] com.liferay.portal.kernel.portlet.bridges.mvc.ActionCommand
	  The project `blade.portlet.actioncommand` demonstrates this integration point.  It integrates the action command named `greet` with portlet `greeter`.

	  __NOTE:__ To see how this example works, a portlet plugin with a portlet named **greeter** (javax.portlet.name='greeter') is to be deployed.  The command adds a key `greeting_message` to Liferay SessionMessages, along with a session attribute `GREETER_MESSAGE`. You can independently deploy the bundle `blade.portlet.actioncommand` a.k.a refresh the bundle without the need to redeploy the Portlet plugin.

The rest will come soon.

# License
[License](/LICENSE.txt)

# Contribution

These templates can be copied freely and contributions are welcome.

# References

[OSGI R5](http://www.osgi.org/Release5/HomePage)

[OSGI Compendium R5](http://www.osgi.org/Download/File?url=/download/r5/osgi.cmpn-5.0.0.pdf)

[Blueprint](http://www.eclipse.org/gemini/blueprint/)

[Bndtools](http://www.bndtools.org)
