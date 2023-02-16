# Sample Projects for Liferay Portal

Liferay's sample projects provides bootstrap project environments for all major
build tools in common use for Java projects so that Liferay development can
start quickly and easily. These templates can be copied freely and contributions
are welcome. See the [Contribution Guidelines](#contribution-guidelines) for
more information on how to contribute new sample projects and/or documentation.

## Build Tools

The template projects are categorized under two build tools:

* `liferay-workspace` - A set of Liferay projects configured to work in a
  [Liferay Workspace](https://portal.liferay.dev/docs/7-2/reference/-/knowledge_base/r/liferay-workspace)
  environment.
* `liferay-workspace-test-integration-sample` - A Liferay project configured to 
  execute Integration Tests.
* `maven` - A set of Liferay projects that can be bootstrapped onto the *Maven*
  development environment.

## Usage

The projects follow the Liferay Workspace project style for gradle and maven.

* `liferay-workspace` - in `gradle.properties`, Set `liferay.workspace.product`
  to the target product version.
* `liferay-workspace-test-integration-sample` - You can find test project in
  modules/sample/sample-test folder. Then run `gradlew buildService testIntegration`
  in root folder of this sample workspace test project.
* `maven` - Set the corresponding `release.portal.bom` found in
  `dependencyManagement`

## Contribution Guidelines

When contributing a new sample, you **must** include accompanying documentation.

### Sample Code

You can contribute additional template samples by creating the project for one
of the possible build tools and sending a pull request to
`liferay/liferay-blade-samples`. A repository admin will review the submission
and replicate the project for the other supported build tools, once the submission
is approved.

### Sample Docs

You can contribute documentation by adding a `README.markdown` file to the root
folder of a sample project. This article should include the following sections:

- *What does this sample do when it's deployed?*
- *What API(s) and/or code components does this sample highlight?*
- *How does this sample leverage the API(s) and/or code component?*

For example, the
[Resource Bundle Override](https://portal.liferay.dev/docs/7-2/reference/-/knowledge_base/r/resource-bundle-override)
article explains the `resource-bundle-override` sample using the aforementioned
sections.

## Frameworks

The template projects also demonstrate how to use various frameworks:

* Blueprint
* Declarative Services (**DS**)
* OSGi API

### A Note on Blueprint

Liferay does not provide a Blueprint implementation out of the box. To use the
Blueprint modules provided in Blade, you must deploy a Blueprint implementation
such as [Apache Aries - Blueprint](http://aries.apache.org/modules/blueprint.html).
Three bundles are needed:

* [Apache Aries Blueprint Bundle](http://mvnrepository.com/artifact/org.apache.aries.blueprint/org.apache.aries.blueprint/1.1.0)
* [Apache Aries Blueprint Annotation API](http://mvnrepository.com/artifact/org.apache.aries.blueprint/org.apache.aries.blueprint.annotation.api/1.0.1)
* [Apache Aries Proxy Bundle](http://mvnrepository.com/artifact/org.apache.aries.proxy/org.apache.aries.proxy/1.0.1)

Simply download the bundles from [mvnrepository](https://mvnrepository.com/) and
drop them in your `osgi/modules` folder before deploying Blueprint bundles.

## Liferay Extension Points and Template Projects

## Apps

### Action Command Portlet

**Template project description**: Demonstrates the `MVCActionCommand` extension
point. It integrates the action command named `greet` with portlet `greeter`. To
see how this example works, a portlet plugin with a portlet named **greeter**
(`javax.portlet.name='greeter'`) should be deployed. The command adds a key
`greeting_message` to Liferay `SessionMessages`, along with a session attribute
`GREETER_MESSAGE`. You can independently deploy the bundle
`blade.portlet.actioncommand` (i.e., refresh the bundle without the need to
redeploy the Portlet plugin).

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/apps/action-command-portlet](./liferay-workspace/apps/action-command-portlet)   |
| Maven      | [./maven/apps/action-command-portlet](./maven/apps/action-command-portlet)        |

### Blueprint Portlet

**Template project description**: Demonstrates how to use the Blueprint
framework for registering a Liferay MVC portlet.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/apps/blueprint-portlet](./liferay-workspace/apps/blueprint-portlet)   |
| Maven      | [./maven/apps/blueprint-portlet](./maven/apps/blueprint-portlet)        |

### Configuration Action

**Template project description**: Demonstrates the `ConfigurationAction`
integration point.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/apps/configuration-action](./liferay-workspace/apps/configuration-action)   |
| Maven      | [./maven/apps/configuration-action](./maven/apps/configuration-action)          |

### Control Panel Portlet

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/apps/control-panel-portlet](./liferay-workspace/apps/control-panel-portlet)   |
| Maven      | [./maven/apps/control-panel-portlet](./maven/apps/control-panel-portlet)        |

### DS Portlet

**Template project description**: Demonstrates how to use the DS (Declarative
Services) framework for registering a Liferay MVC portlet.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/apps/ds-portlet](./liferay-workspace/apps/ds-portlet)   |
| Maven      | [./maven/apps/ds-portlet](./maven/apps/ds-portlet)        |

### Filter Portlet

**Template project description**:  Demonstrates how to apply `PortletFilter`s.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/apps/filter-portlet](./liferay-workspace/apps/filter-portlet)   |
| Maven      | [./maven/apps/filter-portlet](./maven/apps/filter-portlet)        |

### FreeMarker Portlet

**Template project description**:  Demonstrates a simple FreeMarker portlet.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/apps/freemarker-portlet](./liferay-workspace/apps/freemarker-portlet)   |
| Maven      | [./maven/apps/freemarker-portlet](./maven/apps/freemarker-portlet)        |

### Greedy Policy Option Portlet

Refer to this sample's Readmes for more information.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/apps/greedy-policy-option-portlet](./liferay-workspace/apps/greedy-policy-option-portlet)   |
| Maven      | [./maven/apps/greedy-policy-option-portlet](./maven/apps/greedy-policy-option-portlet)        |

### JSP Portlet

**Template project description**:  Demonstrates a simple JSP portlet.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/apps/jsp-portlet](./liferay-workspace/apps/jsp-portlet)   |
| Maven      | [./maven/apps/jsp-portlet](./maven/apps/jsp-portlet)        |

