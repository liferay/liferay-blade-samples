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

package com.liferay.blade.samples.servicebuilder.adq.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the Bar service. Represents a row in the &quot;ADQ_Bar&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see BarModel
 * @see com.liferay.blade.samples.servicebuilder.adq.model.impl.BarImpl
 * @see com.liferay.blade.samples.servicebuilder.adq.model.impl.BarModelImpl
 * @generated
 */
@ImplementationClassName("com.liferay.blade.samples.servicebuilder.adq.model.impl.BarImpl")
@ProviderType
public interface Bar extends BarModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.blade.samples.servicebuilder.adq.model.impl.BarImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Bar, Long> BAR_ID_ACCESSOR = new Accessor<Bar, Long>() {
			@Override
			public Long get(Bar bar) {
				return bar.getBarId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Bar> getTypeClass() {
				return Bar.class;
			}
		};
}