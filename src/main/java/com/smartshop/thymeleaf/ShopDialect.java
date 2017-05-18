package com.smartshop.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;

public class ShopDialect extends AbstractProcessorDialect {
	public ShopDialect() {
		super("Shop Dialect", // Dialect name
				"shop", // Dialect prefix (hello:*)
				1000); // Dialect precedence
	}

	/*
	 * Initialize the dialect's processors.
	 *
	 * Note the dialect prefix is passed here because, although we set "hello"
	 * to be the dialect's prefix at the constructor, that only works as a
	 * default, and at engine configuration time the user might have chosen a
	 * different prefix to be used.
	 */
	@Override
	public Set<IProcessor> getProcessors(final String dialectPrefix) {
		final Set<IProcessor> processors = new HashSet<IProcessor>();
		processors.add(new ProductOptionAttributeTagProcessor(dialectPrefix));
		return processors;
	}
}