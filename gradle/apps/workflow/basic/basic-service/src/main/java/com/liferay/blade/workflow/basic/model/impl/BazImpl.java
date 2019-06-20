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

package com.liferay.blade.workflow.basic.model.impl;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model implementation for the Baz service. Represents a row in the &quot;Workflow_Baz&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.blade.workflow.basic.model.Baz</code> interface.
 * </p>
 *
 * @author Inácio Nery
 */
@ProviderType
public class BazImpl extends BazBaseImpl {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a baz model instance should use the {@link com.liferay.blade.workflow.basic.model.Baz} interface instead.
	 */
	public BazImpl() {
	}

}