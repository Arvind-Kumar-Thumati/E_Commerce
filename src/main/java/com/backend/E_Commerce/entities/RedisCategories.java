package com.backend.E_Commerce.entities;

// import java.io.Serializable;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("Category")
public class RedisCategories extends Categories{    
    public RedisCategories(Categories category){
        this.setCategoryId(category.getCategoryId());
        this.setName(category.getName());
    }    
    public RedisCategories(Integer categoryId, String name){        
        this.categoryId = categoryId;
        super.setName(name);
        
    }    
}
