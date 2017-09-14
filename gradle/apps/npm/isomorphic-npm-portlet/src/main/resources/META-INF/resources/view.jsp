<%@ include file="/init.jsp" %>

<aui:script require="isomorphic-npm-portlet@1.0.0">
	debugger
	var out = document.getElementById('<portlet:namespace />-output');

	out.innerHTML += 'Portlet main module loaded.\n';
	out.innerHTML += "Invoking portlet's main module default export.\n";
	out.innerHTML += '\n';

	isomorphicNpmPortlet100.default({
		log: function(msg) {
			out.innerHTML += msg + '\n';
		}
	});
</aui:script>

<pre id="<portlet:namespace />-output">
</pre>
