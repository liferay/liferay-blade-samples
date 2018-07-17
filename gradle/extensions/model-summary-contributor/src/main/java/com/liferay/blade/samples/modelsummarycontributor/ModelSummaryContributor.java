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

package com.liferay.blade.samples.modelsummarycontributor;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Summary;

import java.util.Locale;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Liferay
 */
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.blogs.model.BlogsEntry",
	service = com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor.class
)
public class ModelSummaryContributor
	implements com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor {

	@Activate
	public void activate(BundleContext bundleContext) {
		_log.info("activate");
	}

	@Deactivate
	public void deactivate() {
		_log.info("deactivate");
	}

	@Override
	public Summary getSummary(
		Document document, Locale locale, String snippet) {

		Summary summary = _createSummary(document);

		summary.setMaxContentLength(128);

		return summary;
	}

	private Summary _createSummary(Document document) {
		String prefix = Field.SNIPPET + StringPool.UNDERLINE;

		String title = document.get(prefix + Field.TITLE, Field.TITLE);

		String company = document.get(
			prefix + Field.COMPANY_ID, Field.COMPANY_ID);

		return new Summary(title, company);
	}

	private static Log _log = LogFactoryUtil.getLog(
		ModelSummaryContributor.class);

}