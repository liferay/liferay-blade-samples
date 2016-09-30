# Bootstrap Liferay Advanced Developer Environments (BLADE)

[![Build Status](https://travis-ci.org/rotty3000/blade.svg?branch=master)](https://travis-ci.org/rotty3000/blade)
[![Join the chat at https://gitter.im/rotty3000/blade](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/rotty3000/blade?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

Liferay's Blade samples provides bootstrap project environments for all major
build tools in common use for Java projects so that Liferay development can
start quickly and easily.

## Build Tools

The template projects are categorized under four build tools:

* `gradle` - A set of Liferay projects that can be bootstrapped onto the
  *Gradle* based development environment.
* `liferay-gradle` - A set of Liferay projects that can be bootstrapped onto the
  `com.liferay.plugin` (a Gradle plugin) based development environment.
* `maven` - A set of Liferay projects that can be bootstrapped onto the *Maven*
  development environment.
* `bndtools` - A set of Liferay projects that can be bootstrapped onto the
  *Bndtools* based development environment.

### A Note on BNDTools

There are 2 additional projects in the `/bndtools/` folder that you will need to
build and publish the modules:

* `/bndtools/cnf` - The Bndtools configuration project
* `/bndtools/blade.run` - A Bndtools project which can push bundles into an OSGi
  container and run them. The `biz.aQute.remote.agent-X.X.X.jar` must be
  deployed first before this project will function properly.

## Frameworks

The template projects also demonstrate how to use various frameworks:

* Blueprint
* Declarative Services (**DS**)
* OSGi API

### A Note on Blueprint

Liferay does not provide a Blueprint implementation out of the box. To use the
Blueprint modules provided in Blade, you must deploy a Blueprint implementation
such as [Apache Aries -
Blueprint](http://aries.apache.org/modules/blueprint.html). Three bundles are
needed:

* [Apache Aries Blueprint Bundle](http://mvnrepository.com/artifact/org.apache.aries.blueprint/org.apache.aries.blueprint/1.1.0)
* [Apache Aries Blueprint Annotation API](http://mvnrepository.com/artifact/org.apache.aries.blueprint/org.apache.aries.blueprint.annotation.api/1.0.1)
* [Apache Aries Proxy Bundle](http://mvnrepository.com/artifact/org.apache.aries.proxy/org.apache.aries.proxy/1.0.1)

Simply download the bundles from [mvnrepository](https://mvnrepository.com/) and
drop them in your `osgi/modules` folder before deploying Blueprint bundles.

## Liferay Extension Points and Template Projects 

### `auth.pipeline.pre` 

__Extension point description__: 

__Template project description__: Uses Apache Shiro for hooking
`auth.pipeline.pre`.

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.authenticator.shiro](./liferay-gradle/blade.authenticator.shiro) |
| BND Tools  | [./bndtools/blade.authenticator.shiro](./bndtools/blade.authenticator.shiro)    |
| Gradle     | [./gradle/blade.authenticator.shiro](./gradle/blade.authenticator.shiro)        |
| Maven      | [./maven/blade.authenticator.shiro](./maven/blade.authenticator.shiro)          |

### `auth.failure` and `auth.max.failures`

__Extension point description__: 

__Template project description__: Demonstrates a hook for `auth.failure` and
`auth.max.failures`.

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.authfailure](./liferay-gradle/blade.authfailure) |
| BND Tools  | [./bndtools/blade.authfailure](./bndtools/blade.authfailure)    |
| Gradle     | [./gradle/blade.authfailure](./gradle/blade.authfailure)        |
| Maven      | [./maven/blade.authfailure](./maven/blade.authfailure)          |

### `AutoLogin`

__Extension point description__: 

__Template project description__: Demonstrates the `AutoLogin` integration
point.

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.autologin](./liferay-gradle/blade.autologin) |
| BND Tools  | [./bndtools/blade.autologin](./bndtools/blade.autologin)    |
| Gradle     | [./gradle/blade.autologin](./gradle/blade.autologin)        |
| Maven      | [./maven/blade.autologin](./maven/blade.autologin)          |

### `ConfigurationAction`

__Extension point description__: 

__Template project description__: Demonstrates the `ConfigurationAction`
integration point.

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.configurationaction](./liferay-gradle/blade.configurationaction) |
| BND Tools  | [./bndtools/blade.configurationaction](./bndtools/blade.configurationaction)    |
| Gradle     | [./gradle/blade.configurationaction](./gradle/blade.configurationaction)        |
| Maven      | [./maven/blade.configurationaction](./maven/blade.configurationaction)          |

### Control Menu Entry

__Extension point description__: 

__Template project description__: 

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.controlmenuentry](./liferay-gradle/blade.controlmenuentry) |
| BND Tools  | [./bndtools/blade.controlmenuentry](./bndtools/blade.controlmenuentry)    |
| Gradle     | [./gradle/blade.controlmenuentry](./gradle/blade.controlmenuentry)        |
| Maven      | [./maven/blade.controlmenuentry](./maven/blade.controlmenuentry)          |

### Core JSP Hook

__Extension point description__: 

__Template project description__: 

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.corejsphook](./liferay-gradle/blade.corejsphook) |
| BND Tools  | [./bndtools/blade.corejsphook](./bndtools/blade.corejsphook)    |
| Gradle     | [./gradle/blade.corejsphook](./gradle/blade.corejsphook)        |
| Maven      | [./maven/blade.corejsphook](./maven/blade.corejsphook)          |

### `FriendlyURLMapper`

__Extension point description__: Lets a developer provide (or overwrite)
friendly URL mapping for portlets.

__Template project description__: Demonstrates how to create a
`FriendlyURLMapper` for the standard `NetworkUtilities` portlet.

The `NetworkUtilities` portlet does not provide friendly URLs out of the box. To
test this plugin, put a `NetworkUtilities` portlet on the home page. After
deploying this plugin, you can access its tabs directly using the following
URLs:

* http://localhost:8080/web/guest/home/-/NetworkUtilities/dns-lookup
* http://localhost:8080/web/guest/home/-/NetworkUtilities/whois

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.friendlyurl](./liferay-gradle/blade.friendlyurl) |
| BND Tools  | [./bndtools/blade.friendlyurl](./bndtools/blade.friendlyurl)    |
| Gradle     | [./gradle/blade.friendlyurl](./gradle/blade.friendlyurl)        |
| Maven      | [./maven/blade.friendlyurl](./maven/blade.friendlyurl)          |

### Gogo Command

__Extension point description__: 

__Template project description__: Demonstrates Felix Gogo commands and consuming
Liferay services through DS (Declarative Services).

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.gogo](./liferay-gradle/blade.gogo) |
| BND Tools  | [./bndtools/blade.gogo](./bndtools/blade.gogo)    |
| Gradle     | [./gradle/blade.gogo](./gradle/blade.gogo)        |
| Maven      | [./maven/blade.gogo](./maven/blade.gogo)          |

### JSP Hook

__Extension point description__: 

__Template project description__: Demonstrates a JSP hook for `login.jsp` in the
`com.liferay.login.web` bundle via a fragment bundle.

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.hook.jsp](./liferay-gradle/blade.hook.jsp) |
| BND Tools  | [./bndtools/blade.hook.jsp](./bndtools/blade.hook.jsp)        |
| Gradle     | [./gradle/blade.hook.jsp](./gradle/blade.hook.jsp)        |
| Maven      | [./maven/blade.hook.jsp](./maven/blade.hook.jsp)          |

