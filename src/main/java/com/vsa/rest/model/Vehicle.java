/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vsa.rest.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

/**
 * Rest layer class for vehicle
 * 
 * @author Hanumant
 */

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class Vehicle implements Serializable {
	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 3730889822270936553L;

	@NotNull(message = "Vehicle id is required")
	@Size(min = 1, max = 100, message = "Data is too large")
	private String vehicleId;
	@NotNull(message = "Vehicle registration number is required")
	@Size(min = 1, max = 45, message = "Data is too large")
	private String regNumber;
	@Size(max = 45)
	@NotNull(message = "Vehicle status is required")
	@Size(min = 1, max = 45, message = "Data is too large")
	private String status;
	@NotNull(message = "Vehicle owner is required")
	private Owner owner;
	@NotNull(message = "Image path is required")
	private String imagePath;

}