### JSP Portlet with Notification Framework

**Template project description**:  Demonstrates a simple JSP portlet and the usage of Liferay's Notification framework.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/apps/notification-portlet](./liferay-workspace/apps/notification-portlet)   |


### JSP WAR Portlet

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/wars/jsp-war-portlet](./liferay-workspace/wars/jsp-war-portlet)   |
| Maven      | [./maven/apps/jsp-portlet](./maven/apps/jsp-war-portlet)        |



### OSGi Portlet

**Template project description**: Demonstrates how to use the raw OSGI APIs for
registering a Liferay MVC portlet.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/apps/osgi-portlet](./liferay-workspace/apps/osgi-portlet)   |
| Maven      | [./maven/apps/osgi-portlet](./maven/apps/osgi-portlet)        |

### Render Command Portlet

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/apps/render-command-portlet](./liferay-workspace/apps/render-command-portlet)   |
| Maven      | [./maven/apps/render-command-portlet](./maven/apps/render-command-portlet)        |

### Resource Command Portlet

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/apps/resource-command-portlet](./liferay-workspace/apps/resource-command-portlet)   |
| Maven      | [./maven/apps/resource-command-portlet](./maven/apps/resource-command-portlet)

### REST

**Extension point description**: Lets developers create custom JAX-RS standard
based RESTful services.

**Template project description**: Demonstrates how to create a JAX-RS service
that lists Liferay users.

Then you can access the service via
[http://localhost:8080/o/users/list/](http://localhost:8080/o/rest-test/blade.users/list/).

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/apps/rest](./liferay-workspace/apps/rest)   |
| Maven      | [./maven/apps/rest](./maven/apps/rest)        |

### Sample Verifier

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/apps/sample-verifier](./liferay-workspace/apps/sample-verifier)   |
| Maven      | [./maven/apps/sample-verifier](./maven/apps/sample-verifier)        |

### Service Builder

#### ADQ

Refer to this sample's Readme for more information.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/apps/service-builder/adq](./liferay-workspace/apps/service-builder/adq)   |
| Maven  | [./maven/apps/service-builder/adq](./maven/apps/service-builder/adq)  |

#### Basic

**Template project description**: Demonstrates how to create a Service Builder
project separated into three bundles:

* `api` bundle is for interfaces
* `service` bundle is for implementations
* `web` bundle is a portlet calling the generated services

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/apps/service-builder/basic](./liferay-workspace/apps/service-builder/basic)   |
| Maven  | [./maven/apps/service-builder/basic](./maven/apps/service-builder/basic)  |

#### JDBC

