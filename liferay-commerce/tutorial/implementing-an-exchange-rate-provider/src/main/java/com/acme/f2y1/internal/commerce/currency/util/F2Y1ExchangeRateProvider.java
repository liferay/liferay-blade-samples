package com.acme.f2y1.internal.commerce.currency.util;

import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.util.ExchangeRateProvider;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.math.BigDecimal;

import java.util.List;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = "commerce.exchange.provider.key=" + F2Y1ExchangeRateProvider.KEY,
	service = ExchangeRateProvider.class
)
public class F2Y1ExchangeRateProvider implements ExchangeRateProvider {

	public static final String KEY = "Example";

	@Override
	public BigDecimal getExchangeRate(
			CommerceCurrency primaryCommerceCurrency,
			CommerceCurrency secondaryCommerceCurrency)
		throws Exception {

		String primaryCurrencyCode = primaryCommerceCurrency.getCode();
		String secondaryCurrencyCode = secondaryCommerceCurrency.getCode();

		primaryCurrencyCode = StringUtil.toUpperCase(primaryCurrencyCode);
		secondaryCurrencyCode = StringUtil.toUpperCase(secondaryCurrencyCode);

		JSONArray exchangeRatesArray = _getStaticExchangeRates();

		List<String> codesList = JSONUtil.toStringList(
			exchangeRatesArray, "code");

		double primaryRate = _getRateForCode(
			exchangeRatesArray, codesList, primaryCurrencyCode);
		double secondaryRate = _getRateForCode(
			exchangeRatesArray, codesList, secondaryCurrencyCode);

		return new BigDecimal(secondaryRate / primaryRate);
	}

	private double _getRateForCode(
		JSONArray ratesArray, List<String> codesList, String code) {

		int index = codesList.indexOf(code);

		JSONObject jsonObject = ratesArray.getJSONObject(index);

		return jsonObject.getDouble("rate");
	}

	private JSONArray _getStaticExchangeRates() throws Exception {
		Class<?> clazz = getClass();

		String countriesPath =
			"com/acme/f2y1/internal/commerce/exchange/rates" +
				"/exchange_rates-01-10-2019.json";

		String countriesJSON = StringUtil.read(
			clazz.getClassLoader(), countriesPath, false);

		return JSONFactoryUtil.createJSONArray(countriesJSON);
	}

}