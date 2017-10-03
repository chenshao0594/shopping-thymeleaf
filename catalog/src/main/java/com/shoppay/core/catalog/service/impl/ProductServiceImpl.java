package com.shoppay.core.catalog.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.shoppay.common.service.impl.AbstractDomainServiceImpl;
import com.shoppay.common.utils.MoneyFormatUtils;
import com.shoppay.core.catalog.Category;
import com.shoppay.core.catalog.Product;
import com.shoppay.core.catalog.ProductOption;
import com.shoppay.core.catalog.ProductOptionValue;
import com.shoppay.core.catalog.ProductRelationship;
import com.shoppay.core.catalog.ProductRelationshipEnum;
import com.shoppay.core.catalog.QProduct;
import com.shoppay.core.catalog.SKU;
import com.shoppay.core.catalog.model.ProductOptionDTO;
import com.shoppay.core.catalog.model.ProductOptionPricing;
import com.shoppay.core.catalog.repository.ProductRepository;
import com.shoppay.core.catalog.service.CategoryService;
import com.shoppay.core.catalog.service.ProductOptionService;
import com.shoppay.core.catalog.service.ProductOptionValueService;
import com.shoppay.core.catalog.service.ProductRelationshipService;
import com.shoppay.core.catalog.service.ProductService;
import com.shoppay.core.catalog.service.SKUService;

/**
 * Service Implementation for managing Product.
 */
@Service
@Transactional
public class ProductServiceImpl extends AbstractDomainServiceImpl<Product, Long> implements ProductService {

	private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	private final ProductRepository productRepository;
	@Inject
	CategoryService categoryService;

	@Inject
	ProductOptionService productOptionService;

	@Inject
	ProductRelationshipService productRelationshipService;

	@Inject
	SKUService skuService;

	@Inject
	ProductOptionValueService productOptionValueService;

	public ProductServiceImpl(ProductRepository productRepository ) {
		super(productRepository);
		this.productRepository = productRepository;
	}

	@Override
	public Product findOne(Long id) {
		Product product = productRepository.findOne(id);

		return product;
	}

