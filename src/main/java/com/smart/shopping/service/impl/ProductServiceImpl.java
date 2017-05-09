package com.smart.shopping.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smart.shopping.core.catalog.Product;
import com.smart.shopping.core.catalog.ProductOption;
import com.smart.shopping.core.catalog.ProductOptionValue;
import com.smart.shopping.core.catalog.SKU;
import com.smart.shopping.core.catalog.service.CategoryService;
import com.smart.shopping.core.catalog.service.ProductOptionService;
import com.smart.shopping.core.catalog.service.ProductOptionValueService;
import com.smart.shopping.repository.ProductRepository;
import com.smart.shopping.repository.search.ProductSearchRepository;
import com.smart.shopping.service.ProductService;
import com.smart.shopping.service.SKUService;

/**
 * Service Implementation for managing Product.
 */
@Service
@Transactional
public class ProductServiceImpl extends AbstractDomainServiceImpl<Product, Long> implements ProductService {

	private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	private final ProductRepository productRepository;
	private final ProductSearchRepository productSearchRepository;
	@Inject
	CategoryService categoryService;
	@Inject
	ProductOptionService productOptionService;

	@Inject
	SKUService skuService;

	@Inject
	ProductOptionValueService productOptionValueService;

	public ProductServiceImpl(ProductRepository productRepository, ProductSearchRepository productSearchRepository) {
		super(productRepository, productSearchRepository);
		this.productRepository = productRepository;
		this.productSearchRepository = productSearchRepository;
	}

	@Override
	public void generateAdditionalSKUsByBatch(Long productId) {
		Product product = this.productRepository.getOne(productId);
		if (product == null) {
			throw new RuntimeException("null option");
		}
		Set<ProductOption> productOptions = product.getProductOptions();
		if (CollectionUtils.isEmpty(productOptions)) {
			LOGGER.info("product options is empty,product{} ", product.getId());
			return;
		}
		List<List<ProductOptionValue>> allPermutations = generatePermutations(0, new ArrayList<ProductOptionValue>(),
				new ArrayList(product.getProductOptions()));
		if (allPermutations == null) {
			return;
		}
		List<List<ProductOptionValue>> previouslyGeneratedPermutations = new ArrayList<List<ProductOptionValue>>();
		if (CollectionUtils.isNotEmpty(product.getAdditionalSKUs())) {
			for (SKU additionalSKU : product.getAdditionalSKUs()) {
				if (CollectionUtils.isNotEmpty(additionalSKU.getProductOptionValues())) {
					previouslyGeneratedPermutations.add(new ArrayList(additionalSKU.getProductOptionValues()));
				}
			}
		}

		List<List<ProductOptionValue>> permutationsToGenerate = new ArrayList<List<ProductOptionValue>>();
		for (List<ProductOptionValue> permutation : allPermutations) {
			boolean previouslyGenerated = false;
			for (List<ProductOptionValue> generatedPermutation : previouslyGeneratedPermutations) {
				if (isSamePermutation(permutation, generatedPermutation)) {
					previouslyGenerated = true;
					break;
				}
			}

			if (!previouslyGenerated) {
				permutationsToGenerate.add(permutation);
			}
		}

		for (List<ProductOptionValue> permutation : permutationsToGenerate) {
			if (permutation.isEmpty())
				continue;
			SKU permutatedSKU = new SKU();
			permutatedSKU.setName(product.getName());
			permutatedSKU.setRetailPrice(product.getRetailPrice());
			permutatedSKU.setSalePrice(product.getSalePrice());
			permutatedSKU.setDescription(product.getDescription());
			permutatedSKU.setProduct(product);
			LOGGER.info("permutation list : ", permutation);
			permutatedSKU = this.skuService.save(permutatedSKU);

			permutatedSKU.setProductOptionValues(new HashSet<ProductOptionValue>(permutation));
			this.skuService.save(permutatedSKU);
			product.getAdditionalSKUs().add(permutatedSKU);
		}
		product.setHasSKU(true);
		this.save(product);
		return;
	}

	private boolean isSamePermutation(List<ProductOptionValue> perm1, List<ProductOptionValue> perm2) {
		if (perm1.size() == perm2.size()) {
			Collection<Long> perm1Ids = CollectionUtils.collect(perm1, new Transformer<ProductOptionValue, Long>() {
				@Override
				public Long transform(ProductOptionValue input) {
					// TODO Auto-generated method stub
					return input.getId();
				}
			});
			Collection<Long> perm2Ids = CollectionUtils.collect(perm2, new Transformer<ProductOptionValue, Long>() {
				@Override
				public Long transform(ProductOptionValue input) {
					// TODO Auto-generated method stub
					return input.getId();
				}
			});
			return perm1Ids.containsAll(perm2Ids);
		}
		return false;
	}

	private List<List<ProductOptionValue>> generatePermutations(int currentTypeIndex,
			List<ProductOptionValue> currentPermutation, List<ProductOption> options) {
		List<List<ProductOptionValue>> result = new ArrayList<List<ProductOptionValue>>();
		if (currentTypeIndex == options.size()) {
			result.add(currentPermutation);
			return result;
		}

		ProductOption currentOption = options.get(currentTypeIndex);
		Set<ProductOptionValue> allowedValues = currentOption.getProductOptionValues();
		if (CollectionUtils.isEmpty(allowedValues)) {
			result.addAll(generatePermutations(currentTypeIndex + 1, currentPermutation, options));
		}
		for (ProductOptionValue option : allowedValues) {
			List<ProductOptionValue> permutation = new ArrayList<ProductOptionValue>();
			permutation.addAll(currentPermutation);
			permutation.add(option);
			result.addAll(generatePermutations(currentTypeIndex + 1, permutation, options));
		}
		return result;
	}
}