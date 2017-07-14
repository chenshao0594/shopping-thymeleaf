package com.shoppay.admin.controller;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.shoppay.common.exception.BusinessException;
import com.shoppay.constants.AppConstants;
import com.shoppay.core.catalog.Product;
import com.shoppay.core.catalog.service.ProductRelationshipService;
import com.shoppay.core.catalog.service.ProductService;

@RestController
@RequestMapping(AppConstants.ADMIN_PREFIX + "/catalogue/")
public class CatalogueRestController {
	private final Logger LOGGER = LoggerFactory.getLogger(CatalogueRestController.class);

	@Inject
	private ProductService productService;

	@Inject
	private ProductRelationshipService productRelationshipService;

	@Timed
	@GetMapping("product/{id}/others")
	public ResponseEntity<Iterable<Product>> getOtherProudct(@PathVariable("id") long productId)
			throws BusinessException {
		Product product = this.productService.findOne(productId);
		if (product == null) {
			throw new BusinessException("product {} not exist");
		}
		Iterable<Product> others = this.productService.findOthers(product);
		return ResponseEntity.ok().body(others);

	}

	@Timed
	@DeleteMapping("product/{id}/relations/{relationId}")
	public ResponseEntity<Void> deleteRelation(@PathVariable("id") long productId,
			@PathVariable("relationId") Long relationId) throws BusinessException {
		LOGGER.info("delete relationship   {}", relationId);
		List<Long> ids = new LinkedList<Long>();
		ids.add(relationId);
		Product product = this.productService.findOne(productId);
		if (product == null) {
			throw new BusinessException("product {} not exist");
		}
		this.productRelationshipService.deletes(product, ids);
		return ResponseEntity.ok().build();
	}
}
