<%@ include file="/init.jsp" %>

<aui:script require="metaljs-portlet@1.0.0">
	metaljsPortlet100.default('<portlet:namespace />-button');
</aui:script>

<button id="<portlet:namespace />-button">
	Click me to open a superb modal dialog!
</button>