Refer to this sample's Readme for more information.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/apps/service-builder/jdbc](./liferay-workspace/apps/service-builder/jdbc)   |
| Maven  | [./maven/apps/service-builder/jdbc](./maven/apps/service-builder/jdbc)  |

#### JNDI

Refer to this sample's Readme for more information.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/apps/service-builder/jndi](./liferay-workspace/apps/service-builder/jndi)   |
| Maven  | [./maven/apps/service-builder/jndi](./maven/apps/service-builder/jndi)  |

### Shared Language Keys

Refer to this sample's Readme for more information.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/apps/shared-language-keys](./liferay-workspace/apps/shared-language-keys)   |
| Maven      | [./maven/apps/shared-language-keys](./maven/apps/shared-language-keys)        |

### Simulation Panel App

Refer to this sample's Readme for more information.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/apps/simulation-panel-app](./liferay-workspace/apps/simulation-panel-app)   |
| Maven      | [./maven/apps/simulation-panel-app](./maven/apps/simulation-panel-app)        |

### Spring MVC Portlet

Refer to this sample's Readme for more information.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/wars/springmvc-portlet](./liferay-workspace/wars/springmvc-portlet)   |
| Maven      | [./maven/apps/springmvc-portlet](./maven/apps/springmvc-portlet)        |

## Ext

### Login Web Ext

Refer to this sample's Readme for more information.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/ext/login-web-ext](./liferay-workspace/ext/login-web-ext)   |

## Extensions

### Auth Failure

**Template project description**: Demonstrates a hook for `auth.failure` and
`auth.max.failures`.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/extensions/auth-failure](./liferay-workspace/extensions/auth-failure)   |
| Maven      | [./maven/extensions/auth-failure](./maven/extensions/auth-failure)          |

### Authenticator Shiro

**Template project description**: Uses Apache Shiro for hooking
`auth.pipeline.pre`.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/extensions/authenticator-shiro](./liferay-workspace/extensions/authenticator-shiro)   |
| Maven      | [./maven/extensions/authenticator-shiro](./maven/extensions/authenticator-shiro)          |

### Auto Login

**Template project description**: Demonstrates the `AutoLogin` integration
point.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/extensions/auto-login](./liferay-workspace/extensions/auto-login)   |
| Maven      | [./maven/extensions/auto-login](./maven/extensions/auto-login)          |

### Control Menu Entry

Refer to this sample's Readme for more information.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/extensions/control-menu-entry](./liferay-workspace/extensions/control-menu-entry)   |
| Maven      | [./maven/extensions/control-menu-entry](./maven/extensions/control-menu-entry)          |

### Doclib Resource Command Override

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/extensions/doclib-resource-command-override](./liferay-workspace/extensions/doclib-resource-command-override)   |
| Maven      | [./maven/extensions/doclib-resource-command-override](./maven/extensions/doclib-resource-command-override)        |

### Document Action

Refer to this sample's Readme for more information.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/extensions/document-action](./liferay-workspace/extensions/document-action)   |
| Maven      | [./maven/extensions/document-action](./maven/extensions/document-action)        |

### Gogo Command

Refer to this sample's Readme for more information.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/extensions/gogo](./liferay-workspace/extensions/gogo)   |
| Maven      | [./maven/extensions/gogo](./maven/extensions/gogo)          |

### Index Settings Contributor

Refer to this sample's Readme for more information.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/extensions/index-settings-contributor](./liferay-workspace/extensions/index-settings-contributor)   |
| Maven      | [./maven/extensions/index-settings-contributor](./maven/extensions/index-settings-contributor)          |

### Indexer Post Processor

Refer to this sample's Readme for more information.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/extensions/indexer-post-processor](./liferay-workspace/extensions/indexer-post-processor)   |
| Maven      | [./maven/extensions/indexer-post-processor](./maven/extensions/indexer-post-processor)        |

### Lifecycle Login Pre-action

**Extension point description**: Demonstrates how to implement a Liferay
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

**Template project description**:  Demonstrates a hook for `login.events.pre`.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/extensions/lifecycle-login-preaction](./liferay-workspace/extensions/lifecycle-login-preaction)   |
| Maven      | [./maven/extensions/lifecycle-login-preaction](./maven/extensions/lifecycle-login-preaction)        |

### Model Listener

Refer to this sample's Readme for more information.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/extensions/model-listener](./liferay-workspace/extensions/model-listener)   |
| Maven      | [./maven/extensions/model-listener](./maven/extensions/model-listener)        |

