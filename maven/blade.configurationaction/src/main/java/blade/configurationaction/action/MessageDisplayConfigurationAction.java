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

package blade.configurationaction.action;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

import aQute.bnd.annotation.metatype.Configurable;
import blade.configurationaction.config.MessageDisplayConfiguration;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;

/**
 * @author Kamesh Sampath
 */
@Component(
				configurationPid = "blade.configurationaction.config.MessageDisplayConfiguration",
				configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
				property = {
					"javax.portlet.name=blade_configurationaction_portlet_BladeMessagePortlet"
				},
				service = ConfigurationAction.class)
public class MessageDisplayConfigurationAction
	extends DefaultConfigurationAction {

	@Override
	public void processAction(
		PortletConfig portletConfig, ActionRequest actionRequest,
		ActionResponse actionResponse)
		throws Exception {

		_log.debug("Blade Message Portlet configuration action");

		String fontColor = ParamUtil.getString(actionRequest, "fontColor");
		String fontFamily = ParamUtil.getString(actionRequest, "fontFamily");
		String fontSize = ParamUtil.getString(actionRequest, "fontSize");

		_log.debug("Message Display Configuration: Font Family:" + fontFamily);
		_log.debug("Message Display Configuration: Font Size:" + fontSize);
		_log.debug("Message Display Configuration: Font Color:" + fontColor);

		setPreference(actionRequest, "fontColor", fontColor);
		setPreference(actionRequest, "fontFamily", fontFamily);
		setPreference(actionRequest, "fontSize", fontSize);

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	@Override
	public String render(
		PortletConfig portletConfig, RenderRequest renderRequest,
		RenderResponse renderResponse) throws Exception {

		_log.debug("Blade Message Portlet configuration render");

		renderRequest.setAttribute(
			MessageDisplayConfiguration.class.getName(),
			_messageDisplayConfiguration);

		return super.render(portletConfig, renderRequest, renderResponse);
	}

	@Activate
	@Modified
	protected void activate(Map<Object, Object> properties) {
		_messageDisplayConfiguration =
			Configurable.createConfigurable(
				MessageDisplayConfiguration.class, properties);
	}
	private Log _log =
		LogFactoryUtil.getLog(MessageDisplayConfigurationAction.class);

	private volatile MessageDisplayConfiguration _messageDisplayConfiguration;

}
