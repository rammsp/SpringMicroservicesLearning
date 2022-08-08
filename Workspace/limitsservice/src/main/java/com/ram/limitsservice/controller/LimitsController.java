package com.ram.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ram.limitsservice.bean.Limits;
import com.ram.limitsservice.configuration.LimitsConfiguration;

@RestController
public class LimitsController {
	
	@Autowired
	LimitsConfiguration limitsConfiguration;
	
	@GetMapping("/limits")
	public Limits retrieveLimits() {
		return new Limits(limitsConfiguration.getMinimum(), limitsConfiguration.getMaximum());
	}

}
