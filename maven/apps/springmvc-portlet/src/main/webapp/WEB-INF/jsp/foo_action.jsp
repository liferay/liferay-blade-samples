<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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