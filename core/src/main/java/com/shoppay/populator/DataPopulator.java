package com.shoppay.populator;

import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.exception.ConversionException;

public interface DataPopulator<Source, Target> {

	public Target populate(Source source, Target target, MerchantStore store) throws ConversionException;

	public Target populate(Source source, MerchantStore store) throws ConversionException;

}
