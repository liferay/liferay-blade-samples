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
	<b><liferay-ui:message key="-web.caption" /></b>
</p>

<%
UseJDBC.useJDBC();
%>

<liferay-ui:search-container delta="20" emptyResultsMessage="No data!">
	<liferay-ui:search-container-results results="<%= UseJDBC.getCountries() %>" />

	<liferay-ui:search-container-row className="com.liferay.blade.samples.jdbcservicebuilder.model.Country" keyProperty="id" modelVar="country">
		<liferay-ui:search-container-column-text name="ID" value="<%= String.valueOf(country.getCountryId()) %>" />
		<liferay-ui:search-container-column-text name="Name" value="<%= country.getCountryName() %>" />
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>