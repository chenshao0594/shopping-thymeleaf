package com.shoppay.core.facade;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.shoppay.core.payment.PaymentConfiguration;
import com.shoppay.core.payment.service.PaymentConfigurationService;

@Service
public class PaymentFacadeImpl implements PaymentFacade {

	@Inject
	private PaymentConfigurationService paymentConfigurationService;

	@Override
	public List<PaymentConfiguration> methods() {
		return this.paymentConfigurationService.list();
	}

}
