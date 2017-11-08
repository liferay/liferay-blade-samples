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

package com.liferay.blade.samples.jndiservicebuilder.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Region service. Represents a row in the &quot;region&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see RegionModel
 * @see com.liferay.blade.samples.jndiservicebuilder.model.impl.RegionImpl
 * @see com.liferay.blade.samples.jndiservicebuilder.model.impl.RegionModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.blade.samples.jndiservicebuilder.model.impl.RegionImpl")
@ProviderType
public interface Region extends RegionModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.blade.samples.jndiservicebuilder.model.impl.RegionImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Region, Long> REGION_ID_ACCESSOR = new Accessor<Region, Long>() {
			@Override
			public Long get(Region region) {
				return region.getRegionId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Region> getTypeClass() {
				return Region.class;
			}
		};
}