### Resource Bundle

__Extension point description__: 

__Template project description__: 

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.hook.resourcebundle](./liferay-gradle/blade.hook.resourcebundle) |
| BND Tools  | [./bndtools/blade.hook.resourcebundle](./bndtools/blade.hook.resourcebundle)      |
| Gradle     | [./gradle/blade.hook.resourcebundle](./gradle/blade.hook.resourcebundle)      |
| Maven      | [./maven/blade.hook.resourcebundle](./maven/blade.hook.resourcebundle)        |

### `IndexerPostProcessor`

__Extension point description__: 

__Template project description__: Demonstrates how to create a custom
`IndexerPostProcessor`.

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.indexerpostprocessor](./liferay-gradle/blade.indexerpostprocessor) |
| BND Tools  | [./bndtools/blade.indexerpostprocessor](./bndtools/blade.indexerpostprocessor)      |
| Gradle     | [./gradle/blade.indexerpostprocessor](./gradle/blade.indexerpostprocessor)      |
| Maven      | [./maven/blade.indexerpostprocessor](./maven/blade.indexerpostprocessor)        |

### `login.events.pre`

__Extension point description__: Demonstrates how to implement a Liferay
`com.liferay.portal.kernel.events.LifecycleAction`. This API replaces all the
legacy lifecycle events such as `com.liferay.portal.kernel.events.Action`,
`com.liferay.portal.kernel.events.SessionAction`, and
`com.liferay.portal.kernel.events.SimpleAction`. Connecting a `LifecycleAction`
to a particular event is determined by the OSGi service property `key`. The
following keys are supported:

