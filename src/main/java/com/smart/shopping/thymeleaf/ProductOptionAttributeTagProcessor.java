package com.smart.shopping.thymeleaf;

import java.math.BigDecimal;

import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;
import org.unbescape.html.HtmlEscape;

import com.google.gson.Gson;
import com.smart.shopping.core.catalog.ProductOptionValue;

public class ProductOptionAttributeTagProcessor extends AbstractAttributeTagProcessor {

	private static final String ATTR_NAME = "product_option_value";
	private static final int PRECEDENCE = 10000;

	public ProductOptionAttributeTagProcessor(final String dialectPrefix) {
		super(TemplateMode.HTML, // This processor will apply only to HTML mode
				dialectPrefix, // Prefix to be applied to name for matching
				null, // No tag name: match any tag name
				false, // No prefix to be applied to tag name
				ATTR_NAME, // Name of the attribute that will be matched
				true, // Apply dialect prefix to attribute name
				PRECEDENCE, // Precedence (inside dialect's precedence)
				true); // Remove the matched attribute afterwards
	}

	@Override
	protected void doProcess(final ITemplateContext context, final IProcessableElementTag tag,
			final AttributeName attributeName, final String attributeValue,
			final IElementTagStructureHandler structureHandler) {
		/*
		 * In order to evaluate the attribute value as a Thymeleaf Standard
		 * Expression, we first obtain the parser, then use it for parsing the
		 * attribute value into an expression object, and finally execute this
		 * expression object.
		 */
		final IEngineConfiguration configuration = context.getConfiguration();
		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
		final IStandardExpression expression = parser.parseExpression(context, attributeValue);
		final ProductOptionValue productOptionValue = (ProductOptionValue) expression.execute(context);

		ProductOptionValueDTO dto = new ProductOptionValueDTO();
		dto.setOptionId(productOptionValue.getProductOption().getId());
		dto.setValueId(productOptionValue.getId());
		dto.setValueName(productOptionValue.getCode());
		// if (productOptionValue.getPriceAdjustment() != null) {
		// dto.setPriceAdjustment(productOptionValue.getPriceAdjustment().getAmount());
		// }
		Gson gson = new Gson();
		String optionValueInfo = gson.toJson(dto);
		structureHandler.setAttribute("data-product-option-value", HtmlEscape.escapeHtml5(optionValueInfo));
	}

	private class ProductOptionValueDTO {
		private Long optionId;
		private Long valueId;
		private String valueName;
		private BigDecimal priceAdjustment;

		@SuppressWarnings("unused")
		public Long getOptionId() {
			return optionId;
		}

		public void setOptionId(Long optionId) {
			this.optionId = optionId;
		}

		@SuppressWarnings("unused")
		public Long getValueId() {
			return valueId;
		}

		public void setValueId(Long valueId) {
			this.valueId = valueId;
		}

		@SuppressWarnings("unused")
		public String getValueName() {
			return valueName;
		}

		public void setValueName(String valueName) {
			this.valueName = valueName;
		}

		@SuppressWarnings("unused")
		public BigDecimal getPriceAdjustment() {
			return priceAdjustment;
		}

		public void setPriceAdjustment(BigDecimal priceAdjustment) {
			this.priceAdjustment = priceAdjustment;
		}
	}
}
