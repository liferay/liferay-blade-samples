# Blade InfoListProvider

InfoListProviders are a way of programmatically filtering content, e.g. for AssetPublisher.
On the user interface they appear as "Content Set Providers".

This sample component is the slightly enhanced (localizable) component from
[Liferay's documentation](https://portal.liferay.dev/docs/7-2/frameworks/-/knowledge_base/f/creating-an-information-list-provider)

Rename package and class to make it yours.

It seems that Liferay takes the fully qualified classname as key, so make sure you create unique
classes if you create multiple InfoListProviders.

Note: There used to be an earlier version of InfoListProvider in a different package.
If the package referenced in this sample doesn't exist for you, you might need to update your BOM
configuration with (for example) the following value:

 * `liferay.workspace.target.platform.version = 7.2.10.1` (for a minimum version of DXP 7.2 SP1)
 * `liferay.workspace.target.platform.version = 7.2.1` (for a minimum version of Liferay CE 7.2.1 GA2)

(see [LPS-110075](https://issues.liferay.com/browse/LPS-110075) for background)

As this sample was created long after the availability of DXP 7.2 SP1 or CE 7.2.1 GA2, the author
hasn't checked for the exact time of package change for the class.