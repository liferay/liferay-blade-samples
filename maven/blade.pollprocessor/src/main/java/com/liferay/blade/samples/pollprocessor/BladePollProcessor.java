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

package com.liferay.blade.samples.pollprocessor;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.poller.BasePollerProcessor;
import com.liferay.portal.kernel.poller.DefaultPollerResponse;
import com.liferay.portal.kernel.poller.PollerProcessor;
import com.liferay.portal.kernel.poller.PollerRequest;
import com.liferay.portal.kernel.poller.PollerResponse;

import java.util.Date;

import org.osgi.service.component.annotations.Component;

/**
 * @author Liferay
 */
@Component(
	immediate = true,
	property = {"javax.portlet.name=blade_portlet_BladePollProcessorPortlet"},
	service = PollerProcessor.class
)
public class BladePollProcessor extends BasePollerProcessor {

	@Override
	protected PollerResponse doReceive(PollerRequest pollerRequest)
		throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug("Recevied the poller request" + pollerRequest);
		}

		JSONObject responseObject = JSONFactoryUtil.createJSONObject();
		PollerResponse pollerResponse = new DefaultPollerResponse();
		responseObject.put(
			"message", "Hello from BLADE Poller, time now is:" + new Date());
		pollerResponse.setParameter("content", responseObject);

		return pollerResponse;
	}

	@Override
	protected void doSend(PollerRequest pollerRequest) throws Exception {
		String status = getString(pollerRequest, "status");

		if (_log.isInfoEnabled()) {
			_log.info("Poller status:" + status);
		}
	}

	private final Log _log = LogFactoryUtil.getLog(BladePollProcessor.class);

}