	@Override
	public void generateAdditionalSKUsByBatch(Long productId, List<Long> optionIds) {
		Product product = this.productRepository.getOne(productId);
		if (product == null) {
			throw new RuntimeException("null product !!!");
		}
		Set<ProductOption> productOptions = new HashSet<ProductOption>();
		for (Long optionId : optionIds) {
			productOptions.add(this.productOptionService.findOne(optionId));
		}
		if (CollectionUtils.isEmpty(productOptions)) {
			LOGGER.info("product options is empty,product{} ", product.getId());
			return;
		}
		List<List<ProductOptionValue>> allPermutations = generatePermutations(0, new ArrayList<ProductOptionValue>(),
				new ArrayList(productOptions));
		if (allPermutations == null) {
			return;
		}
		List<List<ProductOptionValue>> previouslyGeneratedPermutations = new ArrayList<List<ProductOptionValue>>();
		if (CollectionUtils.isNotEmpty(product.getSkus())) {
			for (SKU additionalSKU : product.getSkus()) {
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
			permutatedSKU.setStandardPrice(product.getStandardPrice());
			permutatedSKU.setDescription(product.getDescription());
			permutatedSKU.setProduct(product);
			List<String> skuAttributes = new LinkedList<String>();
			for (ProductOptionValue value : permutation) {
				skuAttributes.add(value.getCode());
			}
			permutatedSKU.setAttributes(String.join("/", skuAttributes));
			// permutatedSKU = this.skuService.save(permutatedSKU);
			permutatedSKU.setProductOptionValues(new HashSet<ProductOptionValue>(permutation));
			this.skuService.save(permutatedSKU);
			product.getSkus().add(permutatedSKU);
		}
		product.getProductOptions().addAll(productOptions);
		product.setHasSKU(true);
		this.save(product);
		return;
	}

	private boolean isSamePermutation(List<ProductOptionValue> perm1, List<ProductOptionValue> perm2) {
		if (perm1.size() == perm2.size()) {
			Collection<Long> perm1Ids = CollectionUtils.collect(perm1, new Transformer<ProductOptionValue, Long>() {
				@Override
				public Long transform(ProductOptionValue input) {
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

	@Override
	public List<Map<String, Long>> countProductsByCategories() {
		List<Map<String, Long>> result = this.productRepository.countProductsByCategories();
		return result;
	}

	@Override
	public List<ProductOptionPricing> buildSKUsPricing(Product product) {
		Set<SKU> skus = product.getSkus();
		List<ProductOptionPricing> skuPricing = new ArrayList<>();
		for (SKU sku : skus) {
			Set<Long> productOptionValueIds = new HashSet<>();
			Set<ProductOptionValue> productOptionValues = sku.getProductOptionValues();
			for (ProductOptionValue productOptionValue : productOptionValues) {
				productOptionValueIds.add(productOptionValue.getId());
			}
			ProductOptionPricing dto = new ProductOptionPricing();
			dto.setRetailPrice(MoneyFormatUtils.formatPrice(sku.getRetailPrice()));
			dto.setStandardPrice(MoneyFormatUtils.formatPrice(sku.getStandardPrice()));
			dto.setSelectedOptions(productOptionValueIds);
			dto.setSkuId(sku.getId());
			skuPricing.add(dto);
		}
		return skuPricing;
	}

	@Override
	public List<ProductOptionDTO> buildProductOptionsDTO(Product product) {
		List<ProductOptionDTO> dtos = new ArrayList<>();
		for (ProductOption option : product.getProductOptions()) {
			ProductOptionDTO dto = new ProductOptionDTO();
			dto.setId(option.getId());
			dto.setType(option.getType().name());
			Map<Long, String> values = new HashMap<>();
			for (ProductOptionValue value : option.getProductOptionValues()) {
				values.put(value.getId(), value.getCode());
			}
			dto.setValues(values);
			dtos.add(dto);
		}

		return dtos;
	}

	@Override
	public String findNameById(Long id) {
		return this.productRepository.findNameById(id);
	}

	@Override
	public Iterable<Product> findOthers(Product product) {
		QProduct qProduct = QProduct.product;
		BooleanExpression exp = qProduct.id.notIn(product.getId());
		return this.productRepository.findAll(exp);
	}

	@Override
	public Iterable<Product> findRelations(Product product) {
		return this.productRelationshipService.getRelations(product);
	}

	@Override
	public void addRelations(long id, List<Long> ids) {
		Product product = this.findOne(id);
		List<Product> exitedRelations = this.productRelationshipService.getRelations(product);
		if (product == null) {
			return;
		}
		for (Long item : ids) {
			Product temp = this.findOne(item);
			if (temp == null) {
				continue;
			}
			if (exitedRelations.contains(temp)) {
				continue;
			}
			ProductRelationship rel = new ProductRelationship();
			rel.setProduct(product);
			rel.setRelatedProduct(temp);
			rel.setType(ProductRelationshipEnum.RELATED);
			product.getRelationships().add(rel);
		}
		this.save(product);
	}

	@Override
	public Product findBySearchURL(String searchURL) {
		QProduct qProduct = QProduct.product;
		return this.productRepository.findOne(qProduct.searchUrl.eq(searchURL));
	}

	@Override
	public long countByCategory(Category category) {
		QProduct qProduct = QProduct.product;
		return this.productRepository.count(qProduct.category.id.eq(category.getId()));
	}

	@Override
	public Page<Product> findAllByCategory(Category category,Pageable pageable) {
		return this.productRepository.findAllByCategory(category, pageable);
	}

	@Override
	public Page<Product> searchByName(String name, Pageable pageable) {
		return this.productRepository.findAllByNameContaining(name, pageable);
	}

}