<#--
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
-->

<#assign
	aui = PortletJspTagLibs["/META-INF/liferay-aui.tld"]
	liferay_portlet = PortletJspTagLibs["/META-INF/liferay-portlet-ext.tld"]
	liferay_security = PortletJspTagLibs["/META-INF/liferay-security.tld"]
	liferay_theme = PortletJspTagLibs["/META-INF/liferay-theme.tld"]
	liferay_ui = PortletJspTagLibs["/META-INF/liferay-ui.tld"]
	liferay_util = PortletJspTagLibs["/META-INF/liferay-util.tld"]
	portlet = PortletJspTagLibs["/META-INF/liferay-portlet.tld"]
/>

<@liferay_theme["defineObjects"] />
<@portlet["defineObjects"] />