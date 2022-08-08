package com.ram.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ram.currencyconversionservice.entity.CurrencyConversion;
import com.ram.currencyconversionservice.proxy.CurrencyExchangeProxy;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CurrencyConvertionController {
	
	@Autowired
	private CurrencyExchangeProxy proxy;
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion retieveCurrencyConversion(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		ResponseEntity<CurrencyConversion> forEntity = new RestTemplate()
		.getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", 
				CurrencyConversion.class,
				uriVariables);
		
		CurrencyConversion currencyConversion = forEntity.getBody();
		return new CurrencyConversion(
				currencyConversion.getId(),
				from,
				to,
				quantity,
				currencyConversion.getConversionMultiple(),
				quantity.multiply(currencyConversion.getConversionMultiple()),
				currencyConversion.getEnvironment());
	}
	
	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion retieveCurrencyConversionFeign(
			@PathVariable String from,
			@PathVariable String to,
			@PathVariable BigDecimal quantity) {
		
		CurrencyConversion currencyConversion = proxy.retieveCurrencyExchangeValue(from, to);
		return new CurrencyConversion(
				currencyConversion.getId(),
				from,
				to,
				quantity,
				currencyConversion.getConversionMultiple(),
				quantity.multiply(currencyConversion.getConversionMultiple()),
				currencyConversion.getEnvironment() + " - Feign");
	}
	
	
	@GetMapping("/currency-conversion/sample-api")
	//@Retry(name = "red-bus-api")
	@CircuitBreaker(name = "default", fallbackMethod = "fallback")
	public String sampleApiForResiliency() {
		ResponseEntity<CurrencyConversion> forEntity = new RestTemplate().getForEntity("http://localhost:9952/test", CurrencyConversion.class);
		return "Sample Api Results";
	}
	
	public String fallback( Exception e) {
		return "Fall back";
	}

}
