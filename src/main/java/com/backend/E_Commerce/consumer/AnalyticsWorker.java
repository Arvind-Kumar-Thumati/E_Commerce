package com.backend.E_Commerce.consumer;

import com.backend.E_Commerce.config.RabbitmqConfig;
// import com.backend.E_Commerce.config.RabbitmqConfig.*;
import com.backend.E_Commerce.entities.RedisAnalytics;
import com.backend.E_Commerce.repositories.RedisAnalyticsRepo;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnalyticsWorker {
    @Autowired
    private RedisAnalyticsRepo analyticsRepo;

    @RabbitListener(queues = RabbitmqConfig.QUEUE)
    public void addAnalytics(RedisAnalytics analytics){
        System.out.println("From mq : Storing into reddis..!");
        analyticsRepo.save(analytics);
    }
}
