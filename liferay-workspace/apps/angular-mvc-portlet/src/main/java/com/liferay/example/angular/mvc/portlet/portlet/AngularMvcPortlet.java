package com.liferay.example.angular.mvc.portlet.portlet;

import com.liferay.example.angular.mvc.portlet.constants.AngularMvcPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author ivan
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.header-portlet-css=/angular/styles.css",
		"com.liferay.portlet.header-portlet-javascript=/angular/runtime.js",
		"com.liferay.portlet.header-portlet-javascript=/angular/polyfills.js",
		"com.liferay.portlet.header-portlet-javascript=/angular/main.js",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=AngularMvc",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AngularMvcPortletKeys.ANGULARMVC,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AngularMvcPortlet extends MVCPortlet {
}
