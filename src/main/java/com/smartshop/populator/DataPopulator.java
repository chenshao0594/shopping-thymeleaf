package com.smartshop.populator;

import com.smartshop.domain.MerchantStore;
import com.smartshop.exception.ConversionException;

public interface DataPopulator<Source, Target> {

	public Target populate(Source source, Target target, MerchantStore store) throws ConversionException;

	public Target populate(Source source, MerchantStore store) throws ConversionException;

}
