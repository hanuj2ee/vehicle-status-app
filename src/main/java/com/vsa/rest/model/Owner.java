package com.vsa.rest.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * Rest layer class for Owner
 * 
 * @author Hanumant
 */

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class Owner implements Serializable {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = -5384369816694351134L;

	private long id;
	@Size(min = 1, message = "Data is too large", max = 100)
	@NotNull(message = "Owner name required")
	private String name;
	@Size(min = 1, message = "Data is too large", max = 100)
	@NotNull(message = "Owner address required")
	private String address;
	private List<Vehicle> vehicles;
}
