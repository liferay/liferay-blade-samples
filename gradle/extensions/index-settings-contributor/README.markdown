# Index Settings Contributor

This sample shows how to add a custom type mapping on Liferay Portal.

Liferay's search engine provides an API to define custom mappings. To use it:

1. Define the new mapping.
In this sample, the mapping is defined on `META-INF/mappings/resources/index-type-mappings.json` file.
Notice that the default document on Liferay is called `LiferayDocumentType`.
The mapping's features can be found at [elastic search's docs](https://www.elastic.co/guide/en/elasticsearch/reference/current/mapping.html).

2. Put the mapping into Elasticsearch.
The `IndexSettingsContributor` components are invoked during the reindexing and receive a `TypeMappingsHelper` as a hook to add new mappings.
In this sample, this feature is implemented by `IndexSettingsContributor` class that reads the `.json` add its content to Liferay's default index.