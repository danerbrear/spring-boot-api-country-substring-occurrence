package com.practice.demo.models;

import lombok.Getter;

@Getter
public class CountryOccurrences implements Comparable<CountryOccurrences> {
    private String name;
    private int count;

    public CountryOccurrences(String name, int count) {
        this.name = name;
        this.count = count;
    }

    @Override
    public int compareTo(CountryOccurrences o) {
        return Integer.compare(o.getCount(), this.getCount());
    }
}