### Poll Processor

**Template project description**: Demonstrates how to make a hook for a
`PollerProcessor`.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/extensions/poll-processor](./liferay-workspace/extensions/poll-processor)   |
| Maven      | [./maven/extensions/poll-processor](./maven/extensions/poll-processor)        |

### Portlet Configuration Icon

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/extensions/portlet-configuration-icon](./liferay-workspace/extensions/portlet-configuration-icon)   |
| Maven      | [./maven/extensions/portlet-configuration-icon](./maven/extensions/portlet-configuration-icon)        |

### Portlet Toolbar Contributor

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/extensions/portlet-toolbar-contributor](./liferay-workspace/extensions/portlet-toolbar-contributor)   |
| Maven      | [./maven/extensions/portlet-toolbar-contributor](./maven/extensions/portlet-toolbar-contributor)        |

### Resource Bundle

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/extensions/resource-bundle](./liferay-workspace/extensions/resource-bundle)   |
| Maven      | [./maven/extensions/resource-bundle](./maven/extensions/resource-bundle)        |

### Screen Name Validator

Refer to this sample's Readme for more information.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/extensions/screen-name-validator](./liferay-workspace/extensions/screen-name-validator)   |
| Maven      | [./maven/extensions/screen-name-validator](./maven/extensions/screen-name-validator)        |

### Search Keyword Query Contributor

Refer to this sample's Readme for more information.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/extensions/search-keyword-query-contributor](./liferay-workspace/extensions/search-keyword-query-contributor) |
| Maven      | [./maven/extensions/search-keyword-query-contributor](maven/extensions/search-keyword-query-contributor) |

### Search Model Pre-Filter Contributor

Refer to this sample's Readme for more information.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/extensions/search-model-pre-filter-contributor](./liferay-workspace/extensions/search-model-pre-filter-contributor)   |
| Maven      | [./maven/extensions/search-model-pre-filter-contributor](./maven/extensions/search-model-pre-filter-contributor)

### Servlet

Refer to this sample's Readme for more information.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/extensions/servlet](./liferay-workspace/extensions/servlet)   |
| Maven      | [./maven/extensions/servlet](./maven/extensions/servlet)        |

### User Service Wrapper

**Template project description**: Demonstrates how to wrap the
`UserLocalService` with custom a `UserLocalServiceWrapper`.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/user-service-wrapper](./liferay-workspace/user-service-wrapper)   |
| Maven      | [./maven/user-service-wrapper](./maven/user-service-wrapper)        |

## Overrides

### Module JSP Override

Refer to this sample's Readme for more information.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/overrides/module-jsp-override](./liferay-workspace/overrides/module-jsp-override)   |
| Maven      | [./maven/overrides/module-jsp-override](./maven/overrides/module-jsp-override)          |

### Portlet Form Taglib Override

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/overrides/portlet-form-taglib-override](./liferay-workspace/overrides/portlet-form-taglib-override)   |
| Maven      | [./maven/overrides/portlet-form-taglib-override](./maven/overrides/portlet-form-taglib-override)          |

## Themes

### Simple Theme

Refer to this sample's Readme for more information.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/wars/simple-theme](./liferay-workspace/wars/simple-theme)   |
| Maven      | [./maven/themes/simple-theme](./maven/themes/simple-theme)        |

### Template Context Contributor

Refer to this sample's Readme for more information.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/themes/template-context-contributor](./liferay-workspace/themes/template-context-contributor)   |
| Maven      | [./maven/themes/template-context-contributor](./maven/themes/template-context-contributor)        |

### Theme Contributor

Refer to this sample's Readme for more information.

| Build tool | Link to project's source code                                                   |
| ---------- | ------------------------------------------------------------------------------- |
| Liferay Workspace | [./liferay-workspace/themes/theme-contributor](./liferay-workspace/themes/theme-contributor)   |
| Maven      | [./maven/themes/theme-contributor](./maven/themes/theme-contributor)        |

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
* com.liferay.portal.kernel.portlet.PortletLayoutListener
* com.liferay.portal.kernel.sanitizer.Sanitizer
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

## References

[OSGI R5](http://www.osgi.org/Release5/HomePage)

[OSGI Compendium R5](http://www.osgi.org/Download/File?url=/download/r5/osgi.cmpn-5.0.0.pdf)

[Blueprint](http://www.eclipse.org/gemini/blueprint/)
