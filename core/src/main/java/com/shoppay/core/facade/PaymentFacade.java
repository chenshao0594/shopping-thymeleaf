package com.shoppay.core.facade;

import java.util.List;

import com.shoppay.core.payment.PaymentConfiguration;

public interface PaymentFacade {

	public List<PaymentConfiguration> methods();

}