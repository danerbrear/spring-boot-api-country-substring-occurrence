package com.practice.demo.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.practice.demo.models.CountryList;

@Service
public class CountryService {

    private static final String COUNTRY_INFO_URL = "https://restcountries.com/v3.1/all";
    private final RestTemplate restTemplate;

    public CountryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<CountryList> getCountryList() {
        System.out.println("Getting country info...");
        ResponseEntity<CountryList> response = this.restTemplate.getForEntity(getUrl(), CountryList.class);
        return response;
    }

    private String getUrl() {
        return COUNTRY_INFO_URL + "?fields=name,capital,languages";
    }
}
