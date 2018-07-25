# Model Pre Filter Contributor [](id=model-pre-filter-contributor)

This sample demonstrates how to include a pre-filter parameters to search queries on Liferay Portal.

## What does this sample do when it's deployed? [](id=what-does-this-sample-do-when-its-deployed)

This sample change the all the queries search to pre-filter all entries created after 01/01/2018.

## What API(s) and/or code components does this sample highlight? [](id=what-apis-and-or-code-components-does-this-sample-highlight)

This sample leverages the
[ModelPreFilterContributor](https://github.com/liferay/liferay-portal/blob/master/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/query/contributor/ModelPreFilterContributor.java)
API.

## How does this sample leverage the API(s) and/or code component? [](id=how-does-this-sample-leverage-the-apis-and-or-code-component)

This sample conveys the recommended approach to adding a pre-filter, which may contribute to the relevance of the search, in keyword queries.

To achieve this goal, you need to create a component that implements the 
`com.liferay.portal.search.spi.model.query.contributor.ModelPreFilterContributor`.

Specifically, you must implement the `contribute` method, which is invoked when a user enters something in the search bars:

```.java
@Override
public void contribute(
    BooleanFilter booleanFilter, ModelSearchSettings modelSearchSettings,
    SearchContext searchContext) {

    RangeTermFilter rangeTermFilter = new RangeTermFilter(
        Field.CREATE_DATE, true, true, "20180101000000", null);

    booleanFilter.add(rangeTermFilter, BooleanClauseOccur.SHOULD);
}
```

Also, it is important to highlight the `@Component` annotation that register a new service to the OSGI:

```.java
@Component(
	immediate = true,
	property = "indexer.class.name=ALL",
	service = com.liferay.portal.search.spi.model.query.contributor.ModelPreFilterContributor.class
)
```

## Where Is This Sample? [](id=where-is-this-sample)

There are three different versions of this sample, each built with a different build tool:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/extensions/search-model-pre-filter-contributor)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/master/liferay-workspace/extensions/search-model-pre-filter-contributor)
- [Maven](https://github.com/liferay/liferay-blade-samples/tree/master/maven/extensions/search-model-pre-filter-contributor)