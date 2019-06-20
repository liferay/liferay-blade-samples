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

package com.liferay.blade.workflow.basic.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the Baz service. Represents a row in the &quot;Workflow_Baz&quot; database table, with each column mapped to a property of this class.
 *
 * @author Inácio Nery
 * @see BazModel
 * @generated
 */
@ImplementationClassName("com.liferay.blade.workflow.basic.model.impl.BazImpl")
@ProviderType
public interface Baz extends BazModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.blade.workflow.basic.model.impl.BazImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Baz, Long> BAZ_ID_ACCESSOR =
		new Accessor<Baz, Long>() {

			@Override
			public Long get(Baz baz) {
				return baz.getBazId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Baz> getTypeClass() {
				return Baz.class;
			}

		};

}