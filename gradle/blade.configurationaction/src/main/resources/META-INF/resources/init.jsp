<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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
			portletPreferences.getValue(
				"fontFamily", messageDisplayConfiguration.fontFamily());

		fontColor =
			portletPreferences.getValue(
				"fontColor", messageDisplayConfiguration.fontColor());

		fontSize =
			portletPreferences.getValue(
				"fontSize",
				String.valueOf(messageDisplayConfiguration.fontSize()));
	}
%>