package com.smartshop.config;

import java.util.concurrent.TimeUnit;

import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.jhipster.config.JHipsterProperties;

@Configuration
@EnableCaching
@AutoConfigureAfter(value = { MetricsConfiguration.class })
@AutoConfigureBefore(value = { WebConfigurer.class, DatabaseConfiguration.class })
public class CacheConfiguration {

	private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

	public CacheConfiguration(JHipsterProperties jHipsterProperties) {
		JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

		jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(CacheConfigurationBuilder
				.newCacheConfigurationBuilder(Object.class, Object.class,
						ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
				.withExpiry(
						Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
				.build());
	}

	@Bean
	public JCacheManagerCustomizer cacheManagerCustomizer() {
		return cm -> {
			cm.createCache(com.smartshop.core.user.domain.User.class.getName(), jcacheConfiguration);
			cm.createCache(com.smartshop.core.user.domain.Authority.class.getName(), jcacheConfiguration);
			cm.createCache(com.smartshop.core.user.domain.User.class.getName() + ".authorities", jcacheConfiguration);
			cm.createCache(com.smartshop.core.user.domain.PersistentToken.class.getName(), jcacheConfiguration);
			cm.createCache(com.smartshop.core.user.domain.User.class.getName() + ".persistentTokens", jcacheConfiguration);
			cm.createCache(com.smartshop.core.catalog.Category.class.getName(), jcacheConfiguration);
			cm.createCache(com.smartshop.core.catalog.Category.class.getName() + ".categories", jcacheConfiguration);
			cm.createCache(com.smartshop.domain.Attachment.class.getName(), jcacheConfiguration);
			cm.createCache(com.smartshop.domain.EmailSetting.class.getName(), jcacheConfiguration);
			cm.createCache(com.smartshop.domain.MerchantStore.class.getName(), jcacheConfiguration);
			cm.createCache(com.smartshop.core.catalog.Product.class.getName(), jcacheConfiguration);
			cm.createCache(com.smartshop.core.catalog.Product.class.getName() + ".productOptions", jcacheConfiguration);
			cm.createCache(com.smartshop.core.catalog.Product.class.getName() + ".skus", jcacheConfiguration);
			cm.createCache(com.smartshop.core.catalog.Product.class.getName() + ".relationships", jcacheConfiguration);
			cm.createCache(com.smartshop.core.catalog.SKU.class.getName(), jcacheConfiguration);
			cm.createCache(com.smartshop.core.catalog.SKU.class.getName() + ".productOptionValues",
					jcacheConfiguration);
			cm.createCache(com.smartshop.core.catalog.ProductOption.class.getName(), jcacheConfiguration);
			cm.createCache(com.smartshop.core.catalog.ProductOption.class.getName() + ".productOptionValues",
					jcacheConfiguration);
			cm.createCache(com.smartshop.core.catalog.ProductOptionValue.class.getName(), jcacheConfiguration);
			cm.createCache(com.smartshop.core.catalog.ProductOptionValue.class.getName() + ".productOption",
					jcacheConfiguration);
			cm.createCache(com.smartshop.core.catalog.ProductRelationship.class.getName(), jcacheConfiguration);
			cm.createCache(com.smartshop.core.customer.Customer.class.getName(), jcacheConfiguration);
			cm.createCache(com.smartshop.core.order.SalesOrder.class.getName(), jcacheConfiguration);
			cm.createCache(com.smartshop.core.order.SalesOrderStatusHistory.class.getName(), jcacheConfiguration);
			cm.createCache(com.smartshop.core.payment.PaymentConfiguration.class.getName(), jcacheConfiguration);
			cm.createCache(com.smartshop.domain.ShippingConfiguration.class.getName(), jcacheConfiguration);
			// cart
			cm.createCache(com.smartshop.core.cart.Cart.class.getName(), jcacheConfiguration);
			cm.createCache(com.smartshop.core.cart.CartItem.class.getName(), jcacheConfiguration);
			// order
			cm.createCache(com.smartshop.core.order.SalesOrderTotal.class.getName(), jcacheConfiguration);
			cm.createCache(com.smartshop.core.order.OrderProductLine.class.getName(), jcacheConfiguration);
			cm.createCache(com.smartshop.core.payment.Transaction.class.getName(), jcacheConfiguration);

			cm.createCache(com.smartshop.common.reference.Currency.class.getName(), jcacheConfiguration);
			cm.createCache(com.smartshop.core.common.Zone.class.getName(), jcacheConfiguration);

			//
			// jhipster-needle-ehcache-add-entry
		};
	}
}
