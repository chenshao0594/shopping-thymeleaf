package com.smart.shopping.admin.controller;

import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codahale.metrics.annotation.Timed;
import com.smart.shopping.config.AppConstants;
import com.smart.shopping.core.catalog.Product;
import com.smart.shopping.core.catalog.ProductOption;
import com.smart.shopping.core.catalog.service.CategoryService;
import com.smart.shopping.core.catalog.service.ProductOptionService;
import com.smart.shopping.domain.Attachment;
import com.smart.shopping.exception.BusinessException;
import com.smart.shopping.service.AttachmentService;
import com.smart.shopping.service.ProductService;

/**
 * REST controller for managing Product.
 */
@Transactional
@Controller("AdminProductController")
@RequestMapping("/" + AppConstants.ADMIN_PREFIX + "/" + ProductController.SECTION_KEY)
public class ProductController extends AbstractDomainController<Product, Long> {

	private final Logger log = LoggerFactory.getLogger(ProductController.class);
	public static final String SECTION_KEY = "products";
	private static final String SKUS_FRAGMENT = "skus/list :: skusList";
	private static final Class ENTITY_CLASS = Product.class;

	private final ProductService productService;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductOptionService productOptionService;

	public ProductController(ProductService productService) {
		super(productService);
		this.productService = productService;
	}

	@Override
	protected void preNew(ModelAndView model) {
		model.addObject("categories", this.categoryService.list());
	};

	@Timed
	@GetMapping("/{productId}/skus")
	public ModelAndView querySKUs(@PathVariable("productId") Long productId, ModelAndView model)
			throws BusinessException {
		model.addObject("productId", productId);
		Product product = this.productService.findOne(productId);
		if (product == null) {
			throw new BusinessException("can't find product by id " + productId);
		}
		model.addObject("options", this.productOptionService.findAll(null));
		model.addObject("skus", product.getAdditionalSKUs());
		model.setViewName(this.getSectionKey() + "/skus");
		return model;
	}

	@Timed
	@PostMapping("/{productId}/options")
	public ModelAndView options(@PathVariable("productId") Long productId, List<Long> productOptionIds,
			BindingResult result, ModelAndView model) {
		if (CollectionUtils.isEmpty(productOptionIds)) {

		}
		Product product = this.productService.findOne(productId);
		for (Long optionId : productOptionIds) {
			ProductOption option = this.productOptionService.findOne(optionId);
			product.getProductOptions().add(option);
		}
		this.productService.save(product);
		model.setViewName(this.getSectionKey() + "/skus");
		model.setViewName("redirect:/" + SECTION_KEY + "/" + productId + "/skus");
		return model;
	}

	@Timed
	@GetMapping("/{id}/images")
	public String getAllImages(@PathVariable("id") Long productId, Model model) {
		log.debug("REST request to get a page of Attachments");
		List<Attachment> results = attachmentService.findAllByBOInfo("product", productId);
		model.addAttribute("attachments", results);
		model.addAttribute("productId", productId);
		return this.getSectionKey() + "/images";
	}

	@Timed
	@PostMapping("{id}/generateSkusByBatch")
	public String generateSkusByBatch(@PathVariable Long id, String optionContent, Model model)
			throws URISyntaxException {
		List<Long> optionIds = new LinkedList<Long>();
		for (String each : optionContent.split("@")) {
			optionIds.add(Long.parseLong(each));
		}
		System.out.println("option ids :" + optionIds);
		log.debug("create generateSkusByBatch----------------{}", id);
		this.productService.generateAdditionalSKUsByBatch(id, optionIds);

		Product product = this.productService.findOne(id);
		model.addAttribute("skus", product.getAdditionalSKUs());
		return SKUS_FRAGMENT;
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