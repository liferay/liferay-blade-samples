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

package com.liferay.blade.samples.servicebuilder.adq.service.impl;

import com.liferay.blade.samples.servicebuilder.adq.model.Bar;
import com.liferay.blade.samples.servicebuilder.adq.service.base.BarLocalServiceBaseImpl;

/**
 * The implementation of the bar local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.blade.samples.servicebuilder.adq.service.BarLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BarLocalServiceBaseImpl
 * @see com.liferay.blade.samples.servicebuilder.adq.service.BarLocalServiceUtil
 */
public class BarLocalServiceImpl extends BarLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.liferay.blade.samples.servicebuilder.adq.service.BarLocalServiceUtil} to access the bar local service.
	 */
	public Bar addBarWithoutId(Bar bar) {
		long resourcePrimKey = counterLocalService.increment();

		bar.setBarId(resourcePrimKey);

		return addBar(bar);
	}

	public String barLocal() {
		return "barLocal";
	}

}