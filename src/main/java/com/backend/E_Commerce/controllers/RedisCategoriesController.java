package com.backend.E_Commerce.controllers;

// import java.util.ArrayList;
// import java.util.Iterator;
import java.util.List;

// import com.backend.E_Commerce.entities.Categories;
import com.backend.E_Commerce.entities.RedisCategories;
import com.backend.E_Commerce.repositories.RedisCategoriesRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.cache.CacheProperties.Redis;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/cached/categories")
@EnableCaching
public class RedisCategoriesController {
    
    @Autowired 
    private RedisCategoriesRepo redisCategoriesRepo;

    Logger log = LoggerFactory.getLogger(RedisCategoriesController.class);

    @GetMapping
    // @Cacheable( value = "categories")
    List<Object> getCategories(){
        log.info("Get method..");        
        // ArrayList<RedisCategories> arr = new ArrayList<>();
        // Iterator<RedisCategories> iter = redisCategoriesRepo.findAll().iterator();        
        // while(iter.hasNext()){
        //     arr.add(iter.next());
        // }
        // return arr;
        return redisCategoriesRepo.findAll();
    }

    @GetMapping("/{category_id}")
    @Cacheable(key="#category_id" ,value = "Category")
    RedisCategories getCategoryById(@PathVariable Integer category_id){        
        return redisCategoriesRepo.findById(category_id);
    }

    @PostMapping
    RedisCategories createCategory(@RequestBody RedisCategories category){
        
        log.info("Category : "+ category.getCategoryId() +" "+category.getName());
        log.info("Category : "+ category);        
        return redisCategoriesRepo.save(category);
    }
}
