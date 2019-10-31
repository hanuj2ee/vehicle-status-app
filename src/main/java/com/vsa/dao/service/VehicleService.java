package com.vsa.dao.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.vsa.rest.model.Filter;
import com.vsa.rest.model.Vehicle;

/**
 * Vehicle Service to get all vehicles and add vehicle
 * 
 * @author Hanumant
 *
 */
@Service
@Transactional
public interface VehicleService {

	/**
	 * Add Vehicle
	 * 
	 * @param vehicle - Instance of {@link Vehicle}
	 */
	public Vehicle addVehicle(Vehicle vehicle);

	/**
	 * Update Vehicle
	 * 
	 * @param vehicle - Instance of {@link Vehicle}
	 */
	public void updateVehicle(Vehicle vehicle);

	/**
	 * Get the vehicle by id
	 * 
	 * @param id - Vehicle Id
	 * @return Vehicle of given Id
	 */
	public Vehicle getVehicleById(String id);

	/**
	 * Get All Vehicles for given filter
	 * 
	 * @param filter - Vehicle status
	 * @return Pagination result
	 */
	List<Vehicle> getAllVehicles(Filter filter);
}
