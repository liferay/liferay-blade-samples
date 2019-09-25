package com.acme.q4b9.internal.commerce.tax;

import com.liferay.commerce.exception.CommerceTaxEngineException;
import com.liferay.commerce.tax.CommerceTaxCalculateRequest;
import com.liferay.commerce.tax.CommerceTaxEngine;
import com.liferay.commerce.tax.CommerceTaxValue;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.math.BigDecimal;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = "commerce.tax.engine.key=" + Q4B9CommerceTaxEngine.KEY,
	service = CommerceTaxEngine.class
)
public class Q4B9CommerceTaxEngine implements CommerceTaxEngine {

	public static final String KEY = "Example";

	@Override
	public CommerceTaxValue getCommerceTaxValue(
			CommerceTaxCalculateRequest commerceTaxCalculateRequest)
		throws CommerceTaxEngineException {

		BigDecimal flatTaxValue = new BigDecimal("1.50");

		if (commerceTaxCalculateRequest.isPercentage()) {
			flatTaxValue = flatTaxValue.divide(new BigDecimal(100.0));

			flatTaxValue = flatTaxValue.multiply(
				commerceTaxCalculateRequest.getPrice());
		}

		return new CommerceTaxValue("flat-tax", KEY, flatTaxValue);
	}

	@Override
	public String getDescription(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(
			resourceBundle, "a-flat-tax-rate-that-does-not-adjust");
	}

	@Override
	public String getName(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "flat-tax");
	}

}