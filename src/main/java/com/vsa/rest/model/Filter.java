package com.vsa.rest.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * Filter class to filter data
 * 
 * @author Hanumant
 *
 */
public class Filter {
	private String status;
	private List<Long> owners = new ArrayList<Long>();
}
