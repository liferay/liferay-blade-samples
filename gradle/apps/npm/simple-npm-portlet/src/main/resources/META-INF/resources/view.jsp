<%@ include file="/init.jsp" %>

<aui:script require="simple-npm-portlet@1.0.0">
	window.out = document.getElementById('<portlet:namespace />-output');

	out.innerHTML += 'Portlet main module loaded.\n';
	out.innerHTML += "Invoking portlet's main module default export.\n";

	simpleNpmPortlet100.default();
</aui:script>

<pre id="<portlet:namespace />-output">
</pre>