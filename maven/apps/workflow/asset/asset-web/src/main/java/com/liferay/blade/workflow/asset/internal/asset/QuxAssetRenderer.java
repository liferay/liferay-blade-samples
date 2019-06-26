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

package com.liferay.blade.workflow.asset.internal.asset;

import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseJSPAssetRenderer;
import com.liferay.blade.workflow.asset.model.Qux;
import com.liferay.petra.string.StringPool;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Inácio Nery
 */
public class QuxAssetRenderer extends BaseJSPAssetRenderer<Qux> {

	public QuxAssetRenderer(Qux qux) {
		_qux = qux;
	}

	@Override
	public Qux getAssetObject() {
		return _qux;
	}

	@Override
	public AssetRendererFactory<Qux> getAssetRendererFactory() {
		return new QuxAssetRendererFactory();
	}

	@Override
	public String getClassName() {
		return Qux.class.getName();
	}

	@Override
	public long getClassPK() {
		return _qux.getQuxId();
	}

	@Override
	public long getGroupId() {
		return _qux.getGroupId();
	}

	@Override
	public String getJspPath(
		HttpServletRequest httpServletRequest, String template) {

		if (template.equals(TEMPLATE_ABSTRACT) ||
			template.equals(TEMPLATE_FULL_CONTENT)) {

			return "/asset/full_content.jsp";
		}

		return null;
	}

	@Override
	public int getStatus() {
		return _qux.getStatus();
	}

	@Override
	public String getSummary(
		PortletRequest portletRequest, PortletResponse portletResponse) {

		return StringPool.BLANK;
	}

	@Override
	public String getTitle(Locale locale) {
		return String.valueOf(_qux.getQuxId());
	}

	@Override
	public long getUserId() {
		return _qux.getUserId();
	}

	@Override
	public String getUserName() {
		return _qux.getUserName();
	}

	@Override
	public String getUuid() {
		return _qux.getUuid();
	}

	@Override
	public boolean include(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String template)
		throws Exception {

		httpServletRequest.setAttribute("QUX", _qux);

		return super.include(httpServletRequest, httpServletResponse, template);
	}

	private final Qux _qux;

}