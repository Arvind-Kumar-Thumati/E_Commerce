package com.backend.E_Commerce.controllers;

import java.util.List;
import java.util.Optional;

import com.backend.E_Commerce.entities.Categories;
import com.backend.E_Commerce.entities.Products;
import com.backend.E_Commerce.repositories.ProductsRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
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
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductsRepo productsRepo;

    @Autowired
    private KafkaTemplate<String, Products> kafkaTemplate;

    @Value(value = "${kafka.productsTopic}")
    private String topic;

    @GetMapping()
    List<Products> getProducts(){
        return productsRepo.findAll();
    }

    @PostMapping()
    Products createProducts(@RequestBody Products product){
        System.out.println("Sending kafka message to sellers portal..." + topic + product);
        kafkaTemplate.send(topic, product);
        return product;        
    }

    @KafkaListener(topics = "${kafka.updateTopic}")
    Products updateProductAfterValidation(Products product){
        System.out.println("Storing into db after validation..."+product);
        return productsRepo.save(product);
    }


    @GetMapping("/{product_id}")
    Products getProductById(@PathVariable Integer product_id){
        return productsRepo.findById(product_id).get();
    }        

    @PostMapping("/{product_id}/category")
    Products addCategoriesByProductId(@PathVariable Integer product_id, @RequestBody Categories categories){
        Optional<Products> optionalProduct = productsRepo.findById(product_id);
        if( optionalProduct.isPresent()){
            Products product = optionalProduct.get();
            if( !product.getCategories().contains(categories)){
                product.getCategories().add(categories);       
                return productsRepo.save(product);
            }
            return product;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested resource does not exists");
    }

    @PutMapping("/{product_id}")
    Products updateProductsById(@PathVariable Integer product_id, @RequestBody Products product){
        System.out.println(product);
        Optional<Products> optionalProduct = productsRepo.findById(product_id);
        if( optionalProduct.isPresent()){
            product.setId( product_id );
            return productsRepo.save(product);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested resource does not exists");
    }
    
    @DeleteMapping("/{product_id}")
    HttpStatus deleteProductsById(@PathVariable Integer product_id){
        Optional<Products> optionalProduct = productsRepo.findById(product_id);
        if( optionalProduct.isPresent() ){
            productsRepo.deleteById(product_id);
            return HttpStatus.OK;
        }
        else return HttpStatus.NOT_FOUND;
    }
}
