package com.liferay.blade.samples.screenname.validator;

import com.liferay.blade.samples.screenname.configuration.CustomScreenNameConfiguration;
import com.liferay.blade.samples.screenname.constants.CustomScreenName;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.security.auth.ScreenNameValidator;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.settings.CompanyServiceSettingsLocator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Locale;

/**
 * User: Romeo Sheshi <a href="mailto:rsheshi@gmail.com">Romeo Sheshi</a>
 * Date: 14/11/2016
 * Time: 10:32
 */
@Component(
        immediate = true,
        property = {"service.ranking:Integer=100"},
        configurationPid = "com.liferay.blade.samples.screenname.configuration.CustomScreenNameConfiguration",
        service = ScreenNameValidator.class

)
public class CustomScreenNameValidator implements ScreenNameValidator {

    private static Log _log = LogFactoryUtil.getLog(CustomScreenNameValidator.class);
    private static final String SEPARATOR =",";

    @Override
    public String getAUIValidatorJS() {
		StringBuilder javascript = new StringBuilder();

    		try {
				Company company = _companyLocalService.getCompanyByWebId(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));
				
				long companyId = company.getCompanyId();
				
				String[] reservedWords = getReservedWords(companyId);
				
				
				javascript.append("function(val) { return !(");
				
				for (int i = 0; i < reservedWords.length; i++) {
					javascript.append("val.indexOf(\"" + reservedWords[i] + "\") !== -1");
					
					if (reservedWords.length > 1 && i < reservedWords.length - 1) {
						javascript.append(" || ");
					}
				}
				
				javascript.append(")}");
				
			} catch (PortalException e) {
			}
    	
        return javascript.toString();
    }

    @Override
    public String getDescription(Locale locale) {
        return "The screen name contains reserved words";
    }

    @Override
    public boolean validate(long companyId, String screenName) {
        String[] reservedWords = getReservedWords(companyId);

        for(String word :reservedWords) {
            if(screenName.toLowerCase().contains(word.toLowerCase())){
                return false;
            }
        }


        return true;
    }


    private String[] getReservedWords(long companyId){
        CustomScreenNameConfiguration configuration = getConfiguration(companyId);
        if(configuration!=null){
            String reservedWord = configuration.reservedWord();
            if(reservedWord!=null && !("").equals(reservedWord))
            return reservedWord.split(SEPARATOR);
        }
        return new String[]{};
    }
    private CustomScreenNameConfiguration getConfiguration(long companyId){
        try {
            return _configurationProvider.getConfiguration(
                    CustomScreenNameConfiguration.class,
                    new CompanyServiceSettingsLocator(
                            companyId, CustomScreenName.SERVICE_NAME));
        } catch (ConfigurationException e) {
            _log.error("Error to inizialize the configuration, the plugin will not be active" );
            if(_log.isDebugEnabled()) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Reference(unbind = "-")
    protected void setConfigurationProvider(
            ConfigurationProvider configurationProvider) {

        _configurationProvider = configurationProvider;
    }
    private ConfigurationProvider _configurationProvider;
    
    @Reference
    private CompanyLocalService _companyLocalService;


}