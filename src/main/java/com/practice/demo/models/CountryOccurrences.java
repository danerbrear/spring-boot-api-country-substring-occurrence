package com.practice.demo.models;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;

        CountryOccurrences myObject = (CountryOccurrences) o;
        return count == myObject.count &&
                Objects.equals(name, myObject.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, count);
    }
}