* `application.shutdown.events` - fired during destruction of company instances
  at portal shutdown.
* `application.startup.events` - fired during initialization of company
  instances at portal start, or when a new instance is created.
* `global.shutdown.events` - fired during destruction of the portal's main
  servlet.
* `global.startup.events` - fire during initialization of the portal's main
  servlet.
* `layout.configuration.action.delete` - fired during destruction of a page
  (Layout).
* `layout.configuration.action.update` - fired during initialization of a page
  (Layout).
* `login.events.post` - fired immediately following login.
* `login.events.pre` - fired immediately prior to login.
* `logout.events.post` - fired immediately following logout.
* `logout.events.pre` - fired immediately prior to logout.
* `servlet.service.events.post` - fired following requests to the portal
  (including all portlet container requests).
* `servlet.service.events.pre` - fired prior to requests to the portal
  (including all portlet container requests & post login).
* `servlet.session.create.events` - fired during creation of a portal's HTTP
  session.
* `servlet.session.destroy.events` - fired during destruction of a portal's HTTP
  session.
  
__Template project description__:  Demonstrates a hook for `login.events.pre`.

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.lifecycle.loginpreaction](./liferay-gradle/blade.lifecycle.loginpreaction) |
| BND Tools  | [./bndtools/blade.lifecycle.loginpreaction](./bndtools/blade.lifecycle.loginpreaction)      |
| Gradle     | [./gradle/blade.lifecycle.loginpreaction](./gradle/blade.lifecycle.loginpreaction)      |
| Maven      | [./maven/blade.lifecycle.loginpreaction](./maven/blade.lifecycle.loginpreaction)        |

### `ModelListener`

__Extension point description__: Model Listeners are used to listen for events
on models and do something in response.

__Template project description__: Demonstrates how to create a model listener
for `Layout`.

After deploying this plugin, the title of any newly created page will be
automatically set to *Title generated by model listener!*.

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.modellistener](./liferay-gradle/blade.modellistener) |
| BND Tools  | [./bndtools/blade.modellistener](./bndtools/blade.modellistener)  |
| Gradle     | [./gradle/blade.modellistener](./gradle/blade.modellistener)      |
| Maven      | [./maven/blade.modellistener](./maven/blade.modellistener)        |

### `PollerProcessor`

__Extension point description__: 

__Template project description__: Demonstrates how to make a hook for a
`PollerProcessor`.

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.pollprocessor](./liferay-gradle/blade.pollprocessor) |
| BND Tools  | [./bndtools/blade.pollprocessor](./bndtools/blade.pollprocessor)      |
| Gradle     | [./gradle/blade.pollprocessor](./gradle/blade.pollprocessor)      |
| Maven      | [./maven/blade.pollprocessor](./maven/blade.pollprocessor)        |

### `MVCActionCommand`

__Extension point description__: 

