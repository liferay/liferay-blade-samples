/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.

 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.

 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.blade.samples.portlet.kotlinactioncommand

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
		"javax.portlet.name=com_liferay_blade_samples_portlet_kotlinactioncommand_KotlinGreeterPortletKt",
		"mvc.command.name=greet"
	),
	service = arrayOf(MVCActionCommand::class))
class KotlinGreeterActionCommandKt : MVCActionCommand {

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

		val greetingMessage = "Hello $name! Welcome to OSGi"

		actionRequest?.setAttribute("GREETER_MESSAGE", greetingMessage)

		SessionMessages.add(actionRequest, "greetingMessage", greetingMessage)
	}

	companion object {

		private val _log = LogFactoryUtil.getLog(
			KotlinGreeterActionCommandKt::class.java)
	}

}