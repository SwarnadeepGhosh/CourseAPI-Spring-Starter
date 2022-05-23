package com.swarna.courseapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;

@Configuration
public class CacheConfig {
	
//	@Bean
	public Config cacheConfig() {
		return new Config()
				.setInstanceName("hazle-instance") // instance name for caching
				.addMapConfig(new MapConfig() // Adding one cache, we can configure as many cache as we want
						.setName("topic-cache") // cache will be saved with this name
						.setTimeToLiveSeconds(3000)); // cache will be evicted after 5 minutes
	}

}
