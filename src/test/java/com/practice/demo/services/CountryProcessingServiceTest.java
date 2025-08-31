package com.practice.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
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
        expected.add(getCountryOccurrence2());
        expected.add(getCountryOccurrence3());
        Collections.sort(expected);

        assertEquals(expected, result, "Lists of country occurrences should be equal.");
    }

    private ConcurrentHashMap<String, Integer> createTestCountryMap() {
        ConcurrentHashMap<String, Integer> testCountryMap = new ConcurrentHashMap<>();
        CountryOccurrences test1 = getCountryOccurrence1();
        testCountryMap.put(test1.getName(), test1.getCount());
        CountryOccurrences test2 = getCountryOccurrence2();
        testCountryMap.put(test2.getName(), test2.getCount());
        CountryOccurrences test3 = getCountryOccurrence3();
        testCountryMap.put(test3.getName(), test3.getCount());

        return testCountryMap;
    }

    private CountryOccurrences getCountryOccurrence1() {
        return new CountryOccurrences("United States", 2);
    }

    private CountryOccurrences getCountryOccurrence2() {
        return new CountryOccurrences("United Arab Emirates", 3);
    }

    private CountryOccurrences getCountryOccurrence3() {
        return new CountryOccurrences("Tunisia", 1);
    }
}
