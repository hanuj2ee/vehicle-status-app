package com.vsa.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vsa.dao.service.VehicleService;
import com.vsa.rest.model.Filter;
import com.vsa.rest.model.Response;
import com.vsa.rest.model.Vehicle;
import com.vsa.rest.util.Constants;

/**
 * Rest Controller for
 * 
 * @author Hanumant
 *
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600, methods = {
		RequestMethod.GET })
@RequestMapping("/vehicle")
public class VehicleRestController {
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private Filter filter = new Filter();

	@GetMapping("/vehicles")
	public ResponseEntity<Response> getVehicles(
			@RequestParam(name = "status", required = false, defaultValue = "all") final String status,
			@RequestParam(name = "owners", required = false) final List<Long> owners) {
		filter.setOwners(owners);
		filter.setStatus(status);
		List<Vehicle> vehicles = vehicleService.getAllVehicles(filter);
		Response response = new Response();
		response.setResult(vehicles);
		response.setType(Constants.SUCCESS);
		return ResponseEntity.ok(response);
	}
}
