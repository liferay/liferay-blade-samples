# Bootstrap Liferay Advanced Developer Environments(**BLADE**)

[![Build Status](https://travis-ci.org/rotty3000/blade.svg?branch=master)](https://travis-ci.org/rotty3000/blade)
[![Join the chat at https://gitter.im/rotty3000/blade](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/rotty3000/blade?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

This small experiment aims to provide bootstrap project environments for all the major build tools in common use for Java projects so that Liferay development can start quickly and easily.



# Related build tools and frameworks 


## Build tools

The template projects are categorized based on the build tools,

* `gradle` - these are set of Liferay projects that can be bootstrapped on to `gradle` based development environment
* `liferay-gradle` - these are set of Liferay projects that can be bootstrapped on to `com.liferay.plugin`(a gradle plugin) based development environment
* `maven` - these are set of Liferay projects that can be bootstrapped on to `maven` development environment
* `bndtools` - these are set of Liferay projects that can be bootstrapped on to `bndtools` based development environment


### A note on BNDTools

There are 2 additional projects in the `/bndtools/` folder that you will need to build and publish the modules

* `/bndtools/cnf` - The Bndtools configuration project
* `/bndtools/blade.run` - A bndtools project which can push bundles into an osgi container and run them, need biz.aQute.remote.agent-X.X.X.jar deployed first.


## Frameworks

The projects also demonstrate how to use various frameworks like:

* Blueprint
* Declarative Services(**DS**)
* OSGI API


### A note on Blueprint

Liferay does not provide a blueprint implementation out of the box. To use the blueprint modules provided in blade, you must deploy a blueprint implementation such as [Apache Aries - Blueprint](http://aries.apache.org/modules/blueprint.html). Three bundles are needed:

* [Apache Aries Blueprint Bundle](http://mvnrepository.com/artifact/org.apache.aries.blueprint/org.apache.aries.blueprint/1.1.0)
* [Apache Aries Blueprint Annotation API](http://mvnrepository.com/artifact/org.apache.aries.blueprint/org.apache.aries.blueprint.annotation.api/1.0.1)
* [Apache Aries Proxy Bundle](http://mvnrepository.com/artifact/org.apache.aries.proxy/org.apache.aries.proxy/1.0.1)

Simply download the bundles from mvnrepository and drop them in your osgi/modules folder as usual before deploying blueprint bundles.



# Liferay extension points and template projects 


## `auth.pipeline.pre` 

__Extension point description__: 

__Template project description__: Uses Apache Shiro for hooking `auth.pipeline.pre`

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay-gradle | [./liferay-gradle/blade.authenticator.shiro](./liferay-gradle/blade.authenticator.shiro) |
| BND Tools  | [./bndtools/blade.authenticator.shiro](./bndtools/blade.authenticator.shiro)    |
| Gradle     | [./gradle/blade.authenticator.shiro](./gradle/blade.authenticator.shiro)        |
| Maven      | [./maven/blade.authenticator.shiro](./maven/blade.authenticator.shiro)          |


## `auth.failure` and `auth.max.failures`

__Extension point description__: 

__Template project description__: Demonstrates a hook for `auth.failure` and `auth.max.failures`

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay-gradle | [./liferay-gradle/blade.authfailure](./liferay-gradle/blade.authfailure) |
| BND Tools  | [./bndtools/blade.authfailure](./bndtools/blade.authfailure)    |
| Gradle     | [./gradle/blade.authfailure](./gradle/blade.authfailure)        |
| Maven      | [./maven/blade.authfailure](./maven/blade.authfailure)          |


## `ConfigurationAction`

__Extension point description__: 

__Template project description__: demonstrates integration point of `ConfigurationAction`

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay-gradle | [./liferay-gradle/blade.configurationaction](./liferay-gradle/blade.configurationaction) |
| BND Tools  | [./bndtools/blade.configurationaction](./bndtools/blade.configurationaction)    |
| Gradle     | [./gradle/blade.configurationaction](./gradle/blade.configurationaction)        |
| Maven      | [./maven/blade.configurationaction](./maven/blade.configurationaction)          |


## `FriendlyURLMapper`

__Extension point description__: This extension point allows one to provide (or overwrite) friendly URL mapping for portlets.

__Template project description__: demonstrates how to create a FriendlyURLMapper for the standard `NetworkUtilities` portlet.

`NetworkUtilities` portlet does not provide friendly URLs out of the box. To test this plugin put a `NetworkUtilities` portlet on a page.
If for example that was the home page, after deploying this plugin you can access its tabs directly using the following urls:

* http://localhost:8080/web/guest/home/-/NetworkUtilities/dns-lookup
* http://localhost:8080/web/guest/home/-/NetworkUtilities/whois

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay-gradle | [./liferay-gradle/blade.friendlyurl](./liferay-gradle/blade.friendlyurl) |
| BND Tools  | [./bndtools/blade.friendlyurl](./bndtools/blade.friendlyurl)    |
| Gradle     | [./gradle/blade.friendlyurl](./gradle/blade.friendlyurl)        |
| Maven      | [./maven/blade.friendlyurl](./maven/blade.friendlyurl)          |


## Gogo command

__Extension point description__: 

__Template project description__: demonstrates felix gogo commands and consuming Liferay services through DS (Declarative Services)

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay-gradle | [./liferay-gradle/blade.gogo](./liferay-gradle/blade.gogo) |
| BND Tools  | [./bndtools/blade.gogo](./bndtools/blade.gogo)    |
| Gradle     | [./gradle/blade.gogo](./gradle/blade.gogo)        |
| Maven      | [./maven/blade.gogo](./maven/blade.gogo)          |


## `IndexerPostProcessor`

__Extension point description__: 

__Template project description__:  demonstrates how to create custom IndexerPostProcessor.

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay-gradle | [./liferay-gradle/blade.indexerpostprocessor](./liferay-gradle/blade.indexerpostprocessor) |
| BND Tools  | [./bndtools/blade.indexerpostprocessor](./bndtools/blade.indexerpostprocessor)      |
| Gradle     | [./gradle/blade.indexerpostprocessor](./gradle/blade.indexerpostprocessor)      |
| Maven      | [./maven/blade.indexerpostprocessor](./maven/blade.indexerpostprocessor)        |


## JSP hook

__Extension point description__: 

__Template project description__:  demonstrates a jsp hook for `login.jsp` in `com.liferay.login.web` bundle via fragment bundle.

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay-gradle | [./liferay-gradle/blade.hook.jsp](./liferay-gradle/blade.hook.jsp) |
| BND Tools  | [./bndtools/blade.hook.jsp](./bndtools/blade.hook.jsp)        |
| Gradle     | [./gradle/blade.hook.jsp](./gradle/blade.hook.jsp)        |
| Maven      | [./maven/blade.hook.jsp](./maven/blade.hook.jsp)          |


## `login.events.pre`

__Extension point description__:  This example demonstrates how to implement a Liferay `com.liferay.portal.kernel.events.LifecycleAction`. This API replaces all the legacy lifecycle events such as `com.liferay.portal.kernel.events.Action`, `com.liferay.portal.kernel.events.SessionAction`, and `com.liferay.portal.kernel.events.SimpleAction`.
Connecting an LifecycleAction to a particular event is determined by the OSGi service property `key`. The following keys are supported:

* `application.shutdown.events` - fired during destruction of company instances at portal shutdown
* `application.startup.events` - fired during initialization of company instances at portal start, or when a new instance is created
* `global.shutdown.events` - fired during destruction of the portal's main servlet
* `global.startup.events` - fire during initialization of the portal's main servlet
* `layout.configuration.action.delete` - fired during destruction of a page (Layout)
* `layout.configuration.action.update` - fired during initialization of a page (Layout)
* `login.events.post` - fired immediately following login
* `login.events.pre` - fired immediately prior to login
* `logout.events.post` - fired immediately following logout
* `logout.events.pre` - fired immediately prior to logout
* `servlet.service.events.post` - fired following requests to the portal (including all portlet container requests)
* `servlet.service.events.pre` - fired prior to requests to the portal (including all portlet container requests & post login)
* `servlet.session.create.events` - fired during creation of a portal's http session
* `servlet.session.destroy.events` - fired during destruction of a portal's http session

__Template project description__:  this example demonstrates a hook for `login.events.pre`.

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay-gradle | [./liferay-gradle/blade.lifecycle.loginpreaction](./liferay-gradle/blade.lifecycle.loginpreaction) |
| BND Tools  | [./bndtools/blade.lifecycle.loginpreaction](./bndtools/blade.lifecycle.loginpreaction)      |
| Gradle     | [./gradle/blade.lifecycle.loginpreaction](./gradle/blade.lifecycle.loginpreaction)      |
| Maven      | [./maven/blade.lifecycle.loginpreaction](./maven/blade.lifecycle.loginpreaction)        |


## `ModelListener`

__Extension point description__: Model Listeners are used to listen for events on models and do something in response.

__Template project description__:  Demonstrates how to create a model listener for `Layout`

After deploying this plugin the title of any newly created page will be automatically set to "Title generated by model listener!"


__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay-gradle | [./liferay-gradle/blade.modellistener](./liferay-gradle/blade.modellistener) |
| BND Tools  | [./bndtools/blade.modellistener](./bndtools/blade.modellistener)  |
| Gradle     | [./gradle/blade.modellistener](./gradle/blade.modellistener)      |
| Maven      | [./maven/blade.modellistener](./maven/blade.modellistener)        |


## `MVCActionCommand`

__Extension point description__: 

__Template project description__:  Demonstrates extension point of `MVCActionCommand`. It integrates the action command named `greet` with portlet `greeter`.

To see how this example works, a portlet plugin with a portlet named **greeter** (`javax.portlet.name='greeter'`) is to be deployed.  The command adds a key `greeting_message` to Liferay SessionMessages, along with a session attribute `GREETER_MESSAGE`. You can independently deploy the bundle `blade.portlet.actioncommand` a.k.a refresh the bundle without the need to redeploy the Portlet plugin.

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay-gradle | [./liferay-gradle/blade.portlet.actioncommand](./liferay-gradle/blade.portlet.actioncommand) |
| BND Tools  | [./bndtools/blade.portlet.actioncommand](./bndtools/blade.portlet.actioncommand)  |
| Gradle     | [./gradle/blade.portlet.actioncommand](./gradle/blade.portlet.actioncommand)      |
| Maven      | [./maven/blade.portlet.actioncommand](./maven/blade.portlet.actioncommand)        |


## `PollerProcessor`

__Extension point description__: 

__Template project description__:  demonstrates how to make a hook for `PollerProcessor`.

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay-gradle | [./liferay-gradle/blade.pollprocessor](./liferay-gradle/blade.pollprocessor) |
| BND Tools  | [./bndtools/blade.pollprocessor](./bndtools/blade.pollprocessor)      |
| Gradle     | [./gradle/blade.pollprocessor](./gradle/blade.pollprocessor)      |
| Maven      | [./maven/blade.pollprocessor](./maven/blade.pollprocessor)        |


## Portlet 

__Extension point description__: 

Below are examples of buildeing portles using diferent frameworks: 


### Liferay MVC Portlet - using Blueprint framework

__Template project description__:  demonstrates how to use Blueprint framework for registering a portlet.

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay-gradle | [./liferay-gradle/blade.portlet.blueprint](./liferay-gradle/blade.portlet.blueprint) |
| BND Tools  | [./bndtools/blade.portlet.blueprint](./bndtools/blade.portlet.blueprint)  |
| Gradle     | [./gradle/blade.portlet.blueprint](./gradle/blade.portlet.blueprint)      |
| Maven      | [./maven/blade.portlet.blueprint](./maven/blade.portlet.blueprint)        |


### Liferay MVC Portlet - using DS (Declarative Services) framework

__Template project description__:  demonstrates how to use DS (Declarative Services) framework for registering a portlet.

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay-gradle | [./liferay-gradle/blade.portlet.ds](./liferay-gradle/blade.portlet.ds) |
| BND Tools  | [./bndtools/blade.portlet.ds](./bndtools/blade.portlet.ds)  |
| Gradle     | [./gradle/blade.portlet.ds](./gradle/blade.portlet.ds)      |
| Maven      | [./maven/blade.portlet.ds](./maven/blade.portlet.ds)        |


### Liferay MVC Portlet - using raw OSGI APIs

__Template project description__:  demonstrates how to use the raw OSGI APIs for registering a portlet.

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay-gradle | [./liferay-gradle/blade.portlet.osgiapi](./liferay-gradle/blade.portlet.osgiapi) |
| BND Tools  | [./bndtools/blade.portlet.osgiapi](./bndtools/blade.portlet.osgiapi)  |
| Gradle     | [./gradle/blade.portlet.osgiapi](./gradle/blade.portlet.osgiapi)      |
| Maven      | [./maven/blade.portlet.osgiapi](./maven/blade.portlet.osgiapi)        |


### JSF Portlet - using DS (Declarative Services) framework

__Template project description__:  demonstrates how to use the DS (Declarative Services) for registering a JSF portlet

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay-gradle | [./liferay-gradle/blade.jsf.portlet.ds](./liferay-gradle/blade.jsf.portlet.ds) |
| BND Tools  | [./bndtools/blade.jsf.portlet.ds](./bndtools/blade.jsf.portlet.ds)  |
| Gradle     | [./gradle/blade.jsf.portlet.ds](./gradle/blade.jsf.portlet.ds)      |
| Maven      | [./maven/blade.jsf.portlet.ds](./maven/blade.jsf.portlet.ds)        |

### JSP Portlet 

__Extension point description__: 

__Template project description__:  demonstrates a simple jsp portlet.

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay-gradle | [./liferay-gradle/blade.portlet.jsp](./liferay-gradle/blade.portlet.jsp) |
| BND Tools  | [./bndtools/blade.portlet.jsp](./bndtools/blade.portlet.jsp) |
| Gradle     | [./gradle/blade.portlet.jsp](./gradle/blade.portlet.jsp)      |
| Maven      | [./maven/blade.portlet.jsp](./maven/blade.portlet.jsp)        |


## `PortletFilter`

__Extension point description__: 

__Template project description__:  demonstrates how to apply PortletFilters

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay-gradle | [./liferay-gradle/blade.portlet.filter](./liferay-gradle/blade.portlet.filter) |
| BND Tools  | [./bndtools/blade.portlet.filter](./bndtools/blade.portlet.filter)  |
| Gradle     | [./gradle/blade.portlet.filter](./gradle/blade.portlet.filter)      |
| Maven      | [./maven/blade.portlet.filter](./maven/blade.portlet.filter)        |


## REST service

__Extension point description__: Allows to create custom, JAX-RS standard based restfull services   

__Template project description__:  demonstrates how to create a JAX-RS service that list Liferay users

NOTE: Before this service is accessible, one needs to configure endpoints for it. To do so, go to 
`Control Panel > System > System Settings > Foundation` and then

* Search for CXF Endpoints
* create new `CXFEndpoint publisher configuration` providing `Context path` (say `/rest-test`)
* Go back to `System Settings > Foundation` and select `REST Extender`
* create new `Rest extender configuration` (search with `rest`) providing `Context paths` (say `/rest-test`) and `jaxrs.applications.filters` set to: `(jaxrs.application=true)`

Then you can access the service via http://localhost:8080/o/rest-test/blade.users/list/

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay-gradle | [./liferay-gradle/blade.rest](./liferay-gradle/blade.rest) |
| BND Tools  | [./bndtools/blade.rest](./bndtools/blade.rest)  |
| Gradle     | [./gradle/blade.rest](./gradle/blade.rest)      |
| Maven      | [./maven/blade.rest](./maven/blade.rest)        |


## Service Builder

__Extension point description__: 

__Template project description__:  demonstrates how to create a Service Builder project separated to 4 bundles: 

* api bundle for interface
* svc bundle for implementation
* test bundle for testing
* web bundle is a portlet calling generated services 

__Template projects links__:

| Build tool | subproject   | Link to project's source code                                                   |
| ---------- | ------------ | ------------------------------------------------------------------------------- |
| Liferay-gradle | API          | [./liferay-gradle/blade.servicebuilder.api](./liferay-gradle/blade.servicebuilder.api)  |
| Liferay-gradle | Service      | [./liferay-gradle/blade.servicebuilder.svc](./liferay-gradle/blade.servicebuilder.svc)  |
| Liferay-gradle | Test         | [./liferay-gradle/blade.servicebuilder.test](./liferay-gradle/blade.servicebuilder.test)  |
| Liferay-gradle | Web          | [./liferay-gradle/blade.servicebuilder.web](./liferay-gradle/blade.servicebuilder.web)  |
| BND Tools  | API          | [./bndtools/blade.servicebuilder.api](./bndtools/blade.servicebuilder.api)  |
| BND Tools  | Service      | [./bndtools/blade.servicebuilder.svc](./bndtools/blade.servicebuilder.svc)  |
| BND Tools  | Test         | [./bndtools/blade.servicebuilder.test](./bndtools/blade.servicebuilder.test)  |
| BND Tools  | Web          | [./bndtools/blade.servicebuilder.web](./bndtools/blade.servicebuilder.web)  |
| Gradle     | API          | [./gradle/blade.servicebuilder.api](./gradle/blade.servicebuilder.api)  |
| Gradle     | Service      | [./gradle/blade.servicebuilder.svc](./gradle/blade.servicebuilder.svc)  |
| Gradle     | Test         | [./gradle/blade.servicebuilder.test](./gradle/blade.servicebuilder.test)  |
| Gradle     | Web          | [./gradle/blade.servicebuilder.web](./gradle/blade.servicebuilder.web)  |
| Maven      | All (parent) | [./maven/blade.servicebuilder](./maven/blade.servicebuilder)  |


## `ServiceWrapper`

__Extension point description__: 

__Template project description__:  demonstrates how to wrap UserLocalService with custom UserLocalServiceWrapper

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay-gradle | [./liferay-gradle/blade.service.hook.user](./liferay-gradle/blade.service.hook.user) |
| BND Tools  | [./bndtools/blade.service.hook.user](./bndtools/blade.service.hook.user)  |
| Gradle     | [./gradle/blade.service.hook.user](./gradle/blade.service.hook.user)      |
| Maven      | [./maven/blade.service.hook.user](./maven/blade.service.hook.user)        |


## `StrutsAction`

__Extension point description__: 

__Template project description__:  demonstrates how to write StrutsAction

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay-gradle | [./liferay-gradle/blade.strutsaction](./liferay-gradle/blade.strutsaction) |
| BND Tools  | [./bndtools/blade.strutsaction](./bndtools/blade.strutsaction)  |
| Gradle     | [./gradle/blade.strutsaction](./gradle/blade.strutsaction)      |
| Maven      | [./maven/blade.strutsaction](./maven/blade.strutsaction)        |


## `StrutsPortletAction`

__Extension point description__: 

__Template project description__:  demonstrates how to write StrutsPortletAction

__Template projects links__:

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay-gradle | [./liferay-gradle/blade.strutsportletaction](./liferay-gradle/blade.strutsportletaction) |
| BND Tools  | [./bndtools/blade.strutsportletaction](./bndtools/blade.strutsportletaction)  |
| Gradle     | [./gradle/blade.strutsportletaction](./gradle/blade.strutsportletaction)      |
| Maven      | [./maven/blade.strutsportletaction](./maven/blade.strutsportletaction)        |



# Liferay extension points without template projects

This project does not yet have template projects for the following extension points. We encourage you to contribute some!  

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



# License
[License](/LICENSE.txt)

# Contribution

These templates can be copied freely and contributions are welcome. You can
contribute additional template samples by creating the project for one of the
four build tools and sending a pull request to `liferay/liferay-blade-samples`.
A repository admin will review the submission and replicate the project for the
other three build tools, once the submission is approved.

# References

[OSGI R5](http://www.osgi.org/Release5/HomePage)

[OSGI Compendium R5](http://www.osgi.org/Download/File?url=/download/r5/osgi.cmpn-5.0.0.pdf)

[Blueprint](http://www.eclipse.org/gemini/blueprint/)

[Bndtools](http://www.bndtools.org)
