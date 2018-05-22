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

package com.liferay.blade.samples.servicebuilder.adq.web;

import com.liferay.blade.samples.servicebuilder.adq.model.Bar;
import com.liferay.blade.samples.servicebuilder.adq.service.BarLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.Calendar;
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Liferay
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Service Builder ADQ",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=com_liferay_blade_samples_servicebuilder_adq_web",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class JSPPortlet extends MVCPortlet {

	public static final String MASS_UPDATE = "mass-update";

	public BarLocalService getBarLocalService() {
		return _barLocalService;
	}

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		try {
			String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

			if (cmd.equals(Constants.ADD) || cmd.equals(Constants.UPDATE)) {
				updateBar(actionRequest);
			}
			else if (cmd.equals(Constants.DELETE)) {
				deleteBar(actionRequest);
			}
			else if (cmd.equals(MASS_UPDATE)) {
				massUpdate();
			}

			if (Validator.isNotNull(cmd)) {
				if (SessionErrors.isEmpty(actionRequest)) {
					SessionMessages.add(actionRequest, "requestProcessed");
				}

				String redirect = ParamUtil.getString(
					actionRequest, "redirect");

				actionResponse.sendRedirect(redirect);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	@Override
	public void render(RenderRequest request, RenderResponse response)
		throws IOException, PortletException {

		//set service bean
		request.setAttribute("barLocalService", getBarLocalService());

		super.render(request, response);
	}

	protected void deleteBar(ActionRequest actionRequest) throws Exception {
		long barId = ParamUtil.getLong(actionRequest, "barId");

		getBarLocalService().deleteBar(barId);
	}

	protected void massUpdate() {
		_barLocalService.massUpdate();
	}

	protected void updateBar(ActionRequest actionRequest) throws Exception {
		long barId = ParamUtil.getLong(actionRequest, "barId");

		String field1 = ParamUtil.getString(actionRequest, "field1");
		boolean field2 = ParamUtil.getBoolean(actionRequest, "field2");
		int field3 = ParamUtil.getInteger(actionRequest, "field3");
		String field5 = ParamUtil.getString(actionRequest, "field5");

		int dateMonth = ParamUtil.getInteger(actionRequest, "field4Month");
		int dateDay = ParamUtil.getInteger(actionRequest, "field4Day");
		int dateYear = ParamUtil.getInteger(actionRequest, "field4Year");
		int dateHour = ParamUtil.getInteger(actionRequest, "field4Hour");
		int dateMinute = ParamUtil.getInteger(actionRequest, "field4Minute");
		int dateAmPm = ParamUtil.getInteger(actionRequest, "field4AmPm");

		if (dateAmPm == Calendar.PM) {
			dateHour += 12;
		}

		Date field4 = PortalUtil.getDate(
			dateMonth, dateDay, dateYear, dateHour, dateMinute,
			PortalException.class);

		if (barId <= 0) {
			Bar bar = getBarLocalService().createBar(0);

			bar.setField1(field1);
			bar.setField2(field2);
			bar.setField3(field3);
			bar.setField4(field4);
			bar.setField5(field5);
			bar.isNew();

			getBarLocalService().addBarWithoutId(bar);
		}
		else {
			Bar bar = getBarLocalService().fetchBar(barId);

			bar.setBarId(barId);
			bar.setField1(field1);
			bar.setField2(field2);
			bar.setField3(field3);
			bar.setField4(field4);
			bar.setField5(field5);

			getBarLocalService().updateBar(bar);
		}
	}

	@Reference
	private volatile BarLocalService _barLocalService;

}