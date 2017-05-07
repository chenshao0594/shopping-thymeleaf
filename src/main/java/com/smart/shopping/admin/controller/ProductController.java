package com.smart.shopping.admin.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.codahale.metrics.annotation.Timed;
import com.smart.shopping.core.catalog.Product;
import com.smart.shopping.core.catalog.service.CategoryService;
import com.smart.shopping.core.catalog.service.ProductOptionService;
import com.smart.shopping.domain.Attachment;
import com.smart.shopping.service.AttachmentService;
import com.smart.shopping.service.ProductService;
import com.smart.shopping.web.rest.util.PaginationUtil;

import io.swagger.annotations.ApiParam;

/**
 * REST controller for managing Product.
 */
@Transactional
@Controller
@RequestMapping("/" + ProductController.SECTION_KEY)
public class ProductController extends AbstractDomainController<Product, Long> {

	private final Logger log = LoggerFactory.getLogger(ProductController.class);
	public static final String SECTION_KEY = "products";
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
	public ModelAndView querySKUs(@PathVariable("productId") Long productId, ModelAndView model) {
		model.addObject("productId", productId);
		model.addObject("options", this.productOptionService.list());
		model.setViewName(this.getSectionKey() + "/skus");
		System.out.println("options:  " + this.productOptionService.list());
		return model;
	}

	@Timed
	@GetMapping("/{id}/attachments")
	public ResponseEntity<List<Attachment>> getAllAttachments(@PathVariable("id") Long boid,
			@ApiParam Pageable pageable) {
		log.debug("REST request to get a page of Attachments");
		Page<Attachment> page = attachmentService.findAllByBOInfo("product", boid, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,
				"/api/products/" + boid + "/attachments");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
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