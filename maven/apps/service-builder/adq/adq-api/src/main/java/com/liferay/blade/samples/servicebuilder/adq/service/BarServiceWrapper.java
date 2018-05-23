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

package com.liferay.blade.samples.servicebuilder.adq.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BarService}.
 *
 * @author Brian Wing Shun Chan
 * @see BarService
 * @generated
 */
@ProviderType
public class BarServiceWrapper implements BarService,
	ServiceWrapper<BarService> {
	public BarServiceWrapper(BarService barService) {
		_barService = barService;
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _barService.getOSGiServiceIdentifier();
	}

	@Override
	public BarService getWrappedService() {
		return _barService;
	}

	@Override
	public void setWrappedService(BarService barService) {
		_barService = barService;
	}

	private BarService _barService;
}