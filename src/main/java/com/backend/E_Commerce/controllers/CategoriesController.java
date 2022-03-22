package com.backend.E_Commerce.controllers;

import com.backend.E_Commerce.entities.Categories;
// import com.backend.E_Commerce.entities.RedisCategories;
import com.backend.E_Commerce.repositories.CategoriesRepo;
// import com.backend.E_Commerce.repositories.RedisCategoriesRepo;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/categories")
@EnableCaching
public class CategoriesController {
    @Autowired
    private CategoriesRepo categoriesRepo;

    private Logger log = LoggerFactory.getLogger(Categories.class);

    private void simulateSlowService() {
        try {
            long time = 300L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    @GetMapping
    List<Categories> getCategories() {
        return categoriesRepo.findAll();
    }

    @GetMapping("/{category_id}")
    @Cacheable(key = "#category_id", value = "Category")
    public Categories findCategoryById(@PathVariable Integer category_id){
        log.info("Searching for id : "+category_id);
        simulateSlowService();
        return categoriesRepo.findById(category_id).get();
    }

    @PostMapping()
    Categories createCategory(@RequestBody Categories category) {
        return categoriesRepo.save(category);
    }

    @CachePut(key = "#category.getCategoryId()", value = "Category")
    @PutMapping("/{category_id}")
    Categories updateCategory(@RequestBody Categories category, @PathVariable Integer category_id) {
        Optional<Categories> optionalCategory = categoriesRepo.findById(category_id);
        if (optionalCategory.isPresent()) {
            category.setCategoryId(category_id);
            return categoriesRepo.save(category);
        }
        throw new ResponseStatusException(HttpStatus.FOUND, "Requested resource does not exists");
    }

    @CacheEvict(key = "#category_id", value = "Category")
    @DeleteMapping("/{category_id}")
    HttpStatus deleteCategory(@PathVariable Integer category_id) {
        Optional<Categories> optionalCategory = categoriesRepo.findById(category_id);
        if (optionalCategory.isPresent()) {
            categoriesRepo.deleteById(category_id);
            return HttpStatus.OK;
        }
        throw new ResponseStatusException(HttpStatus.FOUND, "Requested resource does not exists");
    }

    @GetMapping("/cached")
    @Cacheable(value = "Categories")
    List<Categories> getCategoriesCached() {
        log.info("Get method..");
        return categoriesRepo.findAll();
    }

    @GetMapping("/cached/{category_id}")
    @Cacheable(key = "#category_id", value = "Categories")
    Categories getCategoryByIdCached(@PathVariable Integer category_id) {
        log.info(categoriesRepo.getById(category_id).getName());
        return categoriesRepo.getById(category_id);
    }

    @CachePut(key = "#category.id", value = "Categories")
    @PostMapping("/cached")
    Categories createCategoryCached(@RequestBody Categories category) {
        return categoriesRepo.save(category);
    }

    @CachePut(key = "#category_id", value = "Categories")
    @PutMapping("/cached/{category_id}")
    Categories updateCategoryCached(@RequestBody Categories category, @PathVariable Integer category_id) {
        Optional<Categories> optionalCategory = categoriesRepo.findById(category_id);
        if (optionalCategory.isPresent()) {
            category.setCategoryId(category_id);
            return categoriesRepo.save(category);
        }
        throw new ResponseStatusException(HttpStatus.FOUND, "Requested resource does not exists");
    }

    @CacheEvict(value = "Categories", key = "#category_id")
    @DeleteMapping("/cached/{category_id}")
    HttpStatus deleteCategoryCached(@PathVariable Integer category_id) {
        Optional<Categories> optionalCategory = categoriesRepo.findById(category_id);
        if (optionalCategory.isPresent()) {
            categoriesRepo.deleteById(category_id);
            return HttpStatus.OK;
        }
        throw new ResponseStatusException(HttpStatus.FOUND, "Requested resource does not exists");
    }

}
