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

<strong><liferay-ui:message key="welcome-to-the-blade-workflow-asset-web" /></strong>

<aui:button-row>
	<portlet:renderURL var="editQuxURL">
		<portlet:param name="mvcPath" value="/edit.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
	</portlet:renderURL>

	<aui:button href="<%= editQuxURL %>" value="add-qux" />
</aui:button-row>

<liferay-ui:search-container
	total="<%= quxLocalService.getQuxsCount() %>"
>
	<liferay-ui:search-container-results
		results="<%= quxLocalService.getQuxs(searchContainer.getStart(), searchContainer.getEnd()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.blade.workflow.asset.model.Qux"
		escapedModel="true"
		modelVar="qux"
	>
		<liferay-ui:search-container-column-text
			name="id"
			property="quxId"
			valign="top"
		/>

		<liferay-ui:search-container-column-status
			name="status"
			status="<%= qux.getStatus() %>"
			statusByUserId="<%= qux.getStatusByUserId() %>"
			statusDate="<%= qux.getStatusDate() %>"
		/>

		<liferay-asset:asset-categories-summary
			className="<%= Qux.class.getName() %>"
			classPK="<%= qux.getQuxId() %>"
		/>

		<liferay-asset:asset-tags-summary
			className="<%= Qux.class.getName() %>"
			classPK="<%= qux.getQuxId() %>"
		/>

		<liferay-ui:search-container-column-jsp
			cssClass="entry-action"
			path="/action.jsp"
			valign="top"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>