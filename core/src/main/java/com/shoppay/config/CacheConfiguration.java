package com.shoppay.config;

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

import com.shoppay.common.domain.Attachment;
import com.shoppay.common.domain.MerchantStore;
import com.shoppay.common.reference.Currency;

import io.github.jhipster.config.JHipsterProperties;

@Configuration
@EnableCaching
@AutoConfigureAfter(value = { MetricsConfiguration.class })
@AutoConfigureBefore(value = { WebConfigurer.class, DatabaseConfiguration.class })
public class CacheConfiguration {

	private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

	public CacheConfiguration(JHipsterProperties applicationProperties) {
		JHipsterProperties.Cache.Ehcache ehcache = applicationProperties.getCache().getEhcache();

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
			cm.createCache(com.shoppay.common.user.User.class.getName(), jcacheConfiguration);
			cm.createCache(com.shoppay.common.user.Authority.class.getName(), jcacheConfiguration);
			cm.createCache(com.shoppay.common.user.User.class.getName() + ".authorities", jcacheConfiguration);
			cm.createCache(com.shoppay.common.user.PersistentToken.class.getName(), jcacheConfiguration);
			cm.createCache(com.shoppay.common.user.User.class.getName() + ".persistentTokens", jcacheConfiguration);
			cm.createCache(com.shoppay.core.catalog.Category.class.getName(), jcacheConfiguration);
			cm.createCache(com.shoppay.core.catalog.Category.class.getName() + ".categories", jcacheConfiguration);
			cm.createCache(Attachment.class.getName(), jcacheConfiguration);
			cm.createCache(com.shoppay.domain.EmailSetting.class.getName(), jcacheConfiguration);
			cm.createCache(MerchantStore.class.getName(), jcacheConfiguration);
			cm.createCache(com.shoppay.core.catalog.Product.class.getName(), jcacheConfiguration);
			cm.createCache(com.shoppay.core.catalog.Product.class.getName() + ".productOptions", jcacheConfiguration);
			cm.createCache(com.shoppay.core.catalog.Product.class.getName() + ".skus", jcacheConfiguration);
			cm.createCache(com.shoppay.core.catalog.Product.class.getName() + ".relationships", jcacheConfiguration);
			cm.createCache(com.shoppay.core.catalog.SKU.class.getName(), jcacheConfiguration);
			cm.createCache(com.shoppay.core.catalog.SKU.class.getName() + ".productOptionValues",
					jcacheConfiguration);
			cm.createCache(com.shoppay.core.catalog.ProductOption.class.getName(), jcacheConfiguration);
			cm.createCache(com.shoppay.core.catalog.ProductOption.class.getName() + ".productOptionValues",
					jcacheConfiguration);
			cm.createCache(com.shoppay.core.catalog.ProductOptionValue.class.getName(), jcacheConfiguration);
			cm.createCache(com.shoppay.core.catalog.ProductOptionValue.class.getName() + ".productOption",
					jcacheConfiguration);
			cm.createCache(com.shoppay.core.catalog.ProductRelationship.class.getName(), jcacheConfiguration);
			cm.createCache(com.shoppay.core.customer.Customer.class.getName(), jcacheConfiguration);
			cm.createCache(com.shoppay.core.order.SalesOrder.class.getName(), jcacheConfiguration);
			cm.createCache(com.shoppay.core.order.SalesOrderStatusHistory.class.getName(), jcacheConfiguration);
			cm.createCache(com.shoppay.core.payment.PaymentConfiguration.class.getName(), jcacheConfiguration);
			cm.createCache(com.shoppay.domain.ShippingConfiguration.class.getName(), jcacheConfiguration);
			// cart
			cm.createCache(com.shoppay.core.cart.Cart.class.getName(), jcacheConfiguration);
			cm.createCache(com.shoppay.core.cart.CartItem.class.getName(), jcacheConfiguration);
			// order
			cm.createCache(com.shoppay.core.order.SalesOrderTotal.class.getName(), jcacheConfiguration);
			cm.createCache(com.shoppay.core.order.OrderProductLine.class.getName(), jcacheConfiguration);
			cm.createCache(com.shoppay.core.transaction.Transaction.class.getName(), jcacheConfiguration);

			cm.createCache(Currency.class.getName(), jcacheConfiguration);
			cm.createCache(com.shoppay.common.reference.Zone.class.getName(), jcacheConfiguration);

			//
			// jhipster-needle-ehcache-add-entry
		};
	}
}
