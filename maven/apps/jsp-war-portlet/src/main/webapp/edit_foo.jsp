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
String redirect = ParamUtil.getString(request, "redirect");

long fooId = ParamUtil.getLong(request, "fooId");

Foo foo = null;

if (fooId > 0) {
	foo = fooLocalService.getFoo(fooId);
}
%>

<portlet:actionURL name="updateFoo" var="updateURL">
	<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.UPDATE %>" />
</portlet:actionURL>

<aui:form action="<%= updateURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= foo == null ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="fooId" type="hidden" value="<%= fooId %>" />

	<liferay-ui:header
		backURL="<%= redirect %>"
		title='<%= (foo != null) ? foo.getField1() : "new-foo" %>'
	/>

	<liferay-ui:asset-categories-error />

	<liferay-ui:asset-tags-error />

	<aui:model-context bean="<%= foo %>" model="<%= Foo.class %>" />

	<aui:fieldset>
		<aui:input name="field1" />

		<aui:input name="field2" />

		<aui:input name="field3" />

		<aui:input name="field4" />

		<aui:input name="field5" />

		<liferay-ui:custom-attributes-available className="<%= Foo.class.getName() %>">
			<liferay-ui:custom-attribute-list
				className="<%= Foo.class.getName() %>"
				classPK="<%= (foo != null) ? foo.getFooId() : 0 %>"
				editable="<%= true %>"
				label="<%= true %>"
			/>
		</liferay-ui:custom-attributes-available>

		<aui:input name="categories" type="assetCategories" />

		<aui:input name="tags" type="assetTags" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>