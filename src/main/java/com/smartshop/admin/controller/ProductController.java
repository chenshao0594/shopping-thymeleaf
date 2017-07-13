package com.smartshop.admin.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.codahale.metrics.annotation.Timed;
import com.smartshop.attachment.common.AttachmentEnum;
import com.smartshop.attachment.common.PreviewConfig;
import com.smartshop.constants.AppConstants;
import com.smartshop.core.catalog.Product;
import com.smartshop.core.catalog.ProductOption;
import com.smartshop.core.catalog.SKU;
import com.smartshop.core.catalog.service.CategoryService;
import com.smartshop.core.catalog.service.ProductOptionService;
import com.smartshop.core.catalog.service.ProductRelationshipService;
import com.smartshop.core.catalog.service.ProductService;
import com.smartshop.core.catalog.service.SKUService;
import com.smartshop.domain.Attachment;
import com.smartshop.exception.BusinessException;
import com.smartshop.service.AttachmentService;

/**
 * REST controller for managing Product.
 */
@Transactional
@Controller("AdminProductController")
@RequestMapping(AppConstants.ADMIN_PREFIX + "/" + ProductController.SECTION_KEY)
public class ProductController extends AbstractDomainController<Product, Long> {

	private final Logger log = LoggerFactory.getLogger(ProductController.class);
	public static final String SECTION_KEY = "products";
	private static final String SKUS_FRAGMENT = "skus/list :: skusList";

	private static final String RELATIONS_FRAGMENT = "products/relation-list :: relation-section";
	private static final Class ENTITY_CLASS = Product.class;

	private final ProductService productService;

	@Autowired
	private AttachmentService attachmentService;

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SKUService skuService;

	@Autowired
	private ProductOptionService productOptionService;

	@Autowired
	private ProductRelationshipService productRelationshipService;

	public ProductController(ProductService productService) {
		super(productService);
		this.productService = productService;
	}

	@Override
	protected void preCreate(Product product) {
		SKU sku = new SKU();
		sku.setCode(product.getCode());
		sku.setName(product.getName());
		sku.setRetailPrice(product.getRetailPrice());
		sku.setStandardPrice(product.getStandardPrice());
		sku.setProduct(product);
		sku.setDefault(true);
		sku.setAttributes("default SKU");
		skuService.save(sku);
		product.getSkus().add(sku);
	};

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
		model.addObject("skus", product.getSkus());
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
		model.setViewName("redirect:" + AppConstants.ADMIN_PREFIX + "/" + SECTION_KEY + "/" + productId + "/skus");
		return model;
	}

	@Timed
	@GetMapping("/{id}/images")
	public String getAllImages(@PathVariable("id") Long productId, Model model) {
		log.debug("REST request to get a page of Attachments");
		List<Attachment> results = attachmentService.findAllByBOInfo("product", productId, AttachmentEnum.IMAGE);
		List<PreviewConfig> inititalPreviewConfigs = new LinkedList<PreviewConfig>();
		for (Attachment attachment : results) {
			PreviewConfig config = new PreviewConfig(attachment.getName());
			config.addKey(Long.toString(attachment.getId()));
			config.addSize(attachment.getSize());
			config.addUrl(AppConstants.ADMIN_PREFIX + "/attachments/" + attachment.getId());
			inititalPreviewConfigs.add(config);
		}
		model.addAttribute("priviewConfig", inititalPreviewConfigs);
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
		log.debug("create generateSkusByBatch----------------{}", id);
		this.productService.generateAdditionalSKUsByBatch(id, optionIds);

		Product product = this.productService.findOne(id);
		model.addAttribute("skus", product.getSkus());
		return SKUS_FRAGMENT;
	}

	@Timed
	@GetMapping("/{productId}/relations")
	public ModelAndView queryRelations(@PathVariable("productId") Long productId, ModelAndView model)
			throws BusinessException {
		model.addObject("productId", productId);
		Product product = this.productService.findOne(productId);
		if (product == null) {
			throw new BusinessException("can't find product by id " + productId);
		}
		model.addObject("relations", this.productService.findRelations(product));
		model.setViewName(this.getSectionKey() + "/relations");
		return model;
	}

	@Timed
	@PostMapping("{id}/relations")
	public String addRelations(@PathVariable Long id, @RequestParam("relationIds[]") List<Long> ids, Model model)
			throws URISyntaxException {
		log.debug("add  Relations----------------{}", ids);
		this.productService.addRelations(id, ids);
		List<Product> relations = this.productRelationshipService.getRelations(this.productService.findOne(id));
		model.addAttribute("relations", relations);
		return RELATIONS_FRAGMENT;
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