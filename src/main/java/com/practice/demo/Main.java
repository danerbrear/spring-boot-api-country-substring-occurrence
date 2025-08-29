package com.practice.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.demo.models.Country;
import com.practice.demo.services.CountryService;

@SpringBootApplication
@RestController
public class Main {

	@Autowired
	CountryService countryService;

	@GetMapping("/")
	public String index() {
		System.out.println("Helloooooo");
		return "Hello world!";
	}

	@GetMapping("/country-info")
	public ResponseEntity<Country[]> getCountryInfoList() {
		return countryService.getCountryList();
	}

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
