package com.acme.m6a8.web.internal.commerce.discount.rule.type;

import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.discount.model.CommerceDiscountRule;
import com.liferay.commerce.discount.rule.type.CommerceDiscountRuleType;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"commerce.discount.rule.type.key=" + M6A8CommerceDiscountRuleType.KEY,
		"commerce.discount.rule.type.order:Integer=51"
	},
	service = CommerceDiscountRuleType.class
)
public class M6A8CommerceDiscountRuleType implements CommerceDiscountRuleType {

	public static final String KEY = "Example";

	@Override
	public boolean evaluate(
			CommerceDiscountRule commerceDiscountRule,
			CommerceContext commerceContext)
		throws PortalException {

		CommerceOrder commerceOrder = commerceContext.getCommerceOrder();

		if (commerceOrder == null) {
			return false;
		}

		String settingsProperty = commerceDiscountRule.getSettingsProperty(
			commerceDiscountRule.getType());

		int minimumProducts = Integer.valueOf(settingsProperty);

		List<CommerceOrderItem> commerceOrderItems =
			commerceOrder.getCommerceOrderItems();

		if (commerceOrderItems.size() >= minimumProducts) {
			return true;
		}

		return false;
	}

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(
			resourceBundle, "has-a-minimum-number-of-products");
	}

}