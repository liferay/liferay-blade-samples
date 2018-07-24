<%@ include file="/init.jsp" %>

<div id="<portlet:namespace />"></div>

<aui:script require="<%= loaderRequire %>">
	loader.default('#<portlet:namespace />', '<%= mainRequire %>');
</aui:script>