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

<strong><liferay-ui:message key="welcome-to-the-blade-spring-mvc-service-builder-web" /></strong>

<aui:button-row>
	<portlet:renderURL var="addFooURL">
		<portlet:param name="action" value="addFoo" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
	</portlet:renderURL>

	<aui:button href="<%= addFooURL %>" value="add-foo" />
</aui:button-row>

<liferay-ui:search-container
	total="<%= FooLocalServiceUtil.getFoosCount() %>"
>
	<liferay-ui:search-container-results
		results="<%= FooLocalServiceUtil.getFoos(searchContainer.getStart(), searchContainer.getEnd()) %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.blade.samples.servicebuilder.model.Foo"
		escapedModel="true"
		modelVar="foo"
	>
		<liferay-ui:search-container-column-text
			name="id"
			property="fooId"
			valign="top"
		/>

		<liferay-ui:search-container-column-text
			name="field1"
			valign="top"
		>
			<strong><%= foo.getField1() %></strong>

			<br />

			<div class="lfr-asset-categories">
				<liferay-ui:asset-categories-summary
					className="<%= Foo.class.getName() %>"
					classPK="<%= foo.getFooId() %>"
				/>
			</div>

			<div class="lfr-asset-tags">
				<liferay-ui:asset-tags-summary
					className="<%= Foo.class.getName() %>"
					classPK="<%= foo.getFooId() %>"
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
			value="<%= foo.getField4() %>"
		/>

		<liferay-ui:search-container-column-text
			property="field5"
			valign="top"
		/>

		<liferay-ui:search-container-column-jsp
			cssClass="entry-action"
			path="/WEB-INF/jsp/foo_action.jsp"
			valign="top"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>