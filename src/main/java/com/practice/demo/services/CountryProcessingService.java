package com.practice.demo.services;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.practice.demo.models.Country;

@Service
public class CountryProcessingService {

    // K: Country V: Count of letter occurrance
    private ConcurrentHashMap<String, Integer> countryMap;

    public CountryProcessingService() {
        this.countryMap = new ConcurrentHashMap<>();
    }

    @Async
    public CompletableFuture<Void> processCountry(Country country, String input) {
        System.out.println(Thread.currentThread().getName() + " processing " + country.getName().getCommon());

        if (input.length() < country.getName().getCommon().length()) {
            String temp = "";
            int count = 0;
            for (char letter : country.getName().getCommon().toCharArray()) {

                boolean equalsInput = true;
                int greatestMatchIndex = -1;
                for (int i = 0; i < temp.length(); i++) {
                    if (letter != input.toCharArray()[i]) {
                        equalsInput = false;
                    } else {
                        greatestMatchIndex = i;
                    }
                }

                if (equalsInput) {
                    count++;
                }

                if (greatestMatchIndex >= 0) {
                    temp += letter;
                }
            }

            System.out.printf("Occurences of %s in %s is %d\n", input, country.getName().getCommon(), count);

            this.countryMap.put(country.getName().getCommon(), count);
        } else {
            System.out.println("Input longer than country name");
            this.countryMap.put(country.getName().getCommon(), 0);
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return CompletableFuture.completedFuture(null);
    }
}