__Template project description__: Demonstrates the `MVCActionCommand` extension
point. It integrates the action command named `greet` with portlet `greeter`. To
see how this example works, a portlet plugin with a portlet named **greeter**
(`javax.portlet.name='greeter'`) should be deployed. The command adds a key
`greeting_message` to Liferay `SessionMessages`, along with a session attribute
`GREETER_MESSAGE`. You can independently deploy the bundle
`blade.portlet.actioncommand` (i.e., refresh the bundle without the need to
redeploy the Portlet plugin).

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.portlet.actioncommand](./liferay-gradle/blade.portlet.actioncommand) |
| BND Tools  | [./bndtools/blade.portlet.actioncommand](./bndtools/blade.portlet.actioncommand)  |
| Gradle     | [./gradle/blade.portlet.actioncommand](./gradle/blade.portlet.actioncommand)      |
| Maven      | [./maven/blade.portlet.actioncommand](./maven/blade.portlet.actioncommand)        |

### Portlet

Below are examples of building portlets using different frameworks: 

#### Liferay MVC Portlet - Using Blueprint Framework

__Template project description__: Demonstrates how to use the Blueprint
framework for registering a portlet.

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.portlet.blueprint](./liferay-gradle/blade.portlet.blueprint) |
| BND Tools  | [./bndtools/blade.portlet.blueprint](./bndtools/blade.portlet.blueprint)  |
| Gradle     | [./gradle/blade.portlet.blueprint](./gradle/blade.portlet.blueprint)      |
| Maven      | [./maven/blade.portlet.blueprint](./maven/blade.portlet.blueprint)        |

#### Liferay MVC Portlet - Using DS (Declarative Services) Framework

__Template project description__: Demonstrates how to use the DS (Declarative
Services) framework for registering a portlet.

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.portlet.ds](./liferay-gradle/blade.portlet.ds) |
| BND Tools  | [./bndtools/blade.portlet.ds](./bndtools/blade.portlet.ds)  |
| Gradle     | [./gradle/blade.portlet.ds](./gradle/blade.portlet.ds)      |
| Maven      | [./maven/blade.portlet.ds](./maven/blade.portlet.ds)        |

#### JSP Portlet 

__Extension point description__: 

__Template project description__:  Demonstrates a simple JSP portlet.

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.portlet.jsp](./liferay-gradle/blade.portlet.jsp) |
| BND Tools  | [./bndtools/blade.portlet.jsp](./bndtools/blade.portlet.jsp) |
| Gradle     | [./gradle/blade.portlet.jsp](./gradle/blade.portlet.jsp)      |
| Maven      | [./maven/blade.portlet.jsp](./maven/blade.portlet.jsp)        |

#### Liferay MVC Portlet - Using Raw OSGI APIs

__Template project description__: Demonstrates how to use the raw OSGI APIs for
registering a portlet.

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.portlet.osgiapi](./liferay-gradle/blade.portlet.osgiapi) |
| BND Tools  | [./bndtools/blade.portlet.osgiapi](./bndtools/blade.portlet.osgiapi)  |
| Gradle     | [./gradle/blade.portlet.osgiapi](./gradle/blade.portlet.osgiapi)      |
| Maven      | [./maven/blade.portlet.osgiapi](./maven/blade.portlet.osgiapi)        |

### Configuration Icon

__Extension point description__: 

__Template project description__: 

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.portlet.configuration.icon](./liferay-gradle/blade.portlet.configuration.icon) |
| BND Tools  | [./bndtools/blade.portlet.configuration.icon](./bndtools/blade.portlet.configuration.icon)  |
| Gradle     | [./gradle/blade.portlet.configuration.icon](./gradle/blade.portlet.configuration.icon)      |
| Maven      | [./maven/blade.portlet.configuration.icon](./maven/blade.portlet.configuration.icon)        |

### Control Panel

__Extension point description__: 

__Template project description__: 

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.portlet.controlpanel](./liferay-gradle/blade.portlet.controlpanel) |
| BND Tools  | [./bndtools/blade.portlet.controlpanel](./bndtools/blade.portlet.controlpanel)  |
| Gradle     | [./gradle/blade.portlet.controlpanel](./gradle/blade.portlet.controlpanel)      |
| Maven      | [./maven/blade.portlet.controlpanel](./maven/blade.portlet.controlpanel)        |

### `PortletFilter`

__Extension point description__: 

