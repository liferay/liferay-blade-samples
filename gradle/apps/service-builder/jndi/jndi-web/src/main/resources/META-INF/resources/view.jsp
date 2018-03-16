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

<p>
	<b><liferay-ui:message key="jndi-web.caption" /></b>
</p>

<%
UseJNDI.useJNDI();
%>

<liferay-ui:search-container delta="20" emptyResultsMessage="No data!">
	<liferay-ui:search-container-results results="<%= UseJNDI.getRegions() %>" />

	<liferay-ui:search-container-row className="com.liferay.blade.samples.jndiservicebuilder.model.Region" keyProperty="id" modelVar="region">
		<liferay-ui:search-container-column-text name="ID" value="<%= String.valueOf(region.getRegionId()) %>" />
		<liferay-ui:search-container-column-text name="Name" value="<%= region.getRegionName() %>" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>