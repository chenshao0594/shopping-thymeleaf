package com.smartshop.core.facade;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.smartshop.core.catalog.service.PaymentConfigurationService;
import com.smartshop.core.payment.PaymentConfiguration;

@Service
public class PaymentFacadeImpl implements PaymentFacade {

	@Inject
	private PaymentConfigurationService paymentConfigurationService;

	@Override
	public List<PaymentConfiguration> methods() {
		return this.paymentConfigurationService.list();
	}

}
