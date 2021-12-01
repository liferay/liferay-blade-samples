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
	<b><liferay-ui:message key="bladenotifications.caption"/></b>
</p>

<portlet:actionURL name="/update_subscription" var="updateSubscriptionURL">
	<portlet:param name="mvcActionCommand" value="/update_subscription" />
</portlet:actionURL>

<portlet:actionURL name="/send_notification" var="sendNotificationURL">
	<portlet:param name="mvcActionCommand" value="/send_notification" />
</portlet:actionURL>

<aui:form action="<%= updateSubscriptionURL %>" method="post" name="fm" >
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= command %>" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:button-row>
				<aui:button type="submit" value="<%= command %>" />
			</aui:button-row>
		</aui:fieldset>
	</aui:fieldset-group>
</aui:form>

<aui:form action="<%= sendNotificationURL %>" method="post" name="fm2" >
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= BladeNotificationPortletKeys.NOTIFY %>" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:input type="text" name="<%= BladeNotificationPortletKeys.USER_EMAIL %>" />
			<aui:button-row>
				<aui:button type="submit" value="<%= BladeNotificationPortletKeys.NOTIFY %>" />
			</aui:button-row>
		</aui:fieldset>
	</aui:fieldset-group>
</aui:form>