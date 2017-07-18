package com.shoppay.thymeleaf;

import org.apache.commons.lang3.StringUtils;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

import com.shoppay.common.reference.Address;

public class AddressProcessor extends AbstractElementTagProcessor {
	public static final int PRECEDENCE = 100000;
	public static final String ELEMENT_NAME = "address";
	private static final String VALUE = "value";

	public AddressProcessor(final String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, ELEMENT_NAME, (dialectPrefix != null), null, false, PRECEDENCE);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		// TODO Auto-generated method stub
		String value = tag.getAttribute(VALUE).getValue();
		final IEngineConfiguration configuration = context.getConfiguration();
		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
		/*
		 * Parse the attribute value as a Thymeleaf Standard Expression
		 */
		final IStandardExpression expression = parser.parseExpression(context, value);
		final Address address = (Address) expression.execute(context);
		if (address == null) {
			structureHandler.replaceWith("", false);
			return;
		}

		StringBuilder result = new StringBuilder();
		result.append(address.getFirstName()).append("&nbsp;").append(address.getLastName()).append("<br/>");
		if (!StringUtils.isBlank(address.getCompany())) {
			result.append(address.getCompany()).append("<br/>");
		}
		if (!StringUtils.isBlank(address.getAddress())) {
			result.append(address.getAddress()).append("<br/>");
		}
		if (!StringUtils.isBlank(address.getCity())) {
			result.append(address.getCity()).append("<br/>");
		}
		if (!StringUtils.isBlank(address.getCountry())) {
			result.append(address.getCountry()).append("<br/>");
		}
		if (!StringUtils.isBlank(address.getPostalCode())) {
			result.append(address.getPostalCode()).append("<br/>");
		}
		if (!StringUtils.isBlank(address.getPhone())) {
			result.append(address.getPhone()).append("<br/>");
		}
		structureHandler.replaceWith(result.toString(), false);
	}

}
