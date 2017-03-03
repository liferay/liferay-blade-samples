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

package com.liferay.blade.samples.springmvc;

import com.liferay.blade.samples.servicebuilder.service.FooLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.osgi.service.component.annotations.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import java.util.Calendar;
import java.util.Date;

import com.liferay.blade.samples.servicebuilder.model.Foo;
import com.liferay.blade.samples.servicebuilder.service.FooLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * class SpringMVCPortletViewController: The Spring MVC portlet view controller
 * class.
 *
 * @author Liferay
 */
@Controller
@RequestMapping("VIEW")
public class SpringMVCPortletViewController {

	/**
	 * view: This is the default method invoked for the normal view.
	 * @param request
	 * @param response
	 * @return String the view result.
	 */
	@RenderMapping
	public String view(RenderRequest request, RenderResponse response) {
		return "view";
	}

	/**
	 * view2: This is another view method that is invoked when the action key
	 * is set to view.
	 * @param request
	 * @param response
	 * @return String The view result.
	 */
	@RenderMapping(params = "action=view")
	public String view2(RenderRequest request, RenderResponse response) {
		return "view";
	}

	/**
	 * addFoo: This is the view handler when the action is addFoo.
	 * @param request
	 * @param response
	 * @return String The view result.
	 */
	@RenderMapping(params = "action=addFoo")
	public String addFoo(RenderRequest request, RenderResponse response) {
		return "edit_foo";
	}

	/**
	 * editFoo: This is the view handler when the action is editFoo.
	 * @param request
	 * @param response
	 * @return String The view result.
	 */
	@RenderMapping(params = "action=editFoo")
	public String editFoo(RenderRequest request, RenderResponse response) {
		return "edit_foo";
	}

	/**
	 * updateFoo: This is the action handler when the action key is updateFoo,
	 * this will be the case for both Foo adds and updates.
	 * @param actionRequest
	 * @param response
	 * @throws Exception
	 */
	@ActionMapping(params = "action=updateFoo")
	public void updateFoo(ActionRequest actionRequest, ActionResponse response)
			throws Exception {
		// see if there is an existing foo id
		long fooId = ParamUtil.getLong(actionRequest, "fooId");

		// extract the form field values.
		String field1 = ParamUtil.getString(actionRequest, "field1");
		boolean field2 = ParamUtil.getBoolean(actionRequest, "field2");
		int field3 = ParamUtil.getInteger(actionRequest, "field3");
		String field5 = ParamUtil.getString(actionRequest, "field5");

		// convert the calendar details into a date.
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

		// if foo id is less than or equal to zero, we're adding a new Foo
		if (fooId <= 0) {
			_log.info("Adding a new foo...");

			// create the Foo
			Foo foo = FooLocalServiceUtil.createFoo(0);

			// set the fields
			foo.setField1(field1);
			foo.setField2(field2);
			foo.setField3(field3);
			foo.setField4(field4);
			foo.setField5(field5);

			// Invoke the service layer to add the Foo
			FooLocalServiceUtil.addFooWithoutId(foo);
		}
		else {
			_log.info("Updating a new foo...");

			// doing an update, retrieve the current Foo
			Foo foo = FooLocalServiceUtil.fetchFoo(fooId);

			// update the Foo fields
			foo.setFooId(fooId);
			foo.setField1(field1);
			foo.setField2(field2);
			foo.setField3(field3);
			foo.setField4(field4);
			foo.setField5(field5);

			// Invoke the service layer to update the Foo
			FooLocalServiceUtil.updateFoo(foo);
		}
	}

	/**
	 * deleteFoo: This is the action handler when the action key is deleteFoo,
	 * this will be the case when we are deleting a Foo.
	 * @param actionRequest
	 * @param response
	 * @throws Exception
	 */
	@ActionMapping(params = "action=deleteFoo")
	public void deleteFoo(ActionRequest actionRequest, ActionResponse response)
			throws Exception {
		_log.info("Deleting a new foo...");

		long fooId = ParamUtil.getLong(actionRequest, "fooId");

		response.setRenderParameter("action","view");

		FooLocalServiceUtil.deleteFoo(fooId);
	}

	private static final Log _log =
			LogFactoryUtil.getLog(SpringMVCPortletViewController.class);
}