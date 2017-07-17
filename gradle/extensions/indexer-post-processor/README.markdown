# Indexer Post Processor

## What does this sample do when it's deployed?

The Indexer Post Processor sample demonstrates the use of the
`IndexerPostProcessor` interface, which is provided to customize search queries
and documents before they're sent to the search engine, and/or result summaries
when they're returned to end users. This basic demonstration prints a message in
the log when one of the `*IndexerPostProcessor` methods is called. 

To see the `blade.indexerpostprocessor` messages in Liferay's log, you must add
a logging category to the portal. Navigate to *Control Panel* &rarr;
*Configuration* &rarr; *Server Administration*, then click on *Log Levels*
&rarr; *Add Category*. Fill out the form:

*Logger Name*: `com.liferay.blade.samples.indexerpostprocessor`
*Log Level*: `INFO`

Save the new logging category.

## What API(s) and/or code components does this sample highlight?

This sample leverages the [IndexerPostProcessor](https://docs.liferay.com/ce/portal/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/search/IndexerPostProcessor.html) API.

## How does this sample leverage the API(s) and/or code component?

This sample contains multiple implementations of the `IndexerPostProcessor`
interface. All of the classes first leverage the interface as an OSGi service
via the `@Component` annotation. For example, the `@Component` of the `UserEntityIndexerPostProcessor` looks like this:

    @Component(
        immediate = true,
        property = {
            "indexer.class.name=com.liferay.portal.kernel.model.User",
            "indexer.class.name=com.liferay.portal.kernel.model.UserGroup"
        },
        service = IndexerPostProcessor.class
    )

There's one property provided via the `@Component` annotation:

- `indexer.class.name`: the fully qualified class name of the indexed entity or
an `Indexer` class itself.

This sample implements the `IndexerPostProcessor` interface, overriding
the following methods:

- `postProcessContextBooleanFilter`
- `postProcessContextQuery`
- `postProcessDocument`
- `postProcessFullQuery`
- `postProcessSearchQuery`
- `postProcessSearchQuery`
- `postProcessSummary`

For more information on Liferay's Search API, refer to the [Introduction to
Liferay Search](/developer/tutorials/-/knowledge_base/7-0/introduction-to-liferay-search)
tutorial.
