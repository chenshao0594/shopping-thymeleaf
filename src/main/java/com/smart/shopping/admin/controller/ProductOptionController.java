package com.smart.shopping.admin.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codahale.metrics.annotation.Timed;
import com.smart.shopping.core.catalog.ProductOption;
import com.smart.shopping.core.catalog.ProductOptionValue;
import com.smart.shopping.core.catalog.service.ProductOptionService;
import com.smart.shopping.core.catalog.service.ProductOptionValueService;
import com.smart.shopping.web.rest.util.HeaderUtil;

/**
 * REST controller for managing ProductOption.
 */
@Controller
@RequestMapping("/" + ProductOptionController.SECTION_KEY)
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
	protected void preCreate(ProductOption option) {
		ProductOption productOption = this.productOptionService.getByCode(option.getMerchantStore(), option.getCode());
		if (productOption != null) {
			// throw new BusinessException("code has been existed !!!");
		}
	};

	@Timed
	@PostMapping("/{id}/values")
	public @ResponseBody ResponseEntity<ProductOptionValue> createProductOptionValue(@PathVariable Long id,
			@Valid @RequestBody ProductOptionValue entity) throws URISyntaxException {
		log.debug("REST request to save entity : {}", entity);
		if (entity.getId() != null) {
			return null;
		}
		ProductOption productOption = this.productOptionService.findOne(id);
		entity.setProductOption(productOption);
		productOption.getProductOptionValues().add(entity);
		ProductOption result = this.productOptionService.save(productOption);
		return ResponseEntity.created(new URI("/api/product-options/" + id + "/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert("", result.getId().toString())).body(entity);

	}

	@Timed
	@GetMapping("/{id}/values")
	public @ResponseBody Set<ProductOptionValue> getAllProductOptionValues(@PathVariable Long id) {
		ProductOption option = this.productOptionService.findOne(id);
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