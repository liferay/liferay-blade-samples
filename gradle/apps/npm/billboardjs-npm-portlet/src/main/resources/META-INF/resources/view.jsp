<%--
/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
--%>

<%@ include file="/init.jsp" %>

<!-- Temporary workaround to obtain the library stylesheets -->
<link href="/o/billboardjs-npm-portlet/node_modules/billboard.js@1.1.1/dist/billboard.css" rel="stylesheet">

<div id="<portlet:namespace />-billboard">
	<h1>An example from billboard.js</h1>

	<h2>Default charts</h2>

	<div id="<portlet:namespace />-BarChart"></div>
	<div id="<portlet:namespace />-StepChart"></div>
	<div id="<portlet:namespace />-LineChart"></div>
	<div id="<portlet:namespace />-AreaChart"></div>
	<div id="<portlet:namespace />-SplineChart"></div>
	<div id="<portlet:namespace />-StackedAreaChart"></div>

	<h2>D3 custom charts</h2>
	<style>
		.links line {
		stroke: #999;
		stroke-opacity: 0.6;
		}

		.nodes circle {
		stroke: #fff;
		stroke-width: 1.5px;
		}
	</style>

	<svg height="600" id="<portlet:namespace />-D3Graph" width="960"></svg>
</div>

<aui:script require="billboardjs-npm-portlet@1.0.0">
	billboardjsNpmPortlet100.default('<portlet:namespace />');
</aui:script>