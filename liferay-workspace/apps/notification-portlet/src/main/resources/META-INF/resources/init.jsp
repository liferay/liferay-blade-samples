<%@ page import="com.liferay.portal.kernel.service.SubscriptionLocalService" %>

<%@ page import="com.liferay.portal.kernel.exception.PortalException" %>
<%@ page import="com.liferay.portal.kernel.model.Subscription" %>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.blade.sample.notifications.portlet.BladeNotificationPortlet" %>
<%@ page import="com.liferay.blade.sample.notifications.constants.BladeNotificationPortletKeys" %>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>


<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
    SubscriptionLocalService subscriptionLocalService = (SubscriptionLocalService) renderRequest.getAttribute("subscriptionLocalService");
%>