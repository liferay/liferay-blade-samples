# Search Query Contributor [](id=search-query-contributor)

## What does this sample do when it's deployed? [](id=what-does-this-sample-do-when-its-deployed)

This sample allows searching for blog entries based on the caption value of the blog image.

## What API(s) and/or code components does this sample highlight? [](id=what-apis-and-or-code-components-does-this-sample-highlight)

This sample leverages the
[KeywordQueryContributor](https://github.com/liferay/liferay-portal/blob/master/modules/apps/portal-search/portal-search-spi/src/main/java/com/liferay/portal/search/spi/model/query/contributor/KeywordQueryContributor.java)
API.

## How does this sample leverage the API(s) and/or code component? [](id=how-does-this-sample-leverage-the-apis-and-or-code-component)

This sample conveys the recommended approach to adding a field, which may contribute to the relevance of the search, in keyword queries.

To achieve this goal, you need to create a component that implements the 
`com.liferay.portal.search.spi.model.query.contributor.KeywordQueryContributor` and contains a `QueryHelper`.

Specifically, you must implement the `contribute` method, which is invoked when a user enters something in the search bars:

    @Override
    public void contribute(
        String keywords, BooleanQuery booleanQuery,
        KeywordQueryContributorHelper keywordQueryContributorHelper) {
git
        SearchContext searchContext =
            keywordQueryContributorHelper.getSearchContext();

        queryHelper.addSearchLocalizedTerm(
            booleanQuery, searchContext, Field.CAPTION, true);
    }

Also, it is important to highlight the `@Component` annotation that register a new service to the OSGI:

    @Component(
        immediate = true, property = "indexer.class.name=ALL",
        service = KeywordQueryContributor.class
    )


## Where Is This Sample? [](id=where-is-this-sample)

There are three different versions of this sample, each built with a different build tool:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/extensions/search-query-contributor)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/master/liferay-workspace/extensions/search-query-contributor)
- [Maven](https://github.com/liferay/liferay-blade-samples/tree/master/maven/extensions/search-query-contributor)
