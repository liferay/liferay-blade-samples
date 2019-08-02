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

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.asset.kernel.model.BaseAssetRendererFactory;
import com.liferay.blade.workflow.asset.model.Qux;
import com.liferay.blade.workflow.asset.service.QuxLocalService;
import com.liferay.portal.kernel.exception.PortalException;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Inácio Nery
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=com_liferay_blade_workflow_asset_web",
	service = AssetRendererFactory.class
)
public class QuxAssetRendererFactory extends BaseAssetRendererFactory<Qux> {

	public QuxAssetRendererFactory() {
		setCategorizable(false);
		setClassName(Qux.class.getName());
		setPortletId("com_liferay_blade_workflow_asset_web");
		setSearchable(false);
		setSelectable(false);
	}

	@Override
	public AssetRenderer<Qux> getAssetRenderer(long classPK, int type)
		throws PortalException {

		QuxAssetRenderer quxAssetRenderer = new QuxAssetRenderer(
			_quxLocalService.getQux(classPK));

		quxAssetRenderer.setAssetRendererType(type);
		quxAssetRenderer.setServletContext(_servletContext);

		return quxAssetRenderer;
	}

	@Override
	public String getType() {
		return "qux";
	}

	@Reference
	private QuxLocalService _quxLocalService;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.blade.workflow.asset.web)"
	)
	private ServletContext _servletContext;

}