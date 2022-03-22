// package com.backend.E_Commerce;

// import com.backend.E_Commerce.repositories.RedisCategoriesRepo;
// import com.backend.E_Commerce.entities.RedisCategories;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
// import org.springframework.util.Assert;


// @DataRedisTest
// public class RedisCategoriesTests {
    
//     @Autowired
//     private RedisCategoriesRepo redisCategoriesRepo;

//     @Test
//     public void testAdd(){
//         RedisCategories category = redisCategoriesRepo.save(new RedisCategories(1, "Smartphones"));
//         Assert.notNull(category, "message");
//     }
// }
