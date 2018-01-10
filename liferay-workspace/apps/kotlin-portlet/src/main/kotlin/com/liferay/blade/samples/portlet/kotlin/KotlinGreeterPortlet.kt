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

package com.liferay.blade.samples.portlet.kotlin

import com.liferay.blade.samples.portlet.kotlin.constants.KotlinGreeterPortletKeys
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet

import java.io.IOException

import javax.portlet.Portlet
import javax.portlet.PortletException
import javax.portlet.RenderRequest
import javax.portlet.RenderResponse

import org.osgi.service.component.annotations.Component

/**
 * @author Liferay
 */
@Component(
	immediate = true,
	property = arrayOf(
		"com.liferay.portlet.css-class-wrapper=portlet-greeter",
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Kotlin Greeter Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + KotlinGreeterPortletKeys.KotlinGreeterPortlet,
		"javax.portlet.security-role-ref=power-user,user"
	),
	service = arrayOf(Portlet::class)
)
class KotlinGreeterPortlet : MVCPortlet() {

	@Throws(IOException::class, PortletException::class)
	override fun doView(
		renderRequest: RenderRequest, renderResponse: RenderResponse) {

		val greeting: String? = renderRequest.getAttribute(KotlinGreeterPortletKeys.GreeterMessage) as String?

		if (greeting == null) {
			renderRequest.setAttribute(KotlinGreeterPortletKeys.GreeterMessage, "")
		}

		super.doView(renderRequest, renderResponse)
	}

}