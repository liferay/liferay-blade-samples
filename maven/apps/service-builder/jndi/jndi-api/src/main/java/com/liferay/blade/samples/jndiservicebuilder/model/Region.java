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