<%--
/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
--%>

<%@ include file="/WEB-INF/jsp/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Foo foo = null;

if (row != null) {
	foo = (Foo)row.getObject();
}
else {
	foo = (Foo)request.getAttribute("edit_foo.jsp-foo");
}
%>

<liferay-ui:icon-menu icon="<%= StringPool.BLANK %>" message="<%= StringPool.BLANK %>" showExpanded="<%= row == null %>" showWhenSingleIcon="<%= row == null %>">
	<portlet:renderURL var="editURL">
		<portlet:param name="action" value="editFoo" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="fooId" value="<%= String.valueOf(foo.getFooId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		iconCssClass="icon-edit"
		message="edit"
		url="<%= editURL %>"
	/>

	<portlet:renderURL var="redirectURL">
		<portlet:param name="action" value="view" />
	</portlet:renderURL>

	<portlet:actionURL var="deleteURL">
		<portlet:param name="action" value="deleteFoo" />
		<portlet:param name="redirect" value="<%= (row == null) ? redirectURL : currentURL %>" />
		<portlet:param name="fooId" value="<%= String.valueOf(foo.getFooId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete url="<%= deleteURL %>" />
</liferay-ui:icon-menu>