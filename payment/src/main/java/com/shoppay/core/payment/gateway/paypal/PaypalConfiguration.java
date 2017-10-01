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
	
	private String clientID="AXet8C3s0yrZof41qncgu_jViXaxWBMYCy9nnWVWT5SmLMNk9amfEly9gpetFpvswqjV89bc4bhvTjGn";
	
    private String clientSecret="EEc5uO-oKjJsKM2XCpRhaTbGIBxQjtWnS48SQW-QmG3DgGpyaSLBzd7bkCm-fqCpxM9e1gzqZdKaVhtm";

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
