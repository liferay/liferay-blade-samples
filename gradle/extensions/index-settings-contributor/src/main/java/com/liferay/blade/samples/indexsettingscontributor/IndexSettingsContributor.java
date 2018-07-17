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

package com.liferay.blade.samples.indexsettingscontributor;

import com.liferay.portal.search.elasticsearch6.settings.IndexSettingsHelper;
import com.liferay.portal.search.elasticsearch6.settings.TypeMappingsHelper;

import org.osgi.service.component.annotations.Component;

/**
 * @author Liferay
 */
@Component(
	immediate = true,
	service = com.liferay.portal.search.elasticsearch6.settings.IndexSettingsContributor.class
)
public class IndexSettingsContributor
	implements com.liferay.portal.search.elasticsearch6.settings.IndexSettingsContributor {

	@Override
	public int compareTo(
		com.liferay.portal.search.elasticsearch6.
			settings.IndexSettingsContributor indexSettingsContributor) {

		return -1;
	}

	@Override
	public void contribute(
		String indexName, TypeMappingsHelper typeMappingsHelper) {

		try {
			String mappings = ResourceUtil.readResouceAsString(
				"META-INF/resources/mappings/index-type-mappings.json");

			typeMappingsHelper.addTypeMappings(indexName, mappings);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getPriority() {
		return 0;
	}

	@Override
	public void populate(IndexSettingsHelper indexSettingsHelper) {
	}

}