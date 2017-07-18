package com.shoppay.populator;

import java.util.Locale;

import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.exception.ConversionException;

public abstract class AbstractDataPopulator<Source, Target> implements DataPopulator<Source, Target> {

	private Locale locale;

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public Locale getLocale() {
		return locale;
	}

	@Override
	public Target populate(Source source, MerchantStore store) throws ConversionException {
		return populate(source, createTarget(), store);
	}

	protected abstract Target createTarget();

}