__Template project description__:  Demonstrates how to apply `PortletFilter`s.

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.portlet.filter](./liferay-gradle/blade.portlet.filter) |
| BND Tools  | [./bndtools/blade.portlet.filter](./bndtools/blade.portlet.filter)  |
| Gradle     | [./gradle/blade.portlet.filter](./gradle/blade.portlet.filter)      |
| Maven      | [./maven/blade.portlet.filter](./maven/blade.portlet.filter)        |

### Toolbar Contributor

__Extension point description__: 

__Template project description__: 

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.portlet.toolbar.contributor](./liferay-gradle/blade.portlet.toolbar.contributor) |
| BND Tools  | [./bndtools/blade.portlet.toolbar.contributor](./bndtools/blade.portlet.toolbar.contributor)  |
| Gradle     | [./gradle/blade.portlet.toolbar.contributor](./gradle/blade.portlet.toolbar.contributor)      |
| Maven      | [./maven/blade.portlet.toolbar.contributor](./maven/blade.portlet.toolbar.contributor)        |

### Resource Bundle

__Extension point description__: 

__Template project description__: 

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.resourcebundle](./liferay-gradle/blade.resourcebundle) |
| BND Tools  | [./bndtools/blade.resourcebundle](./bndtools/blade.resourcebundle)  |
| Gradle     | [./gradle/blade.resourcebundle](./gradle/blade.resourcebundle)      |
| Maven      | [./maven/blade.resourcebundle](./maven/blade.resourcebundle)        |

### REST Service

__Extension point description__: Lets developers create custom JAX-RS standard
based RESTful services.

__Template project description__: Demonstrates how to create a JAX-RS service
that lists Liferay users.

**NOTE:** Before this service is accessible, the developer needs to configure
endpoints for it. To do so, go to the Control Panel &rarr; *System* &rarr;
*System Settings* &rarr; *Foundation* and then

* Search for CXF Endpoints
* Create a new *CXFEndpoint publisher configuration* providing a context path
  (e.g., `/rest-test`).
* Go back to *System Settings* &rarr; *Foundation* and select *REST Extender*.
* Create a new REST extender configuration (i.e., search with `rest`) providing
  context paths (e.g., `/rest-test`) and `jaxrs.applications.filters` set to
  `(jaxrs.application=true)`.

Then you can access the service via
[http://localhost:8080/o/rest-test/blade.users/list/](http://localhost:8080/o/rest-test/blade.users/list/).

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.rest](./liferay-gradle/blade.rest) |
| BND Tools  | [./bndtools/blade.rest](./bndtools/blade.rest)  |
| Gradle     | [./gradle/blade.rest](./gradle/blade.rest)      |
| Maven      | [./maven/blade.rest](./maven/blade.rest)        |

### Scheduler Entry

__Extension point description__: 

__Template project description__: 

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.schedulerentry](./liferay-gradle/blade.schedulerentry) |
| BND Tools  | [./bndtools/blade.schedulerentry](./bndtools/blade.schedulerentry)  |
| Gradle     | [./gradle/blade.schedulerentry](./gradle/blade.schedulerentry)      |
| Maven      | [./maven/blade.schedulerentry](./maven/blade.schedulerentry)        |

### `ServiceWrapper`

__Extension point description__: 

__Template project description__: Demonstrates how to wrap the
`UserLocalService` with custom a `UserLocalServiceWrapper`.

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.service.hook.user](./liferay-gradle/blade.service.hook.user) |
| BND Tools  | [./bndtools/blade.service.hook.user](./bndtools/blade.service.hook.user)  |
| Gradle     | [./gradle/blade.service.hook.user](./gradle/blade.service.hook.user)      |
| Maven      | [./maven/blade.service.hook.user](./maven/blade.service.hook.user)        |

### Service Builder

__Extension point description__: 

__Template project description__: Demonstrates how to create a Service Builder
project separated into four bundles:

* `api` bundle is for interfaces
* `svc` bundle is for implementations
* `test` bundle is for testing
* `web` bundle is a portlet calling the generated services 

__Template projects links__:

