package com.backend.E_Commerce.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Analytics implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "analytics_generator")
    @SequenceGenerator(name="analytics_generator", sequenceName = "analytics_seq", allocationSize=50)
    private Integer id;
    private String queryName;
    private Timestamp requestedTime;

    public Analytics(){}

    public Analytics(Integer id, String queryName, Timestamp requstedTime){}

    public Integer getId() {
        return id;
    }
    public String getQueryName() {
        return queryName;
    }
    public Timestamp getRequestedTime() {
        return requestedTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }
    public void setRequestedTime(Timestamp requestedTime) {
        this.requestedTime = requestedTime;
    }

}
