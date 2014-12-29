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
package sample.bunde.bndtools.ip.rbportlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.portlet.GenericPortlet;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kamesh Sampath
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.blade",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Resource Bundle Portlet",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class ResourceBundlePortlet extends GenericPortlet {

	@Override
	protected void doView(RenderRequest request, RenderResponse response)
		throws PortletException, IOException {
		
		Locale locale = LocaleUtil.getDefault();
		
		_log.info("Locale:"+locale.toString());
		
		PrintWriter printWriter = response.getWriter();

		try {
			ResourceBundle resourceBundle = getResourceBundle(
				LocaleUtil.getDefault());
					
			if(resourceBundle != null){
				printWriter.print(
					resourceBundle.getString("bndtools.ip.rbportlet"));
			}
		}
		catch (MissingResourceException e) {
			printWriter.print("Resource Bundle OSGi module not deployed yet");
		}
	}
	
	private Log _log = LogFactoryUtil.getLog(ResourceBundlePortlet.class);

}