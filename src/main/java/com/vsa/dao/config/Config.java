package com.vsa.dao.config;

import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.vsa.rest.model.Filter;

/**
 * The configuration class to create bean
 * 
 * @author Hanumant
 *
 */
@Component
public class Config {
	@Bean(name = "filter", autowireCandidate = true)
	public Filter getFilter() {
		return new Filter();
	}

	@Bean(name = "random", autowireCandidate = true)
	public Random getRandom() {
		return new Random(2);
	}
}
