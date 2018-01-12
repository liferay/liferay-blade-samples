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

package blade.document.action.displaycontext;

import com.liferay.document.library.display.context.BaseDLViewFileVersionDisplayContext;
import com.liferay.document.library.display.context.DLViewFileVersionDisplayContext;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.servlet.taglib.ui.JavaScriptMenuItem;
import com.liferay.portal.kernel.servlet.taglib.ui.JavaScriptToolbarItem;
import com.liferay.portal.kernel.servlet.taglib.ui.Menu;
import com.liferay.portal.kernel.servlet.taglib.ui.MenuItem;
import com.liferay.portal.kernel.servlet.taglib.ui.ToolbarItem;
import com.liferay.portal.kernel.settings.PortletInstanceSettingsLocator;
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.settings.SettingsException;
import com.liferay.portal.kernel.settings.SettingsFactoryUtil;
import com.liferay.portal.kernel.settings.TypedSettings;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowStateException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Reference;
import org.osgi.service.log.LogService;

/**
 * Provides the Custom Display Context, which provides access to the Documents
 * and Media portlet's UI elements.
 *
 * @author liferay
 */
public class BladeActionDisplayContext
	extends BaseDLViewFileVersionDisplayContext {

	public BladeActionDisplayContext(
		UUID uuid, DLViewFileVersionDisplayContext parentDLDisplayContext,
		HttpServletRequest request, HttpServletResponse response,
		FileVersion fileVersion) {

		super(uuid, parentDLDisplayContext, request, response, fileVersion);

		_themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	/**
	 * This method is for adding context menu in Document Overview screen in both page portlet and admin portlet.<br/>
	 * If you want to see context menu in Documents and Media portlet , you should check "Show Actions"
	 * in portlet configuration
	 */
	public Menu getMenu() throws PortalException {
		Menu menu = super.getMenu();

		if (_showAction()) {
			JavaScriptMenuItem jsMenuItem = new JavaScriptMenuItem();

			jsMenuItem.setLabel("Blade Basic Info");
			jsMenuItem.setOnClick(_getOnclick());

			List<MenuItem> list = menu.getMenuItems();

			list.add(jsMenuItem);
		}

		return menu;
	}

	/**
	 * This method is for adding context menu in Document Detail screen in page portlet
	 */
	@Override
	public List<ToolbarItem> getToolbarItems() throws PortalException {
		List<ToolbarItem> toolbarItems = super.getToolbarItems();

		JavaScriptToolbarItem jsToolbarItem = new JavaScriptToolbarItem();

		jsToolbarItem.setLabel("Blade Basic Info");
		jsToolbarItem.setOnClick(_getOnclick());

		toolbarItems.add(jsToolbarItem);

		return toolbarItems;
	}

	private String _getOnclick()
	{

		PortletURL portletURL = PortletURLFactoryUtil.create(
			request, "blade_document_action_portlet_BladeDocumentActionPortlet",
			_themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

		String fileName = fileVersion.getFileName();
		String mimeType = fileVersion.getMimeType();
		String version = fileVersion.getVersion();
		Date date = fileVersion.getCreateDate();

		String createdDate = date.toString();

		String createdUserName = fileVersion.getUserName();
		String statusLabel = WorkflowConstants.getStatusLabel(
			fileVersion.getStatus());

		portletURL.setParameter("fileName", fileName);
		portletURL.setParameter("mimeType", mimeType);
		portletURL.setParameter("version", version);
		portletURL.setParameter("statusLabel", statusLabel);
		portletURL.setParameter("createdDate", createdDate);
		portletURL.setParameter("createdUserName", createdUserName);

		try {
			portletURL.setWindowState(LiferayWindowState.POP_UP);
		}
		catch (WindowStateException wse) {
			_log.log(LogService.LOG_ERROR, wse.getMessage(), wse);
		}

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("Liferay.Util.openWindow({");
		stringBuilder.append("dialog: {cache: false,width:800,modal: true},");
		stringBuilder.append("title: 'basic information',id: ");
		stringBuilder.append("'testPopupIdUnique',uri: '");
		stringBuilder.append(portletURL.toString() + "'});");

		return stringBuilder.toString();
	}

	/**
	 * Read settings from page Documents And Media portlet "Show Actions" portlet configuration.<br/>
	 * But for Documents And Media admin portlet, it will always be true.
	 */
	private boolean _showAction() throws SettingsException {
		PortletDisplay portletDisplay = _themeDisplay.getPortletDisplay();

		String portletName = portletDisplay.getPortletName();

		if (portletName.equals(PortletKeys.DOCUMENT_LIBRARY_ADMIN)) {
			return true;
		}

		Settings settings = SettingsFactoryUtil.getSettings(
			new PortletInstanceSettingsLocator(
				_themeDisplay.getLayout(), portletDisplay.getId()));

		TypedSettings typedSettings = new TypedSettings(settings);

		return typedSettings.getBooleanValue("showActions");
	}

	@Reference
	private LogService _log;

	private ThemeDisplay _themeDisplay;

}