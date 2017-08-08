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

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<div class="list-group-panel" id="<portlet:namespace />simulationOptionsContainer">
	<p class="text-center"><liferay-ui:message key="simulation-description" /></p>

	<div class="container-fluid default-devices flex-container">
		<div class="flex-item-expand hidden-sm hidden-xs lfr-device-item selected text-center" data-option="option-1">
			<aui:icon cssClass="icon icon-monospaced" image="calendar" markupView="lexicon" />

			<small><%= LanguageUtil.get(resourceBundle, "option-1") %></small>
		</div>

		<div class="flex-item-expand hidden-sm hidden-xs lfr-device-item selected text-center" data-option="option-2">
			<aui:icon cssClass="icon icon-monospaced" image="heart" markupView="lexicon" />

			<small><%= LanguageUtil.get(resourceBundle, "option-2") %></small>
		</div>

		<div class="flex-item-expand hidden-sm hidden-xs lfr-device-item selected text-center" data-option="option-3">
			<aui:icon cssClass="icon icon-monospaced" image="star" markupView="lexicon" />

			<small><%= LanguageUtil.get(resourceBundle, "option-3") %></small>
		</div>

		<div class="flex-item-expand hidden-sm hidden-xs lfr-device-item selected text-center" data-option="option-4">
			<aui:icon cssClass="icon icon-monospaced" image="camera" markupView="lexicon" />

			<small><%= LanguageUtil.get(resourceBundle, "option-4") %></small>
		</div>
	</div>
</div>

<aui:script use="aui-base">
	A.one('#<portlet:namespace />simulationOptionsContainer').delegate(
		'click',
		function(event) {
			var currentTarget = event.currentTarget;

			var dataOption = currentTarget.attr('data-option');

			var iframe = A.one('#simulationDeviceIframe');

			var iframeWindow = A.Node.getDOMNode(iframe.get('contentWindow'));

			iframeWindow.confirm(dataOption);
		},
		'.lfr-device-item'
	);
</aui:script>