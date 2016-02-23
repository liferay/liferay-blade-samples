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

package blade.servicebuilder.web;

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

import blade.servicebuilder.model.Foo;
import blade.servicebuilder.service.FooLocalService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.PortalUtil;

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.osgi",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language"
	},
	service = Portlet.class
)

/**
 * @author Andy Wu
  */
public class JSPPortlet extends MVCPortlet {

	@Override
	public void processAction(ActionRequest actionRequest,
			ActionResponse actionResponse) throws IOException, PortletException {

		try {
			String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

			if (cmd.equals(Constants.ADD) || cmd.equals(Constants.UPDATE)) {
				updateFoo(actionRequest);
			} else if (cmd.equals(Constants.DELETE)) {
				deleteFoo(actionRequest);
			}

			if (Validator.isNotNull(cmd)) {
				if (SessionErrors.isEmpty(actionRequest)) {
					SessionMessages.add(actionRequest, "requestProcessed");
				}

				String redirect = ParamUtil.getString(actionRequest, "redirect");

				actionResponse.sendRedirect(redirect);
			}
		} catch (Exception e) {
			throw new PortletException(e);
		}
	}

	@Override
	public void render(RenderRequest request, RenderResponse response)
			throws IOException, PortletException {

		//set service bean
		request.setAttribute("fooLocalService", getFooLocalService());

		super.render(request, response);
	}

	protected void deleteFoo(ActionRequest actionRequest) throws Exception {
		long fooId = ParamUtil.getLong(actionRequest, "fooId");

		getFooLocalService().deleteFoo(fooId);
	}

	protected void updateFoo(ActionRequest actionRequest) throws Exception {
		long fooId = ParamUtil.getLong(actionRequest, "fooId");

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

		Date field4 = PortalUtil.getDate(dateMonth, dateDay, dateYear,
				dateHour, dateMinute, PortalException.class);

		if (fooId <= 0) {
			Foo foo = getFooLocalService().createFoo(0);

			foo.setField1(field1);
			foo.setField2(field2);
			foo.setField3(field3);
			foo.setField4(field4);
			foo.setField5(field5);
			foo.isNew();
			getFooLocalService().addFooWithoutId(foo);
		} else {
			Foo foo = getFooLocalService().fetchFoo(fooId);
			foo.setFooId(fooId);
			foo.setField1(field1);
			foo.setField2(field2);
			foo.setField3(field3);
			foo.setField4(field4);
			foo.setField5(field5);
			getFooLocalService().updateFoo(foo);
		}
	}

	public FooLocalService getFooLocalService() {

		return _fooLocalService;
	}

	@Reference
	public void setFooLocalService(FooLocalService fooLocalService) {

		this._fooLocalService = fooLocalService;
	}

	private FooLocalService _fooLocalService;

}
