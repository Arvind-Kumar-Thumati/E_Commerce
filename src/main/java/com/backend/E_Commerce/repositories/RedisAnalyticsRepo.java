package com.backend.E_Commerce.repositories;

import java.util.List;

import com.backend.E_Commerce.entities.RedisAnalytics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisAnalyticsRepo {
    private static final String HASH_KEY = "Analytics";

    // private Logger log = LoggerFactory.getLogger(RedisCategoriesRepo.class);

    @Autowired
    private RedisTemplate redisTemplate;
    
    
    public RedisAnalytics save( RedisAnalytics analytics){
        // log.info("Save method.."+analytics.getId()+" "+analytics.getQueryName());
        redisTemplate.opsForHash().put(HASH_KEY, analytics.getQueryName(), analytics);
        return analytics;
    }

    public List<Object> findAll(){
        // log.info("Findall method..");
        return redisTemplate.opsForHash().values(HASH_KEY);
    }

    public RedisAnalytics findById(Integer id){
        // log.info("FindById method.."+id);        
        return (RedisAnalytics) redisTemplate.opsForHash().get(HASH_KEY, id);
    }

    public String deleteProduct(Integer id){
        // log.info("deleteProduct method..");
        redisTemplate.opsForHash().delete(HASH_KEY, id);
        return "deleted";
    }

    public Object findByQueryName(String queryName){
        return redisTemplate.opsForHash().get(HASH_KEY, queryName);
    }
}
