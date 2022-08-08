package com.ram.currencyexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ram.currencyexchangeservice.entity.CurrencyExchange;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Integer> {
	
	public CurrencyExchange findByFromAndTo(String from, String to);

}
