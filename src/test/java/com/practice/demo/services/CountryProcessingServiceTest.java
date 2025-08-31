package com.practice.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.practice.demo.models.CountryOccurrences;

public class CountryProcessingServiceTest {

    private CountryProcessingService service;

    @BeforeEach
    public void setup() {
        this.service = new CountryProcessingService();
    }

    @Test
    public void testGetCountryOccurrencesArr()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Field countryMap = CountryProcessingService.class.getDeclaredField("countryMap");
        countryMap.setAccessible(true);
        countryMap.set(this.service, createTestCountryMap());

        List<CountryOccurrences> result = this.service.getCountryOccurrencesArr();

        List<CountryOccurrences> expected = new ArrayList<>();
        expected.add(getCountryOccurrence1());

        assertEquals(expected, result, "Lists of country occurrences should be equal.");
    }

    private ConcurrentHashMap<String, Integer> createTestCountryMap() {
        ConcurrentHashMap<String, Integer> testCountryMap = new ConcurrentHashMap<>();
        CountryOccurrences test1 = getCountryOccurrence1();
        testCountryMap.put(test1.getName(), test1.getCount());

        return testCountryMap;
    }

    private CountryOccurrences getCountryOccurrence1() {
        return new CountryOccurrences("United States", 2);
    }
}