| Build tool | subproject   | Link to project's source code                                                   |
| ---------- | ------------ | ------------------------------------------------------------------------------- |
| Liferay Gradle | API          | [./liferay-gradle/blade.servicebuilder.api](./liferay-gradle/blade.servicebuilder.api)  |
| Liferay Gradle | Service      | [./liferay-gradle/blade.servicebuilder.svc](./liferay-gradle/blade.servicebuilder.svc)  |
| Liferay Gradle | Test         | [./liferay-gradle/blade.servicebuilder.test](./liferay-gradle/blade.servicebuilder.test)  |
| Liferay Gradle | Web          | [./liferay-gradle/blade.servicebuilder.web](./liferay-gradle/blade.servicebuilder.web)  |
| BND Tools  | API          | [./bndtools/blade.servicebuilder.api](./bndtools/blade.servicebuilder.api)  |
| BND Tools  | Service      | [./bndtools/blade.servicebuilder.svc](./bndtools/blade.servicebuilder.svc)  |
| BND Tools  | Test         | [./bndtools/blade.servicebuilder.test](./bndtools/blade.servicebuilder.test)  |
| BND Tools  | Web          | [./bndtools/blade.servicebuilder.web](./bndtools/blade.servicebuilder.web)  |
| Gradle     | API          | [./gradle/blade.servicebuilder.api](./gradle/blade.servicebuilder.api)  |
| Gradle     | Service      | [./gradle/blade.servicebuilder.svc](./gradle/blade.servicebuilder.svc)  |
| Gradle     | Test         | [./gradle/blade.servicebuilder.test](./gradle/blade.servicebuilder.test)  |
| Gradle     | Web          | [./gradle/blade.servicebuilder.web](./gradle/blade.servicebuilder.web)  |
| Maven      | All (parent) | [./maven/blade.servicebuilder](./maven/blade.servicebuilder)  |

### Simulation Panel

__Extension point description__: 

__Template project description__: 

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.simulation.panel.app](./liferay-gradle/blade.simulation.panel.app) |
| BND Tools  | [./bndtools/blade.simulation.panel.app](./bndtools/blade.simulation.panel.app)  |
| Gradle     | [./gradle/blade.simulation.panel.app](./gradle/blade.simulation.panel.app)      |
| Maven      | [./maven/blade.simulation.panel.app](./maven/blade.simulation.panel.app)        |

### `StrutsAction`

__Extension point description__: 

__Template project description__:  Demonstrates how to write a `StrutsAction`.

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.strutsaction](./liferay-gradle/blade.strutsaction) |
| BND Tools  | [./bndtools/blade.strutsaction](./bndtools/blade.strutsaction)  |
| Gradle     | [./gradle/blade.strutsaction](./gradle/blade.strutsaction)      |
| Maven      | [./maven/blade.strutsaction](./maven/blade.strutsaction)        |

### `StrutsPortletAction`

__Extension point description__: 

__Template project description__: Demonstrates how to write a
`StrutsPortletAction`.

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.strutsportletaction](./liferay-gradle/blade.strutsportletaction) |
| BND Tools  | [./bndtools/blade.strutsportletaction](./bndtools/blade.strutsportletaction)  |
| Gradle     | [./gradle/blade.strutsportletaction](./gradle/blade.strutsportletaction)      |
| Maven      | [./maven/blade.strutsportletaction](./maven/blade.strutsportletaction)        |

### Context Contributor

__Extension point description__: 

__Template project description__: 

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Gradle | [./liferay-gradle/blade.template.context.contributor](./liferay-gradle/blade.template.context.contributor) |
| BND Tools  | [./bndtools/blade.template.context.contributor](./bndtools/blade.template.context.contributor)  |
| Gradle     | [./gradle/blade.template.context.contributor](./gradle/blade.template.context.contributor)      |
| Maven      | [./maven/blade.template.context.contributor](./maven/blade.template.context.contributor)        |

## Liferay Extension Points Without Template Projects

This project does not yet have template projects for the following extension
points. We encourage you to contribute some!

