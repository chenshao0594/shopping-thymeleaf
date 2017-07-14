package com.shoppay.admin.controller;

import java.net.URISyntaxException;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codahale.metrics.annotation.Timed;
import com.shoppay.common.exception.BusinessException;
import com.shoppay.constants.AppConstants;
import com.shoppay.core.catalog.ProductOption;
import com.shoppay.core.catalog.ProductOptionValue;
import com.shoppay.core.catalog.service.ProductOptionService;
import com.shoppay.core.catalog.service.ProductOptionValueService;

/**
 * REST controller for managing ProductOption.
 */
@Controller
@RequestMapping(AppConstants.ADMIN_PREFIX + "/" + ProductOptionController.SECTION_KEY)
public class ProductOptionController extends AbstractDomainController<ProductOption, Long> {

	private final Logger log = LoggerFactory.getLogger(ProductOptionController.class);
	public static final String SECTION_KEY = "productOptions";
	private static final Class ENTITY_CLASS = ProductOption.class;

	private final ProductOptionService productOptionService;

	@Autowired
	private ProductOptionValueService productOptionValueService;

	public ProductOptionController(ProductOptionService productOptionService) {
		super(productOptionService);
		this.productOptionService = productOptionService;
	}

	@Override
	protected void preCreate(ProductOption option) throws BusinessException {
		if (this.productOptionService.isExist(option.getCode())) {
			throw new BusinessException("code has been existed !!!");
		}
	};

	@Timed
	@PostMapping("/{optionId}/values")
	public String createProductOptionValue(@PathVariable("optionId") Long optionId, @Valid ProductOptionValue entity)
			throws URISyntaxException {
		log.debug(" request to save product Option value entity : {}", entity.getId());
		ProductOption productOption = this.productOptionService.findOne(optionId);
		entity.setProductOption(productOption);
		productOption.getProductOptionValues().add(entity);
		this.productOptionService.save(productOption);
		return "redirect:/admin/" + this.getSectionKey() + "/" + optionId;
	}

	@Timed
	@GetMapping("/{optionId}/values")
	public @ResponseBody Set<ProductOptionValue> getAllProductOptionValues(@PathVariable("optionId") Long optionId) {
		ProductOption option = this.productOptionService.findOne(optionId);
		if (option == null) {
			return null;
		}
		return option.getProductOptionValues();
	}

	@Override
	protected String getSectionKey() {
		return SECTION_KEY;
	}

	@Override
	protected Class getEntityClass() {
		return ENTITY_CLASS;
	}

}