<%@taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.commerce.product.catalog.CPCatalogEntry" %><%@
page import="com.liferay.commerce.product.catalog.CPSku" %><%@
page import="com.liferay.commerce.product.content.constants.CPContentWebKeys" %><%@
page import="com.liferay.commerce.product.content.util.CPContentHelper" %>

<%
CPContentHelper cpContentHelper = (CPContentHelper)request.getAttribute(CPContentWebKeys.CP_CONTENT_HELPER);

CPCatalogEntry cpCatalogEntry = cpContentHelper.getCPCatalogEntry(request);

CPSku cpSku = cpContentHelper.getDefaultCPSku(cpCatalogEntry);

long cpDefinitionId = cpCatalogEntry.getCPDefinitionId();
%>

<h1>Example Product Renderer</h1>

<c:if test="<%= cpSku != null %>">
	<h3><%= "SKU: " + cpSku.getSku() %></h3>

	<h3><%= "Price: " + cpSku.getPrice().toString() %></h3>

	<h3><%= "Availability: " + cpContentHelper.getAvailabilityLabel(request) %></h3>

	<h3><%= "Stock Quantity: " + cpContentHelper.getStockQuantityLabel(request) %></h3>
</c:if>

<liferay-util:dynamic-include key="com.liferay.commerce.product.content.web#/add_to_cart#" />