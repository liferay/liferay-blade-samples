<%@ include file="/init.jsp" %>

<p>
	<b><liferay-ui:message key="-web.caption"/></b>
</p>

<%
UseJDBC.useJDBC();
%>

<liferay-ui:search-container delta="20" emptyResultsMessage="No data!">
	<liferay-ui:search-container-results results="<%= UseJDBC.getCountries() %>" />
	<liferay-ui:search-container-row className="com.liferay.blade.samples.jdbcservicebuilder.model.Country" keyProperty="id" modelVar="country" >
		<liferay-ui:search-container-column-text name="ID" value="<%= String.valueOf(country.getCountryId()) %>" />
		<liferay-ui:search-container-column-text name="Name" value="<%= country.getCountryName() %>" />
	</liferay-ui:search-container-row>
	<liferay-ui:search-iterator />
</liferay-ui:search-container>
