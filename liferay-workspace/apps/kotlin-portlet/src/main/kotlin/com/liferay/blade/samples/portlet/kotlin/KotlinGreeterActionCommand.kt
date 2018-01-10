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
import com.liferay.portal.kernel.log.LogFactoryUtil
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand
import com.liferay.portal.kernel.servlet.SessionMessages
import com.liferay.portal.kernel.util.ParamUtil
import com.liferay.portal.kernel.util.StringPool

import javax.portlet.ActionRequest
import javax.portlet.ActionResponse
import javax.portlet.PortletException

import org.osgi.service.component.annotations.Component

/**
 * @author Liferay
 */
@Component(
	immediate = true,
	property = arrayOf(
		"javax.portlet.name=" + KotlinGreeterPortletKeys.KotlinGreeterPortlet,
		"mvc.command.name=greet"
	),
	service = arrayOf(MVCActionCommand::class)
)
class KotlinGreeterActionCommand : MVCActionCommand {

	@Throws(PortletException::class)
	override fun processAction(
		actionRequest: ActionRequest?, actionResponse: ActionResponse?): Boolean {

		_handleActionCommand(actionRequest)

		return true
	}

	private fun _handleActionCommand(actionRequest: ActionRequest?) {
		val name = ParamUtil.get(actionRequest, "name", StringPool.BLANK)

		if (_log.isInfoEnabled()) {
			_log.info("Hello " + name)
		}

		val greetingMessage = "Hello $name!"

		actionRequest?.setAttribute("GREETER_MESSAGE", greetingMessage)

		SessionMessages.add(actionRequest, "greetingMessage", greetingMessage)
	}

	companion object {
		private val _log = LogFactoryUtil.getLog(
			KotlinGreeterActionCommand::class.java)
	}

}