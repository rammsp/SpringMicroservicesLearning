package com.ram.currencyconversionservice.entity;

import java.math.BigDecimal;

public class CurrencyConversion {
	
	private int id;
	
	private String from;
	
	private String to;
	
	private BigDecimal quantity;
	
	private BigDecimal conversionMultiple;
	
	private BigDecimal totalCalulatedAmount;
	
	private String environment;

	public CurrencyConversion() {
	}

	public CurrencyConversion(int id, String from, String to, BigDecimal quantity, BigDecimal conversionMultiple,
			BigDecimal totalCalulatedAmount, String environment) {
		this.id = id;
		this.from = from;
		this.to = to;
		this.quantity = quantity;
		this.conversionMultiple = conversionMultiple;
		this.totalCalulatedAmount = totalCalulatedAmount;
		this.environment = environment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}

	public void setConversionMultiple(BigDecimal conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}

	public BigDecimal getTotalCalulatedAmount() {
		return totalCalulatedAmount;
	}

	public void setTotalCalulatedAmount(BigDecimal totalCalulatedAmount) {
		this.totalCalulatedAmount = totalCalulatedAmount;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

}
