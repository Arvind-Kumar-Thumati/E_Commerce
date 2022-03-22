package com.backend.E_Commerce.repositories;

import com.backend.E_Commerce.entities.RedisCategories;
import org.springframework.stereotype.Repository;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;


// The bean 'redisCategoriesRepo', defined in com.backend.E_Commerce.repositories.RedisCategoriesRepo defined in @EnableRedisRepositories declared on RedisRepositoriesRegistrar.EnableRedisRepositoriesConfiguration, could not be registered. A bean with that name has already been defined in com.backend.E_Commerce.repositories.RedisCategoriesRepo defined in @EnableJpaRepositories declared on JpaRepositoriesRegistrar.EnableJpaRepositoriesConfiguration and overriding is disabled.
// @Repository
// public interface RedisCategoriesRepo extends JpaRepository<RedisCategories, Integer>{

// }


// @Repository
// public interface RedisCategoriesRepo extends CrudRepository<RedisCategories, Integer>{

// }

@Repository
public class RedisCategoriesRepo{

    private static final String HASH_KEY = "Category";

    private Logger log = LoggerFactory.getLogger(RedisCategoriesRepo.class);

    @Autowired
    private RedisTemplate redisTemplate;
    
    
    public RedisCategories save( RedisCategories category){
        log.info("Save method.."+category.getCategoryId()+" "+category.getName());
        redisTemplate.opsForHash().put(HASH_KEY, category.getCategoryId(), category);
        return category;
    }

    public List<Object> findAll(){
        log.info("Findall method..");
        return redisTemplate.opsForHash().values(HASH_KEY);
    }

    public RedisCategories findById(Integer id){
        log.info("FindById method.."+id);        
        return (RedisCategories) redisTemplate.opsForHash().get(HASH_KEY, id);
    }

    public String deleteProduct(Integer id){
        log.info("deleteProduct method..");
        redisTemplate.opsForHash().delete(HASH_KEY, id);
        return "deleted";
    }

}
