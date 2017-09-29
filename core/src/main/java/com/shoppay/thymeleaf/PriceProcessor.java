package com.shoppay.thymeleaf;

import java.math.BigDecimal;

import org.hibernate.service.spi.ServiceException;
import org.springframework.context.ApplicationContext;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.spring4.context.SpringContextUtils;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.exception.BusinessException;
import com.shoppay.core.catalog.service.PricingService;
import com.shoppay.shop.utils.CustomerInfoContextHolder;

public class PriceProcessor extends AbstractElementTagProcessor {
	public static final int PRECEDENCE = 100000;
	public static final String ELEMENT_NAME = "monetary";
	private static final String VALUE = "value";
	private PricingService pricingService;

	public PriceProcessor(final String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, ELEMENT_NAME, (dialectPrefix != null), null, false, PRECEDENCE);

	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag,
			IElementTagStructureHandler structureHandler) {
		// TODO Auto-generated method stub
		final ApplicationContext appCtx = SpringContextUtils.getApplicationContext(context);
		pricingService = appCtx.getBean(PricingService.class);
		String valueContent = tag.getAttribute(VALUE).getValue();
		final IEngineConfiguration configuration = context.getConfiguration();
		final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
		final IStandardExpression expression = parser.parseExpression(context, valueContent);
		final BigDecimal price = (BigDecimal) expression.execute(context);
		final MerchantStore store = CustomerInfoContextHolder.getMerchantStore();
		String formatedPrice = "error";
		try {
			formatedPrice = pricingService.getDisplayAmount(price, store);
		} catch (ServiceException | BusinessException e) {
			e.printStackTrace();
		}
		structureHandler.replaceWith(formatedPrice, false);
	}

}
