package com.smartshop.populator;

import java.util.Locale;

import com.itextpdf.text.pdf.fonts.otf.Language;
import com.smartshop.domain.MerchantStore;
import com.smartshop.exception.ConversionException;

public abstract class AbstractDataPopulator<Source, Target> implements DataPopulator<Source, Target> {

	private Locale locale;

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public Locale getLocale() {
		return locale;
	}

	@Override
	public Target populate(Source source, MerchantStore store, Language language) throws ConversionException {
		return populate(source, createTarget(), store, language);
	}

	protected abstract Target createTarget();

}
