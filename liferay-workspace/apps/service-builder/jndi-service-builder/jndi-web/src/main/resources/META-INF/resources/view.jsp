<%@ include file="/init.jsp" %>

<p>
	<b><liferay-ui:message key="jndi-web.caption"/></b>
</p>

<%
UseJNDI.useJNDI();
%>

<liferay-ui:search-container delta="20" emptyResultsMessage="No data!">
	<liferay-ui:search-container-results results="<%= UseJNDI.getRegions() %>" />
	<liferay-ui:search-container-row className="com.liferay.blade.samples.jndiservicebuilder.model.Region" keyProperty="id" modelVar="region" >
		<liferay-ui:search-container-column-text name="ID" value="<%= String.valueOf(region.getRegionId()) %>" />
		<liferay-ui:search-container-column-text name="Name" value="<%= region.getRegionName() %>" />
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator />
</liferay-ui:search-container>
