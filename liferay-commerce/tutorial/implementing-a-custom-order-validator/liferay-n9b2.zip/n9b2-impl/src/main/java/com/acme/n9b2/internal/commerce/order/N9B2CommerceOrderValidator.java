package com.acme.n9b2.internal.commerce.order;

import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.order.CommerceOrderValidator;
import com.liferay.commerce.order.CommerceOrderValidatorResult;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.math.BigDecimal;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"commerce.order.validator.key=" + N9B2CommerceOrderValidator.KEY,
		"commerce.order.validator.priority:Integer=9"
	},
	service = CommerceOrderValidator.class
)
public class N9B2CommerceOrderValidator implements CommerceOrderValidator {

	public static final String KEY = "Example";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public CommerceOrderValidatorResult validate(
			Locale locale, CommerceOrder commerceOrder, CPInstance cpInstance,
			int quantity)
		throws PortalException {

		if (cpInstance == null) {
			return new CommerceOrderValidatorResult(false);
		}

		BigDecimal price = cpInstance.getPrice();

		if ((price.doubleValue() > 100.0) && (quantity > 10)) {
			ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
				"content.Language", locale, getClass());

			return new CommerceOrderValidatorResult(
				false,
				LanguageUtil.get(
					resourceBundle, "this-item-has-a-maximum-quantity-of-10"));
		}

		return new CommerceOrderValidatorResult(true);
	}

	@Override
	public CommerceOrderValidatorResult validate(
			Locale locale, CommerceOrderItem commerceOrderItem)
		throws PortalException {

		BigDecimal price = commerceOrderItem.getUnitPrice();

		if ((price.doubleValue() > 100.0) &&
			(commerceOrderItem.getQuantity() > 10)) {

			ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
				"content.Language", locale, getClass());

			return new CommerceOrderValidatorResult(
				false,
				LanguageUtil.get(
					resourceBundle,
					"expensive-items-have-a-maximum-quantity-of-10"));
		}

		return new CommerceOrderValidatorResult(true);
	}

}