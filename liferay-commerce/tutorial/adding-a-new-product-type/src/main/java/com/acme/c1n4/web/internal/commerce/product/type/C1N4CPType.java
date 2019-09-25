package com.acme.c1n4.web.internal.commerce.product.type;

import com.liferay.commerce.product.type.CPType;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
		"commerce.product.type.display.order:Integer=16",
		"commerce.product.type.name=" + C1N4CPType.NAME
	},
	service = CPType.class
)
public class C1N4CPType implements CPType {

	public static final String NAME = "Example";

	@Override
	public void deleteCPDefinition(long cpDefinitionId) throws PortalException {
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "example");
	}

	@Override
	public String getName() {
		return NAME;
	}

}