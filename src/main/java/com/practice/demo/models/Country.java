package com.practice.demo.models;

import lombok.Getter;

@Getter
public class Country {
    private final Name name;

    public Country(Name name) {
        this.name = name;
    }

    @Getter
    class Name {
        private String common;
        private String official;

        public Name(String common, String official) {
            this.common = common;
            this.official = official;
        }
    }
}
