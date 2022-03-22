package com.backend.E_Commerce.controllers;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.backend.E_Commerce.config.RabbitmqConfig;
import com.backend.E_Commerce.entities.Analytics;
import com.backend.E_Commerce.entities.RedisAnalytics;
import com.backend.E_Commerce.repositories.AnalyticsRepo;
import com.backend.E_Commerce.repositories.RedisAnalyticsRepo;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analytics")
@EnableCaching
public class AnalyticsController {
    
    @Autowired
    private AnalyticsRepo analyticsRepo;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping
    public List<Analytics> getAnalytics(){
        return analyticsRepo.findAll();
    }


    @GetMapping("/{query_name}")
    @Cacheable(key="#query_name", value="Analytics")
    public Object getAnalyticsById(@PathVariable String query_name){    
        System.out.println("Get method...!");    
        return analyticsRepo.findByQueryName(query_name);
    }

    @PostMapping
    public Analytics createAnalytics(@RequestBody Analytics analytics){        
        System.out.println("Post method...!");            
        analytics.setRequestedTime(Timestamp.valueOf(LocalDateTime.now()));        
        rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE, RabbitmqConfig.KEY, analytics);
        return analyticsRepo.save(analytics);
    }
}
