<%@ include file="/init.jsp" %>

<aui:script require="vuejs-portlet@1.0.0">
	vuejsPortlet100.default('<portlet:namespace />');
</aui:script>

<hr>

<div id="<portlet:namespace />-1">
	<p>A friendly reversible message from Vue.js:</p>
	<p>{{message}}</p>
	<button v-on:click="reverseMessage">Reverse message, pretty please</button>
</div>

<hr>

<div id="<portlet:namespace />-2">
	<p>A to do list made with Vue.js components:</p>
  <ol>
    <todo-item
      v-for="item in groceryList"
      v-bind:todo="item"
      v-bind:key="item.id">
    </todo-item>
  </ol>
</div>
