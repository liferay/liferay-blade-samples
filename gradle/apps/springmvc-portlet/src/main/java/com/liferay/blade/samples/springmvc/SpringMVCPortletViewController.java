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

package com.liferay.blade.samples.springmvc;

import com.liferay.blade.samples.servicebuilder.model.Foo;
import com.liferay.blade.samples.servicebuilder.service.FooLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.Calendar;
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

/**
 * Provides the Spring MVC portlet view controller class.
 *
 * @author Liferay
 */
@Controller
@RequestMapping("VIEW")
public class SpringMVCPortletViewController {

	/**
	 * Handles the view when the action is <code>addFoo</code>.
	 *
	 * @param  request the render request
	 * @param  response the render response
	 * @return the view result
	 */
	@RenderMapping(params = "action=addFoo")
	public String addFoo(RenderRequest request, RenderResponse response) {
		return "edit_foo";
	}

	/**
	 * Handles the action when the action key is <code>deleteFoo</code>. This is
	 * the case when Foo is deleted.
	 *
	 * @param  actionRequest the action request
	 * @param  response the action response
	 * @throws Exception if an exception occurred
	 */
	@ActionMapping(params = "action=deleteFoo")
	public void deleteFoo(ActionRequest actionRequest, ActionResponse response)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("Deleting a new foo...");
		}

		long fooId = ParamUtil.getLong(actionRequest, "fooId");

		response.setRenderParameter("action", "view");

		FooLocalServiceUtil.deleteFoo(fooId);
	}

	/**
	 * Handles the view when the action is <code>editFoo</code>.
	 *
	 * @param  request the render request
	 * @param  response the render response
	 * @return the view result
	 */
	@RenderMapping(params = "action=editFoo")
	public String editFoo(RenderRequest request, RenderResponse response) {
		return "edit_foo";
	}

	/**
	 * Handles the action when the action key is <code>updateFoo</code>. This is
	 * the case when Foo is added and updated.
	 *
	 * @param  actionRequest the action request
	 * @param  response the action response
	 * @throws Exception if an exception occurred
	 */
	@ActionMapping(params = "action=updateFoo")
	public void updateFoo(ActionRequest actionRequest, ActionResponse response)
		throws Exception {

		// See if there is an existing Foo ID.

		long fooId = ParamUtil.getLong(actionRequest, "fooId");

		// Extract the form field values.

		String field1 = ParamUtil.getString(actionRequest, "field1");
		boolean field2 = ParamUtil.getBoolean(actionRequest, "field2");
		int field3 = ParamUtil.getInteger(actionRequest, "field3");
		String field5 = ParamUtil.getString(actionRequest, "field5");

		// Convert the calendar details into a date.

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

		// If the Foo ID is less than or equal to zero, add a new Foo.

		if (fooId <= 0) {
			if (_log.isInfoEnabled()) {
				_log.info("Adding a new foo...");
			}

			// Create the Foo.

			Foo foo = FooLocalServiceUtil.createFoo(0);

			// Set the Foo's fields.

			foo.setField1(field1);
			foo.setField2(field2);
			foo.setField3(field3);
			foo.setField4(field4);
			foo.setField5(field5);

			// Invoke the service layer to add the foo.

			FooLocalServiceUtil.addFooWithoutId(foo);
		}
		else {
			if (_log.isInfoEnabled()) {
				_log.info("Updating a new foo...");
			}

			// Retrieve the current Foo during the update.

			Foo foo = FooLocalServiceUtil.fetchFoo(fooId);

			// Update the Foo's fields.

			foo.setFooId(fooId);
			foo.setField1(field1);
			foo.setField2(field2);
			foo.setField3(field3);
			foo.setField4(field4);
			foo.setField5(field5);

			// Invoke the service layer to update the Foo.

			FooLocalServiceUtil.updateFoo(foo);
		}
	}

	/**
	 * Returns the normal view.
	 *
	 * @param  request the render request
	 * @param  response the render response
	 * @return the view result
	 */
	@RenderMapping
	public String view(RenderRequest request, RenderResponse response) {
		return "view";
	}

	/**
	 * Returns the view when the action key is set to <code>view</code>.
	 *
	 * @param  request the render request
	 * @param  response the render response
	 * @return the view result
	 */
	@RenderMapping(params = "action=view")
	public String view2(RenderRequest request, RenderResponse response) {
		return "view";
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SpringMVCPortletViewController.class);

}