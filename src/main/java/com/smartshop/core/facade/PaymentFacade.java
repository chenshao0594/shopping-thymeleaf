package com.smartshop.core.facade;

import java.util.List;

import com.smartshop.domain.PaymentConfiguration;

public interface PaymentFacade {

	public List<PaymentConfiguration> methods();

}