* com.liferay.mail.util.Hook
* com.liferay.portal.kernel.atom.AtomCollectionAdapter
* com.liferay.portal.kernel.format.PhoneNumberFormat
* com.liferay.portal.kernel.lar.PortletDataHandler
* com.liferay.portal.kernel.lar.StagedModelDataHandler
* com.liferay.portal.kernel.lock.LockListener
* com.liferay.portal.kernel.notifications.UserNotificationHandler
* com.liferay.portal.kernel.pop.MessageListener
* com.liferay.portal.kernel.portlet.FriendlyURLMapper
* com.liferay.portal.kernel.portlet.PortletLayoutListener
* com.liferay.portal.kernel.sanitizer.Sanitizer 
* com.liferay.portal.kernel.scheduler.SchedulerEntry
* com.liferay.portal.kernel.scheduler.SchedulerEntry
* com.liferay.portal.kernel.search.Indexer
* com.liferay.portal.kernel.search.OpenSearch
* com.liferay.portal.kernel.servlet.URLEncoder
* com.liferay.portal.kernel.template.TemplateHandler
* com.liferay.portal.kernel.template.TemplateManager
* com.liferay.portal.kernel.trash.TrashHandler
* com.liferay.portal.kernel.upgrade.UpgradeProcess (call the Release service)
* com.liferay.portal.kernel.webdav.WebDAVStorage
* com.liferay.portal.kernel.workflow.WorkflowHandler
* com.liferay.portal.kernel.xmlrpc.Method
* com.liferay.portal.model.ModelListener
* com.liferay.portal.security.auth.AuthToken
* com.liferay.portal.security.auth.AuthVerifierConfiguration
* com.liferay.portal.security.auth.AutoLogin 
* com.liferay.portal.security.auth.EmailAddressGenerator
* com.liferay.portal.security.auth.EmailAddressValidator
* com.liferay.portal.security.auth.FullNameGenerator
* com.liferay.portal.security.auth.FullNameValidator
* com.liferay.portal.security.auth.ScreenNameGenerator
* com.liferay.portal.security.auth.ScreenNameValidator
* com.liferay.portal.security.ldap.AttributesTransformer
* com.liferay.portal.security.membershippolicy.OrganizationMembershipPolicy
* com.liferay.portal.security.membershippolicy.RoleMembershipPolicy
* com.liferay.portal.security.membershippolicy.SiteMembershipPolicy
* com.liferay.portal.security.membershippolicy.UserGroupMembershipPolicy
* com.liferay.portal.security.permission.BaseModelPermissionChecker
* com.liferay.portal.security.permission.PermissionPropagator
* com.liferay.portal.security.pwd.Toolkit
* com.liferay.portal.verify.VerifyProcess
* com.liferay.portlet.asset.model.AssetRendererFactory
* com.liferay.portlet.ControlPanelEntry
* com.liferay.portlet.ControlPanelEntry
* com.liferay.portlet.DefaultControlPanelEntryFactory
* com.liferay.portlet.dynamicdatamapping.render.DDMFormFieldRenderer
* com.liferay.portlet.dynamicdatamapping.util.DDMDisplay
* com.liferay.portlet.expando.model.CustomAttributesDisplay
* com.liferay.portlet.social.model.SocialActivityInterpreter
* com.liferay.portlet.social.model.SocialRequestInterpreter
* java.util.ResourceBundle
* javax.portlet.filter.ActionFilter
* javax.portlet.filter.EventFilter
* javax.portlet.filter.RenderFilter
* javax.portlet.filter.ResourceFilter
* javax.portlet.PreferencesValidator
* javax.servlet.Filter (Liferay InvokerFilterChain Filters)

## License
[License](/LICENSE.txt)

## Contribution

These templates can be copied freely and contributions are welcome. You can
contribute additional template samples by creating the project for one of the
four build tools and sending a pull request to `liferay/liferay-blade-samples`.
A repository admin will review the submission and replicate the project for the
other three build tools, once the submission is approved.

## References

[OSGI R5](http://www.osgi.org/Release5/HomePage)

[OSGI Compendium R5](http://www.osgi.org/Download/File?url=/download/r5/osgi.cmpn-5.0.0.pdf)

[Blueprint](http://www.eclipse.org/gemini/blueprint/)

[Bndtools](http://www.bndtools.org)
