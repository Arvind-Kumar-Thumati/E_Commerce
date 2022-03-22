package com.backend.E_Commerce.repositories;

import java.util.List;

import com.backend.E_Commerce.entities.Analytics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyticsRepo extends JpaRepository<Analytics, Integer>{
    List<Analytics> findByQueryName(String queryName);
}
