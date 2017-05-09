package com.smart.shopping.config;

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
			cm.createCache(com.smart.shopping.domain.User.class.getName(), jcacheConfiguration);
			cm.createCache(com.smart.shopping.domain.Authority.class.getName(), jcacheConfiguration);
			cm.createCache(com.smart.shopping.domain.User.class.getName() + ".authorities", jcacheConfiguration);
			cm.createCache(com.smart.shopping.domain.PersistentToken.class.getName(), jcacheConfiguration);
			cm.createCache(com.smart.shopping.domain.User.class.getName() + ".persistentTokens", jcacheConfiguration);
			cm.createCache(com.smart.shopping.core.catalog.Category.class.getName(), jcacheConfiguration);
			cm.createCache(com.smart.shopping.core.catalog.Category.class.getName() + ".categories",
					jcacheConfiguration);
			cm.createCache(com.smart.shopping.domain.Attachment.class.getName(), jcacheConfiguration);
			cm.createCache(com.smart.shopping.domain.EmailSetting.class.getName(), jcacheConfiguration);
			cm.createCache(com.smart.shopping.domain.MerchantStore.class.getName(), jcacheConfiguration);
			cm.createCache(com.smart.shopping.core.catalog.Product.class.getName(), jcacheConfiguration);
			cm.createCache(com.smart.shopping.core.catalog.Product.class.getName() + ".productOptions",
					jcacheConfiguration);
			cm.createCache(com.smart.shopping.core.catalog.Product.class.getName() + ".additionalSKUs",
					jcacheConfiguration);
			cm.createCache(com.smart.shopping.core.catalog.SKU.class.getName(), jcacheConfiguration);
			cm.createCache(com.smart.shopping.core.catalog.SKU.class.getName() + ".productOptionValues",
					jcacheConfiguration);
			cm.createCache(com.smart.shopping.core.catalog.ProductOption.class.getName(), jcacheConfiguration);
			cm.createCache(com.smart.shopping.core.catalog.ProductOption.class.getName() + ".productOptionValues",
					jcacheConfiguration);
			cm.createCache(com.smart.shopping.core.catalog.ProductOptionValue.class.getName(), jcacheConfiguration);
			cm.createCache(com.smart.shopping.core.catalog.ProductOptionValue.class.getName() + ".productOption",
					jcacheConfiguration);
			cm.createCache(com.smart.shopping.domain.Customer.class.getName(), jcacheConfiguration);

			// jhipster-needle-ehcache-add-entry
		};
	}
}
