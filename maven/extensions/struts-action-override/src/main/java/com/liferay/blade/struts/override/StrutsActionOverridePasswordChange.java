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

package com.liferay.blade.struts.override;

import com.liferay.portal.action.UpdatePasswordAction;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.UserLockoutException;
import com.liferay.portal.kernel.exception.UserPasswordException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.Ticket;
import com.liferay.portal.kernel.model.TicketConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.auth.session.AuthenticatedSessionManagerUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.TicketLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.pwd.PwdToolkitUtilThreadLocal;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kris Patefield
 */
@Component(
	immediate = true, property = "path=/portal/update_password",
	service = StrutsAction.class
)
public class StrutsActionOverridePasswordChange implements StrutsAction {

	@Override
	public String execute(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		//Execute out print in logs - Proof this works
		_log.debug(
			String.format(
				"Custom StrutsAction implementation has been called"));

		//Place your logic from here onwards
		//Standard update password logic included below

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Ticket ticket = getTicket(httpServletRequest);

		if ((ticket != null) &&
			StringUtil.equals(
				httpServletRequest.getMethod(), HttpMethods.GET)) {

			resendAsPost(httpServletRequest, httpServletResponse);

			return null;
		}

		httpServletRequest.setAttribute(WebKeys.TICKET, ticket);

		String cmd = ParamUtil.getString(httpServletRequest, Constants.CMD);

		if (Validator.isNull(cmd)) {
			if (ticket != null) {
				User user = UserLocalServiceUtil.getUser(ticket.getClassPK());

				try {
					UserLocalServiceUtil.checkLockout(user);

					UserLocalServiceUtil.updatePasswordReset(
						user.getUserId(), true);
				}
				catch (UserLockoutException ule) {
					SessionErrors.add(httpServletRequest, ule.getClass(), ule);
				}
			}

			return "portal.update_password";
		}

		try {
			updatePassword(
				httpServletRequest, httpServletResponse, themeDisplay, ticket);

			String redirect = ParamUtil.getString(
				httpServletRequest, WebKeys.REFERER);

			if (Validator.isNotNull(redirect)) {
				redirect = PortalUtil.escapeRedirect(redirect);
			}

			if (Validator.isNull(redirect)) {
				redirect = themeDisplay.getPathMain();
			}

			httpServletResponse.sendRedirect(redirect);

			return null;
		}
		catch (Exception exception) {
			if (exception instanceof UserPasswordException) {
				SessionErrors.add(
					httpServletRequest, exception.getClass(), exception);

				return "portal.update_password";
			}
			else if (exception instanceof NoSuchUserException ||
					 exception instanceof PrincipalException) {

				SessionErrors.add(httpServletRequest, exception.getClass());

				return "portal.error";
			}

			PortalUtil.sendError(
				exception, httpServletRequest, httpServletResponse);

			return null;
		}
	}

	protected Ticket getTicket(HttpServletRequest httpServletRequest) {
		String ticketKey = ParamUtil.getString(httpServletRequest, "ticketKey");

		if (Validator.isNull(ticketKey)) {
			return null;
		}

		try {
			Ticket ticket = TicketLocalServiceUtil.fetchTicket(ticketKey);

			if ((ticket == null) ||
				(ticket.getType() != TicketConstants.TYPE_PASSWORD)) {

				return null;
			}

			if (!ticket.isExpired()) {
				return ticket;
			}

			TicketLocalServiceUtil.deleteTicket(ticket);
		}
		catch (Exception exception) {
		}

		return null;
	}

	protected boolean isValidatePassword(
		HttpServletRequest httpServletRequest) {

		HttpSession session = httpServletRequest.getSession();

		Boolean setupWizardPasswordUpdated = (Boolean)session.getAttribute(
			WebKeys.SETUP_WIZARD_PASSWORD_UPDATED);

		if ((setupWizardPasswordUpdated != null) &&
			setupWizardPasswordUpdated) {

			return false;
		}

		return true;
	}

	protected void resendAsPost(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		System.out.println("resend as post method");

		httpServletResponse.setHeader(
			"Cache-Control", "no-cache, no-store, must-revalidate");
		httpServletResponse.setHeader("Expires", "0");
		httpServletResponse.setHeader("Pragma", "no-cache");

		PrintWriter printWriter = httpServletResponse.getWriter();

		Map<String, String[]> parameterMap =
			httpServletRequest.getParameterMap();

		StringBundler sb = new StringBundler(7 + parameterMap.size() * 5);

		sb.append("<html><body onload=\"document.fm.submit();\">");
		sb.append("<form action=\"");
		sb.append(PortalUtil.getPortalURL(httpServletRequest));
		sb.append("/c/portal/update_password\" method=\"post\" name=\"fm\">");

		for (String name : parameterMap.keySet()) {
			String value = ParamUtil.getString(httpServletRequest, name);

			sb.append("<input name=\"");
			sb.append(HtmlUtil.escapeAttribute(name));
			sb.append("\" type=\"hidden\" value=\"");
			sb.append(HtmlUtil.escapeAttribute(value));
			sb.append("\"/>");
		}

		sb.append("<noscript>");
		sb.append("<input type=\"submit\" value=\"Please continue here...\"/>");
		sb.append("</noscript></form></body></html>");

		printWriter.write(sb.toString());

		printWriter.close();
	}

	protected void updatePassword(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, ThemeDisplay themeDisplay,
			Ticket ticket)
		throws Exception {

		System.out.println("resend as post method");

		AuthTokenUtil.checkCSRFToken(
			httpServletRequest, UpdatePasswordAction.class.getName());

		long userId = 0;

		if (ticket != null) {
			userId = ticket.getClassPK();
		}
		else {
			userId = themeDisplay.getUserId();
		}

		String password1 = httpServletRequest.getParameter("password1");
		String password2 = httpServletRequest.getParameter("password2");
		boolean passwordReset = false;

		boolean previousValidate = PwdToolkitUtilThreadLocal.isValidate();

		try {
			boolean currentValidate = isValidatePassword(httpServletRequest);

			PwdToolkitUtilThreadLocal.setValidate(currentValidate);

			UserLocalServiceUtil.updatePassword(
				userId, password1, password2, passwordReset);
		}
		finally {
			PwdToolkitUtilThreadLocal.setValidate(previousValidate);
		}

		if (ticket != null) {
			TicketLocalServiceUtil.deleteTicket(ticket);

			UserLocalServiceUtil.updatePasswordReset(userId, false);
		}

		User user = UserLocalServiceUtil.getUser(userId);

		Company company = CompanyLocalServiceUtil.getCompanyById(
			user.getCompanyId());

		String login = null;

		String authType = company.getAuthType();

		if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
			login = user.getEmailAddress();
		}
		else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
			login = user.getScreenName();
		}
		else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
			login = String.valueOf(userId);
		}

		AuthenticatedSessionManagerUtil.login(
			httpServletRequest, httpServletResponse, login, password1, false,
			null);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		StrutsActionOverridePasswordChange.class);

}