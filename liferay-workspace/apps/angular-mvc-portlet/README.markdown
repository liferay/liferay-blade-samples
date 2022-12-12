# Angular MVC Portlet

The Angular MVC Portlet sample shows how a native 
[@angular/cli](https://angular.io/cli) project can be tweaked to be able to
deploy it to DXP as a portlet.

This sample is suited for older DXP versions without Client Extensions support
since a better and simpler strategy for DXP 7.4+ would be using a Custom Element
Client Extension project, like in the
[angular-remote-app sample](../angular-remote-app).


## What does this sample do when it's deployed?

This sample registers an MVC portlet in DXP that uses Angular to render its
user interface. Thus, it packages the default sample code provided by 
[@angular/cli](https://angular.io/cli) as a DXP deployable artifact.

The main technologies on which this sample is based are:

  1. [DXP's MVC portlet](https://help.liferay.com/hc/en-us/articles/360029028191-Liferay-MVC-Portlet)
  2. [Custom elements](https://developer.mozilla.org/en-US/docs/Web/Web_Components/Using_custom_elements)
  3. [Angular CLI's build system](https://angular.io/cli)

A good understanding of the three is necessary to understand what is going on 
under the hood.


## Detailed explanation on how this sample was constructed

There are three main necessary steps to construct this sample:

  1. Creating a sample `@angular/cli` project
  2. Tweaking the project so that it implements a custom element
  3. Integrating the source code into an MVC portlet project

Let's see these steps in details in the following sections.


### Creating a sample `@angular/cli` project

This is as easy as it seems: simply follow the standard Angular [documentation
for the new command](https://angular.io/cli/new) of `@angular/cli`.

That will create a sample project that renders the following UI:

![Angular's default example UI](./screenshots/default-angular-example-ui.png)

By default, `@angular/cli` configures the `package.json` scripts so that the
developer can use:

  - `build`: to create a SPA application deployable to any web server
  - `start`: to launch a local server to do live development

These targets assume that you will be deploying the application as a SPA.

Now let’s move on to see how we can adapt this project to be deployed as a
portlet.


### Tweaking the project so that it implements a custom element

The main difference between a SPA and a portlet is that the former assumes full
control of the HTML while the latter must attach to an existing HTML node when
it is bootstrapped.

The default Angular bootstrap process attaches your Angular application to an
HTML node by means of an ID DOM selector (that's `selector` field of
`@Component`'s annotation). 

That can be seen in the generated `AppComponent` class:

```typescript
@Component({
  selector: 'app-root',
})
export class AppComponent {
  // ...
}
```

Unfortunately, this technique is not generalizable for portlet platforms, since
it is possible to add a portlet more than once to the same page, which would
make the IDs collide. Thus, we need some other way to attach the Angular
application to the HTML.

Even though there are more ways to attach Angular applications to HTML nodes, we
will leverage the standard custom elements specification to make this process 
easier.

Basically, we are going to convert our Angular project from a SPA to a
JavaScript bundle that registers a custom element with the browser. 

The modifications we have done to the Angular default example are:

  1. Define a `title` property in AppComponent to parametrize the custom element
  2. Tell Angular not to bootstrap the application as a SPA
  3. Tell Angular to create a custom entry point to bootstrap the application
  4. Implement the custom bootstrap process that registers the custom element

Step 1 adds this code to `AppComponent`:

```typescript
import { Component, Input } from '@angular/core';

export class AppComponent {
  @Input('title') title = 'angular-mvc-portlet';
}
```

Steps 2 and 3 change the `AppModule` code removing the `bootstrap` field of the
`@Component` annotation and adding `entryComponents` instead:


```typescript
@NgModule({
  entryComponents: [
    AppComponent
  ],
})
export class AppModule {
}
```

Step 4 adds the custom bootstrap code:


```typescript
import { Injector, NgModule } from '@angular/core';
import { createCustomElement } from '@angular/elements';

export class AppModule {
	constructor(private injector: Injector) {}

	ngDoBootstrap() {
		const AppComponentElement = createCustomElement(AppComponent, {
			injector: this.injector
		});

		customElements.define('app-component', AppComponentElement);
	}
}
```

You may want to review this
[blog post](https://medium.com/@lavanya.kakimallaiah/creating-a-custom-element-in-angular-aa2a794fd041)
(although it is a bit outdated) explaining all this in detail. It needs some
modifications to the code examples to make it work, but it explains clearly what
needs to be done.

Note that the blog post is not tied to any specific platform, which makes it
suitable for DXP (without needing any specific Liferay tooling) because it is
based on a standard specification (namely custom elements).

There’s also 
[Angular’s custom elements guide](https://angular.io/guide/elements) which is a
valuable resource, too.


### Integrating the source code into an MVC portlet project

With the previous changes, whenever we invoke Angular's build script it will
generate some JavaScript and CSS files in the `build` directory that can be
added to any HTML page to define the custom element in the browser.

Then we can place the custom element tag (`<app-component>`) in any place of the
HTML and the custom element will be rendered.

Since we don't have control of the main page HTML in DXP, because it is
generated by the platform, we need to find a way to make those JavaScript and
CSS files arrive to that main page, as well as make the `<app-component>` tag be
rendered.

The obvious way to do that is by means of a portlet, which is to DXP as custom
element is to the browser (since you can compose a page using portlets). Thus, 
we can
[create a sample MVC portlet](https://help.liferay.com/hc/en-us/articles/360028833552-MVC-Portlet-Template)
with `blade`, then tweak it by adding the Angular's project sample code and
making it generate its build output in the proper place too, so that the Java
build can then continue to create the MVC portlet JAR file including the files
built by Angular.

We need to follow these steps to do all that:

  1. Copy Angular's JSON files to the MVC portlet project root folder
  2. Tweak those files to change Angular's build output
  3. Copy Angular's source code to the `src/main/angular` folder
  4. Add Angular's build output files to the portlet's headers
  5. Use the `<app-component>` tag to render the portlet in `view.jsp`

Steps 1 and 2 copy the `package.json`, `angular.json`, `tsconfig.app.json`,
`tsconfig.json`, and `tsconfig.spec.json` files and modifies them like this:

  1. `package.json`:
      1. Adding `--output-hashing=none` to `ng build` so that output files don't
         show a hash in their names and are predictable.
      2. Commenting the `test` script because otherwise it is invoked by the
         Java build and, since it launches a browser in the default example, it
         blocks the build.
  2. `angular.json`:
      1. Change the location of the JSON schema to a proper place (usually 
         `../../node_modules/@angular/cli/lib/config/schema.json` since the
         project is inside a Liferay Workspace, and workspaces hoist the common
         dependencies to the top level `node_modules` folder.
      2. Change the build's `outputPath` to 
         `build/resources/main/META-INF/resources/angular` so that Angular's
         build puts its output there for `gradle` to take over and put it inside
         the final JAR file.
  3. `angular.json`, `tsconfig.json` and `tsconfig.spec.json`:
      1. Change all references to folders inside `src` to `src/main/angular`,
         since that is the place where we have moved Angular source files.

Step 3 copies all files that Angular default example puts inside the `src`
folder to the `src/main/angular` folder of the MVC portlet project.

Step 4 adds the path to Angular's build output files as properties of the
`AngularMvcPortlet` class `@Component` annotation, like this:

```java
@Component(
	property = {
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.header-portlet-css=/angular/styles.css",
		"com.liferay.portlet.header-portlet-javascript=/angular/runtime.js",
		"com.liferay.portlet.header-portlet-javascript=/angular/polyfills.js",
		"com.liferay.portlet.header-portlet-javascript=/angular/main.js",
	}
)
public class AngularMvcPortlet extends MVCPortlet {
}
```

Note that you must manually update the list of files if modifications to your
sources make Angular's build output change.

Step 5 changes the `view.jsp` file like this:

```jsp
<p>
	<app-component title="Hello World!"></app-component>
</p>
```

This makes the portlet be rendered by the custom element implementation, in the
browser, instead of being rendered in the server by means of JSP content.


## Conclusion

You can use this sample to deploy Angular projects to any version of DXP as long
as your browser supports custom elements.

As stated above, there is a better way to make projects that are deployable to
DXP and use the same techiques explained here for DXP versions 7.4 or higher,
since they support client extensions.

Also note that, if you add multiple Angular portlets to the same page you will
end up having several Angular copies in the browser's JavaScript engine. This is
inherent to the modularized nature of custom elements and, although there have
been proposals for ways to deduplicate shared code, there's no clear standard
yet to do it, so you must plan your strategy for future maintenance taking that
into account.
