package com.acme.m6a8.web.internal.commerce.discount.rule.type;

import com.liferay.commerce.discount.rule.type.CommerceDiscountRuleTypeJSPContributor;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	immediate = true,
	property = "commerce.discount.rule.type.jsp.contributor.key=" + M6A8CommerceDiscountRuleTypeJSPContributor.KEY,
	service = CommerceDiscountRuleTypeJSPContributor.class
)
public class M6A8CommerceDiscountRuleTypeJSPContributor
	implements CommerceDiscountRuleTypeJSPContributor {

	public static final String KEY = "Example";

	@Override
	public void render(
			long l, long l1, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		_jspRenderer.renderJSP(
			_servletContext, httpServletRequest, httpServletResponse,
			"/view.jsp");
	}

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference(target = "(osgi.web.symbolicname=com.acme.m6a8.web)")
	private ServletContext _servletContext;

}