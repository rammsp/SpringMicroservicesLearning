package com.ram.currencyexchangeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ram.currencyexchangeservice.entity.CurrencyExchange;
import com.ram.currencyexchangeservice.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	CurrencyExchangeRepository repository;
	
	@Autowired
	Environment environment;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		CurrencyExchange currencyExchange =  repository.findByFromAndTo(from.toUpperCase(), to.toUpperCase());
		String env = "Port: " + environment.getProperty("server.port");
		currencyExchange.setEnvironment(env);
		if(currencyExchange == null)
			throw new RuntimeException("Currency Value not found!!");
		return currencyExchange;
	}
	

}
