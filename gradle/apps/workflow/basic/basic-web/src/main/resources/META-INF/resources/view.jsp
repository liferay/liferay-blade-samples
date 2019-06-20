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

<strong><liferay-ui:message key="welcome-to-the-blade-workflow-basic-web" /></strong>

<aui:button-row>
	<portlet:renderURL var="editBazURL">
		<portlet:param name="mvcPath" value="/edit.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
	</portlet:renderURL>

	<aui:button href="<%= editBazURL %>" value="add-baz" />
</aui:button-row>

<liferay-ui:search-container
	total="<%= bazLocalService.getBazsCount() %>"
>
	<liferay-ui:search-container-results
		results="<%= bazLocalService.getBazs(searchContainer.getStart(), searchContainer.getEnd()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.blade.workflow.basic.model.Baz"
		escapedModel="true"
		modelVar="baz"
	>
		<liferay-ui:search-container-column-text
			name="id"
			property="bazId"
			valign="top"
		/>

		<liferay-ui:search-container-column-status
			name="status"
			status="<%= baz.getStatus() %>"
			statusByUserId="<%= baz.getStatusByUserId() %>"
			statusDate="<%= baz.getStatusDate() %>"
		/>

		<liferay-ui:search-container-column-jsp
			cssClass="entry-action"
			path="/action.jsp"
			valign="top"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>