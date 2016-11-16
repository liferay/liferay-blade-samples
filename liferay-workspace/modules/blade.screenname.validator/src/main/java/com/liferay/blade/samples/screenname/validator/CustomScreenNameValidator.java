package com.liferay.blade.samples.screenname.validator;

import com.liferay.portal.kernel.security.auth.ScreenNameValidator;
import org.osgi.service.component.annotations.Component;

import java.util.Locale;

@Component(
		immediate = true,
		service = ScreenNameValidator.class
)
public class CustomScreenNameValidator implements ScreenNameValidator {

	@Override
	public String getAUIValidatorJS() {
		return "function(val) {return !(val.indexOf(\"admin\") !==-1)}";
	}

	@Override
	public String getDescription(Locale locale) {
		return "The screenName contains reserved words";
	}

	@Override
	public boolean validate(long companyId, String screenName) {
		return !screenName.contains("admin");
	}
}