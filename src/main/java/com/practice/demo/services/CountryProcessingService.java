package com.practice.demo.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.practice.demo.models.Country;
import com.practice.demo.models.CountryOccurrences;

import lombok.Getter;

@Service
public class CountryProcessingService {

    @Getter
    // K: Country V: Count of letter occurrance
    private ConcurrentHashMap<String, Integer> countryMap;

    public CountryProcessingService() {
        this.countryMap = new ConcurrentHashMap<>();
    }

    @Async
    public CompletableFuture<Void> processCountry(Country country, String input) {
        System.out.println(Thread.currentThread().getName() + " processing " + country.getName().getCommon());

        String name = country.getName().getCommon();

        int count = 0;
        int index = 0;
        while (name.toLowerCase().indexOf(input.toLowerCase(), index) != -1) {
            count++;
            index += name.toLowerCase().indexOf(input.toLowerCase(), index) + input.length();
        }

        countryMap.put(name, count);

        System.out.printf("Occurences of %s in %s is %d\n", input, name, count);

        return CompletableFuture.completedFuture(null);
    }

    public List<CountryOccurrences> getCountryOccurrencesArr() {
        List<CountryOccurrences> countryList = new ArrayList<>();
        Set<String> keys = countryMap.keySet();

        for (String key : keys) {
            int val = countryMap.get(key);
            if (val > 0) {
                countryList.add(new CountryOccurrences(key, val));
            }
        }

        Collections.sort(countryList);

        return countryList;
    }
}
