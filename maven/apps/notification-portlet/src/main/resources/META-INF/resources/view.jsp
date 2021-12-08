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
Subscription subscription = subscriptionLocalService.fetchSubscription(user.getCompanyId(), user.getUserId(), BladeNotificationPortlet.class.getName(), 0);
boolean subscribed = false;
String command = Constants.SUBSCRIBE;

if (subscription != null) {
	subscribed = true;
	command = Constants.UNSUBSCRIBE;
}
%>

<p>
	<b><liferay-ui:message key="bladenotifications.caption" /></b>
</p>

<portlet:actionURL name="/update_subscription" var="updateSubscriptionURL">
	<portlet:param name="mvcActionCommand" value="/update_subscription" />
</portlet:actionURL>

<portlet:actionURL name="/send_notification" var="sendNotificationURL">
	<portlet:param name="mvcActionCommand" value="/send_notification" />
</portlet:actionURL>

<aui:form action="<%= updateSubscriptionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= command %>" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:button-row>
				<aui:button type="submit" value="<%= command %>" />
			</aui:button-row>
		</aui:fieldset>
	</aui:fieldset-group>
</aui:form>

<aui:form action="<%= sendNotificationURL %>" method="post" name="fm2">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= BladeNotificationPortletKeys.NOTIFY %>" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:input name="<%= BladeNotificationPortletKeys.USER_EMAIL %>" type="text" />

			<aui:button-row>
				<aui:button type="submit" value="<%= BladeNotificationPortletKeys.NOTIFY %>" />
			</aui:button-row>
		</aui:fieldset>
	</aui:fieldset-group>
</aui:form>