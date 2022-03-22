package com.backend.E_Commerce;

// import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.cache.annotation.EnableCaching;
// import org.springframework.context.annotation.Bean;
// import org.springframework.data.redis.cache.RedisCacheConfiguration;
// import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
// import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
// @EnableCaching
public class ECommerceApplication {	

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

	// @Bean
	// public RedisCacheConfiguration cacheConfiguration() {
	// 	return RedisCacheConfiguration.defaultCacheConfig()
	// 			.entryTtl(Duration.ofMinutes(60)) // Changing the cache TTL
	// 			.disableCachingNullValues()
	// 			.serializeValuesWith(SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
	// 	// Defining serialization to retrieve data
	// }

}
