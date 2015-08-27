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

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

@Component(immediate = true, property = {
		"com.liferay.portlet.display-category=category.osgi",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language" }, service = Portlet.class)
public class JSPPortlet extends MVCPortlet {

	@Override
	public void processAction(ActionRequest actionRequest,
			ActionResponse actionResponse) throws IOException, PortletException {

	/*	try {
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

				String redirect = ParamUtil
						.getString(actionRequest, "redirect");

				actionResponse.sendRedirect(redirect);
			}
		} catch (Exception e) {
			throw new PortletException(e);
		}*/
	}

	protected void deleteFoo(ActionRequest actionRequest) throws Exception {
	/*	long fooId = ParamUtil.getLong(actionRequest, "fooId");

		FooLocalServiceUtil.deleteFoo(fooId);*/
	}

	protected void updateFoo(ActionRequest actionRequest) throws Exception {
		/*long fooId = ParamUtil.getLong(actionRequest, "fooId");

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

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
				Foo.class.getName(), actionRequest);

		if (fooId <= 0) {
			FooLocalServiceUtil.addFoo(field1, field2, field3, field4, field5,
					serviceContext);
		} else {
			FooLocalServiceUtil.updateFoo(fooId, field1, field2, field3,
					field4, field5, serviceContext);
		}*/
	}

}