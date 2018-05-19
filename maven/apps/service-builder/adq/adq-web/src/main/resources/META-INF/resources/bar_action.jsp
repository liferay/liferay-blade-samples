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

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

Bar bar = null;

if (row != null) {
	bar = (Bar)row.getObject();
}
else {
	bar = (Bar)request.getAttribute("edit_bar.jsp-bar");
}
%>

<liferay-ui:icon-menu icon="<%= StringPool.BLANK %>" message="<%= StringPool.BLANK %>" showExpanded="<%= row == null %>" showWhenSingleIcon="<%= row == null %>">
	<portlet:renderURL var="editURL">
		<portlet:param name="mvcPath" value="/edit_bar.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="barId" value="<%= String.valueOf(bar.getBarId()) %>" />
	</portlet:renderURL>

	<liferay-ui:icon
		iconCssClass="icon-edit"
		message="edit"
		url="<%= editURL %>"
	/>

	<portlet:renderURL var="redirectURL">
		<portlet:param name="mvcPath" value="/view.jsp" />
	</portlet:renderURL>

	<portlet:actionURL var="deleteURL">
		<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.DELETE %>" />
		<portlet:param name="redirect" value="<%= (row == null) ? redirectURL : currentURL %>" />
		<portlet:param name="barId" value="<%= String.valueOf(bar.getBarId()) %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete url="<%= deleteURL %>" />
</liferay-ui:icon-menu>