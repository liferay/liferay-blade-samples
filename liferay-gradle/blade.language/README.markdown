# Language

## What does this sample accomplish?

The Language sample displays the Liferay recommended approach to sharing
language keys through OSGI services. This particular sample provides a resource
module which holds language keys.

## How does this sample work?

By default, our ResourceBundleLoaderAnalyzerPlugin expands modules with
content/Language.properties files to add a couple of provided capabilities:

bundle.symbolic.name
resource.bundle.base.name

Then, our deployed LanguageExtender scans modules with those capabilities to
automatically register an associated ResourceBundleLoader.

You can use this to leverage and use keys from common language modules
republishing an aggregate ResourceBundleLoader as your own in two different
ways:

1. Via Components

You can simply get a reference to the registered service in your components as
detailed in the Overriding a Modules Language Keys tutorial.

The main disadvantage of this approach is that it forces you to provide a
specific implementation of ResourceBundleLoader, making it potentially harder to
modularize in the future.

2. Via Provide Capability

The same LanguageExtender that registers the services supports an extended
syntax that allows you to register an aggregate of a collection of bundles as
your own declaratively.

-liferay-aggregate-resource-bundles: \
	blade.language

This last approach has the advantadge of easier extensibility. Only the common
lang modules need to be built and redeployed when keys change for all modules
using them to automatically pick up the changes while staying clear of
implementation details.

You should deploy this sample with BLADE Language Web module to get the entire
picture.