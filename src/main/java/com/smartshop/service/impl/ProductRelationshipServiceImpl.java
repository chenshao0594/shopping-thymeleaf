
package com.smartshop.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.smartshop.core.catalog.Product;
import com.smartshop.core.catalog.ProductRelationship;
import com.smartshop.core.catalog.QProductRelationship;
import com.smartshop.repository.ProductRelationshipRepository;
import com.smartshop.service.ProductRelationshipService;

/**
 * Service Implementation for managing ProductOption.
 */
@Service
@Transactional
public class ProductRelationshipServiceImpl extends AbstractDomainServiceImpl<ProductRelationship, Long>
		implements ProductRelationshipService {

	private final Logger LOGGER = LoggerFactory.getLogger(ProductRelationshipServiceImpl.class);
	private final ProductRelationshipRepository repository;

	public ProductRelationshipServiceImpl(ProductRelationshipRepository repository) {
		super(repository, null);
		this.repository = repository;
	}

	@Override
	public List<Product> getRelations(Product product) {
		QProductRelationship qProductRelationship = QProductRelationship.productRelationship;
		BooleanExpression exp = qProductRelationship.product.id.eq(product.getId());
		Iterable<ProductRelationship> result = this.repository.findAll(exp);
		List<Product> lists = new LinkedList<Product>();
		for (ProductRelationship item : result) {
			lists.add(item.getRelatedProduct());
		}
		return lists;
	}

	@Override
	public void deletes(Product product, List<Long> relationIds) {
		QProductRelationship qProductRelationship = QProductRelationship.productRelationship;
		BooleanExpression idExp = qProductRelationship.id.in(relationIds);
		BooleanExpression productExp = qProductRelationship.product.eq(product);
		Iterable<ProductRelationship> relations = this.repository.findAll(idExp.and(productExp));
		for (ProductRelationship t : relations) {
			this.repository.delete(t);
		}
	}

}
