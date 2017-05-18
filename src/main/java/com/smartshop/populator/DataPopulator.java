package com.smartshop.populator;

import com.itextpdf.text.pdf.fonts.otf.Language;
import com.smartshop.domain.MerchantStore;
import com.smartshop.exception.ConversionException;

public interface DataPopulator<Source, Target> {

	public Target populate(Source source, Target target, MerchantStore store, Language language)
			throws ConversionException;

	public Target populate(Source source, MerchantStore store, Language language) throws ConversionException;

}
