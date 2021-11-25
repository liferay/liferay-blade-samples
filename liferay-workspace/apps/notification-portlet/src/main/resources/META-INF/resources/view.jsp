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
	<liferay-ui:message key="notification.caption" />
</p>

<%
String clearNotificationMessage = (String)renderRequest.getAttribute("CLEAR_NOTIFICATION_MESSAGE");
int userNotificationCount = (int)renderRequest.getAttribute("USER_NOTIFICATION_COUNT");
String userNotificationMessage = (String)renderRequest.getAttribute("USER_NOTIFICATION_MESSAGE");
%>

<c:if test="<%= userNotificationMessage != null %>">
	<p>
		<b><%= userNotificationMessage %></b>
	</p>
</c:if>

<p>
	<%= "Notifications created so far: <b>" + String.valueOf(userNotificationCount) + "</b>" %>
</p>

<liferay-portlet:actionURL name="notify" var="notifyURL" />

<aui:form action="<%= notifyURL %>" method="post" name="fm">
	<aui:button-row>
		<aui:button type="submit" value="Send"></aui:button>
	</aui:button-row>
</aui:form>

<liferay-portlet:actionURL name="clear" var="clearURL" />

<br />

<c:if test="<%= clearNotificationMessage != null %>">
	<p>
		<%= clearNotificationMessage %>
	</p>
</c:if>

<aui:form action="<%= clearURL %>" method="post" name="fm">
	<aui:button-row>
		<aui:button type="submit" value="Clear Notifications"></aui:button>
	</aui:button-row>
</aui:form>