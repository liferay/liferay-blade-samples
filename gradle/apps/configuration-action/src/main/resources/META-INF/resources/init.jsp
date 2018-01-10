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

<%@ page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.Validator" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@page
	import="com.liferay.blade.samples.configurationaction.MessageDisplayConfiguration" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
	MessageDisplayConfiguration messageDisplayConfiguration =
		(MessageDisplayConfiguration)
		renderRequest.getAttribute(MessageDisplayConfiguration.class.getName());

	String fontFamily = StringPool.BLANK;
	String fontColor = StringPool.BLANK;
	String fontSize = StringPool.BLANK;

	if (Validator.isNotNull(messageDisplayConfiguration)) {
		fontFamily =
			portletPreferences.getValue("fontFamily", messageDisplayConfiguration.fontFamily());

		fontColor =
			portletPreferences.getValue("fontColor", messageDisplayConfiguration.fontColor());

		fontSize =
			portletPreferences.getValue("fontSize", String.valueOf(messageDisplayConfiguration.fontSize()));
	}
%>