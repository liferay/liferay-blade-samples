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

package com.liferay.blade.workflow.asset.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the Qux service. Represents a row in the &quot;Workflow_Qux&quot; database table, with each column mapped to a property of this class.
 *
 * @author Inácio Nery
 * @see QuxModel
 * @generated
 */
@ImplementationClassName("com.liferay.blade.workflow.asset.model.impl.QuxImpl")
@ProviderType
public interface Qux extends PersistedModel, QuxModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.blade.workflow.asset.model.impl.QuxImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Qux, Long> QUX_ID_ACCESSOR =
		new Accessor<Qux, Long>() {

			@Override
			public Long get(Qux qux) {
				return qux.getQuxId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Qux> getTypeClass() {
				return Qux.class;
			}

		};

}