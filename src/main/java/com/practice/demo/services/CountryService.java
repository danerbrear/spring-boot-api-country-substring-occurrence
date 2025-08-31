package com.practice.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.practice.demo.models.Country;
import com.practice.demo.models.CountryOccurrences;

@Service
public class CountryService {

    private static final String COUNTRY_INFO_URL = "https://restcountries.com/v3.1/all";
    private final RestTemplate restTemplate;
    private final CountryProcessingService processor;

    public CountryService(RestTemplate restTemplate, CountryProcessingService processor) {
        this.restTemplate = restTemplate;
        this.processor = processor;
    }

    public ResponseEntity<CountryOccurrences[]> getCountryList(String input) {
        System.out.println("Getting country info...");
        ResponseEntity<Country[]> response = this.restTemplate.getForEntity(getUrl(), Country[].class);

        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (Country country : response.getBody()) {
            futures.add(this.processor.processCountry(country, input));
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        System.out.println("Finished processing.");

        CountryOccurrences[] countryOccurrencesArr = this.processor.getCountryOccurrencesArr()
                .toArray(new CountryOccurrences[0]);

        ResponseEntity<CountryOccurrences[]> result = new ResponseEntity<CountryOccurrences[]>(countryOccurrencesArr,
                HttpStatusCode.valueOf(200));

        return result;
    }

    private String getUrl() {
        return COUNTRY_INFO_URL + "?fields=name,capital,languages";
    }
}
