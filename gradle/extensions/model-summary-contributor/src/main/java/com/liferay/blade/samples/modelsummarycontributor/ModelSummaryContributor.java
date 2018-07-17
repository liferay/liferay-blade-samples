package com.liferay.blade.samples.modelsummarycontributor;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Summary;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

import java.util.Locale;

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

		Summary summary = createSummary(document);

		summary.setMaxContentLength(128);

		return summary;
	}

	private Summary createSummary(Document document) {
		String prefix = Field.SNIPPET + StringPool.UNDERLINE;

		String title = document.get(prefix + Field.TITLE, Field.TITLE);

		String company = document.get(prefix + Field.COMPANY_ID, Field.COMPANY_ID);

		return new Summary(title, company);
	}

	private static Log _log = LogFactoryUtil.getLog(
			ModelSummaryContributor.class);
}