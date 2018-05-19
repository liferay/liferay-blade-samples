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

<strong><liferay-ui:message key="welcome-to-the-blade-service-builder-adq-web" /></strong>

<aui:button-row>
	<portlet:renderURL var="editBarURL">
		<portlet:param name="mvcPath" value="/edit_bar.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
	</portlet:renderURL>

	<portlet:renderURL var="massUpdateURL">
		<portlet:param name="mvcPath" value="/mass_update.jsp" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
	</portlet:renderURL>

	<aui:button href="<%= editBarURL %>" value="add-bar" />
	<aui:button href="<%= massUpdateURL %>" value="mass-update" />
</aui:button-row>

<liferay-ui:search-container
	total="<%= barLocalService.getBarsCount() %>"
>
	<liferay-ui:search-container-results
		results="<%= barLocalService.getBars(searchContainer.getStart(), searchContainer.getEnd()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.blade.samples.servicebuilder.adq.model.Bar"
		escapedModel="true"
		modelVar="bar"
	>
		<liferay-ui:search-container-column-text
			name="id"
			property="barId"
			valign="top"
		/>

		<liferay-ui:search-container-column-text
			name="field1"
			valign="top"
		>
			<strong><%= bar.getField1() %></strong>

			<br />

			<div class="lfr-asset-categories">
				<liferay-ui:asset-categories-summary
					className="<%= Bar.class.getName() %>"
					classPK="<%= bar.getBarId() %>"
				/>
			</div>

			<div class="lfr-asset-tags">
				<liferay-ui:asset-tags-summary
					className="<%= Bar.class.getName() %>"
					classPK="<%= bar.getBarId() %>"
					message="tags"
				/>
			</div>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			property="field2"
			valign="top"
		/>

		<liferay-ui:search-container-column-text
			property="field3"
			valign="top"
		/>

		<liferay-ui:search-container-column-date
			name="field4"
			valign="top"
			value="<%= bar.getField4() %>"
		/>

		<liferay-ui:search-container-column-text
			property="field5"
			valign="top"
		/>

		<liferay-ui:search-container-column-jsp
			cssClass="entry-action"
			path="/bar_action.jsp"
			valign="top"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>