package com.shoppay.core.payment.gateway.paypal;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

@Configuration
public class PaypalConfiguration {
	
	private String clientID="AQZBrSKvlPyxKw7ECIQJbTVfQ_ALhfL3ZaUxysKJYjzWvgVG-1qRZ8-C-t-Do9l6NJTTHAN2MRRrSLkW";
	
    private String clientSecret="ECho-gfJPf1M3xagNLq54ufL-4dfzcjpceU53HYW7mfbT6qs8PUD46cDRdmg1VIIOl6zFfGYGgu0VBX_";

    private String mode="sandbox";
    @Bean
	public Map<String, String> paypalSdkConfig(){
		Map<String, String> sdkConfig = new HashMap<>();
		sdkConfig.put("mode", mode);
		return sdkConfig;
	}
	
	@Bean
	public OAuthTokenCredential authTokenCredential(){
		return new OAuthTokenCredential(clientID, clientSecret, paypalSdkConfig());
	}
	
	
	
	@Bean
	public APIContext apiContext() throws PayPalRESTException{
		APIContext apiContext = new APIContext( clientID,  clientSecret,  mode);
		apiContext.setConfigurationMap(paypalSdkConfig());
		return apiContext;
	}

}
