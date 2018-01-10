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

<%
boolean doConfigure =
	Validator.isNull(fontFamily) && Validator.isNull(fontColor) && (Validator.isNull(fontSize) || "0".equals(fontSize));
%>

<c:choose>
	<c:when test="<%= doConfigure %>">
		<liferay-ui:message
			key="blade_configurationaction_portlet_BladeMessagePortlet.no-config"
		/>
	</c:when>
	<c:otherwise>
		<p style="font-family:<%= fontFamily %>;color:<%= fontColor %>;font-size:<%= fontSize %>">
			<liferay-ui:message
				key="blade_configurationaction_portlet_BladeMessagePortlet.caption"
			/>
		</p>
	</c:otherwise>
</c:choose>