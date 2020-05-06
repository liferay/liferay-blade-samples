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

package com.liferay.blade.samples.infolistprovider;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.info.list.provider.InfoListProvider;
import com.liferay.info.list.provider.InfoListProviderContext;
import com.liferay.info.pagination.Pagination;
import com.liferay.info.sort.Sort;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Note: Prior to DXP 7.2 SP1, InfoListProvider was in a different package.
 * If you use that deprecated InfoListProvider class, your code won't work.
 *
 * InfoListProviders appear on the UI as "Content Set Providers"
 *
 * @author Liferay Docs Team, Olaf Kock
 * @see <a href="https://portal.liferay.dev/docs/7-2/frameworks/-/knowledge_base/f/creating-an-information-list-provider">Creating an information list provider</a>
 */
@Component(service = InfoListProvider.class)
public class BladeInfoListProvider implements InfoListProvider<AssetEntry> {

	@Override
	public List<AssetEntry> getInfoList(
		InfoListProviderContext infoListProviderContext) {

		return _assetEntryLocalService.getTopViewedEntries(
			new String[0], false, 0, 20);
	}

	@Override
	public List<AssetEntry> getInfoList(
		InfoListProviderContext infoListProviderContext, Pagination pagination,
		Sort sort) {

		return _assetEntryLocalService.getTopViewedEntries(
			new String[0], !sort.isReverse(), pagination.getStart(),
			pagination.getEnd());
	}

	@Override
	public int getInfoListCount(
		InfoListProviderContext infoListProviderContext) {

		Company company = infoListProviderContext.getCompany();

		return _assetEntryLocalService.getCompanyEntriesCount(
			company.getCompanyId());
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(
			resourceBundle, "blade-info-list-provider-label");
	}

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

}