# Search Model Pre Filter Contributor [](id=model-pre-filter-contributor)

This sample demonstrates how to include pre-filter parameters to search queries on Liferay Portal.

Steps to test this feature:
- Add the "Search Bar" and "Search Results" portlets to a web page;
- Try to search by a blog's entry on the search bar portlet using the title value. All matched entries must be displayed on search results portlet.
- Deploy the module.
- Try to search by a blog's entry on the search bar portlet using the title value. Only entries created on the last hour must be displayed on search results portlet.

## What does this sample do when it's deployed? [](id=what-does-this-sample-do-when-its-deployed)

This sample changes the all the search queries to pre-filter all entries created on the last hour.

## What API(s) and/or code components does this sample highlight? [](id=what-apis-and-or-code-components-does-this-sample-highlight)

This sample leverages the
[ModelPreFilterContributor](https://github.com/liferay/liferay-portal/blob/master/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/query/contributor/ModelPreFilterContributor.java)
API.

## How does this sample leverage the API(s) and/or code component? [](id=how-does-this-sample-leverage-the-apis-and-or-code-component)

This sample demonstrates how to include parameters to search queries, which are used to pre-filter the search results, in keyword queries.

To achieve this goal, you need to create a component that implements the
`com.liferay.portal.search.spi.model.query.contributor.ModelPreFilterContributor`.

Specifically, you must implement the `contribute` method, which is invoked when a user enters something in the search bars:

```.java
@Override
public void contribute(
    BooleanFilter booleanFilter, ModelSearchSettings modelSearchSettings,
    SearchContext searchContext) {

    RangeTermFilter rangeTermFilter = new RangeTermFilter(
        Field.CREATE_DATE, true, true, "now-1h", null);

    booleanFilter.add(rangeTermFilter, BooleanClauseOccur.MUST);
}
```

Notice that we are using a [date math expression](https://www.elastic.co/guide/en/elasticsearch/reference/current/date-math-index-names.html) as lower boundary parameter to `RangeTermFilter`.
But, it supports string that represents a date on `ỳyyyMMddHHmmss` [format](https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html) too.

Also, it is important to highlight the `@Component` annotation that register a new service to the OSGI:

```.java
@Component(
	immediate = true,
	property = "indexer.class.name=ALL",
	service = com.liferay.portal.search.spi.model.query.contributor.ModelPreFilterContributor.class
)
```

To chose between implementing a `KeywordQueryContributor` or a `ModelPreFilterContributor`
consider these below items:
- Filters are cached and don't influence the score, therefore faster than queries.
- Query is usually something that the users type and pretty much unpredictable, while filters help users narrowing down the search results , for example using facets.

For more information read [Elasticsearch's documentation](https://www.elastic.co/guide/en/elasticsearch/guide/master/_queries_and_filters.html).

## Where Is This Sample? [](id=where-is-this-sample)

There are three different versions of this sample, each built with a different build tool:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/7.1/gradle/extensions/search-model-pre-filter-contributor)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/7.1/liferay-workspace/extensions/search-model-pre-filter-contributor)
- [Maven](https://github.com/liferay/liferay-blade-samples/tree/7.1/maven/extensions/search-model-pre-filter-contributor)