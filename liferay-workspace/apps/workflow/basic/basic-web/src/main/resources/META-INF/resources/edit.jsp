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

long bazId = ParamUtil.getLong(request, "bazId");

Baz baz = null;

if (bazId > 0) {
	baz = bazLocalService.getBaz(bazId);
}
%>

<aui:form action="<%= (javax.portlet.ActionURL)renderResponse.createActionURL() %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= (baz == null) ? Constants.ADD : Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="bazId" type="hidden" value="<%= bazId %>" />
	<aui:input name="groupId" type="hidden" value='<%= BeanParamUtil.getLong(baz, request, "groupId", scopeGroupId) %>' />

	<liferay-ui:header
		backURL="<%= redirect %>"
		title='<%= (baz != null) ? String.valueOf(baz.getPrimaryKey()) : "new-baz" %>'
	/>

	<aui:model-context bean="<%= baz %>" model="<%= Baz.class %>" />

	<aui:button-row>
		<aui:button type="submit" />

		<aui:button href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>