package com.practice.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.practice.demo.models.Country;

@Service
public class CountryService {

    private static final String COUNTRY_INFO_URL = "https://restcountries.com/v3.1/all";
    private final RestTemplate restTemplate;
    private final CountryProcessingService processor;

    public CountryService(RestTemplate restTemplate, CountryProcessingService processor) {
        this.restTemplate = restTemplate;
        this.processor = processor;
    }

    public ResponseEntity<Country[]> getCountryList() {
        System.out.println("Getting country info...");
        ResponseEntity<Country[]> response = this.restTemplate.getForEntity(getUrl(), Country[].class);

        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (Country country : response.getBody()) {
            futures.add(this.processor.processCountry(country));
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        System.out.println("Finished processing.");

        return response;
    }

    private String getUrl() {
        return COUNTRY_INFO_URL + "?fields=name,capital,languages";
    }
}
