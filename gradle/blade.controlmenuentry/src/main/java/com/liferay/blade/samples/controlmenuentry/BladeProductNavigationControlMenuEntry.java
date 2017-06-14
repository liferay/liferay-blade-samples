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

package com.liferay.blade.samples.controlmenuentry;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.product.navigation.control.menu.BaseProductNavigationControlMenuEntry;
import com.liferay.product.navigation.control.menu.ProductNavigationControlMenuEntry;
import com.liferay.product.navigation.control.menu.constants.ProductNavigationControlMenuCategoryKeys;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * Represents a Control menu entry, providing methods to define the entry's
 * icon, label, URL, and visibility.
 *
 * @author Liferay
 */
@Component(
	immediate = true,
	property = {
		"product.navigation.control.menu.category.key=" + ProductNavigationControlMenuCategoryKeys.USER,
		"product.navigation.control.menu.entry.order:Integer=1"
	},
	service = ProductNavigationControlMenuEntry.class
)
public class BladeProductNavigationControlMenuEntry
	extends BaseProductNavigationControlMenuEntry
	implements ProductNavigationControlMenuEntry {

	/**
	 * Returns the icon to display in the Control Menu. By default, Lexicon
	 * icons are expected to be returned. To view all the Lexicon icons
	 * available, see
	 * <a href="http://liferay.github.io/lexicon/content/icons-lexicon/">http://liferay.github.io/lexicon/content/icons-lexicon/</a>.
	 *
	 * @param  request the request
	 * @return the icon to display in the Control Menu
	 */
	@Override
	public String getIcon(HttpServletRequest request) {
		return "link";
	}

	/**
	 * Returns the Control Menu entry's label stored in the module's resource
	 * bundle.
	 *
	 * @param  locale the label's locale
	 * @return the Control Menu entry's label
	 */
	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(
			resourceBundle, "blade-menu-entry-custom-message");
	}

	/**
	 * Returns the Control Menu entry's linked URL.
	 *
	 * @param  request the request
	 * @return the Control Menu entry's linked URL
	 */
	@Override
	public String getURL(HttpServletRequest request) {
		return "http://www.liferay.com";
	}

	/**
	 * Returns <code>true</code> if the Control Menu entry is visible in the
	 * Control Menu.
	 *
	 * @param  request the request
	 * @return <code>true</code> if the Control Menu entry is visible in the
	 *         Control Menu; <code>false</code> otherwise
	 * @throws PortalException if a portal exception occurred
	 */
	@Override
	public boolean isShow(HttpServletRequest request) throws PortalException {
		return true;
	}

}