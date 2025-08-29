package com.practice.demo.services;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.practice.demo.models.Country;

@Service
public class CountryProcessingService {
    private ConcurrentHashMap<String, Integer> countryMap;

    public CountryProcessingService() {
        this.countryMap = new ConcurrentHashMap<>();
    }

    @Async
    public CompletableFuture<Void> processCountry(Country country) {
        System.out.println(Thread.currentThread().getName() + " processing " + country.getName().getCommon());
        this.countryMap.put(country.getName().getCommon(), 1);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return CompletableFuture.completedFuture(null);
    }
}
