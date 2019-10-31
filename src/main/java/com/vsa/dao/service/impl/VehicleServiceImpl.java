
package com.vsa.dao.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsa.dao.model.OwnerModel;
import com.vsa.dao.model.VehicleModel;
import com.vsa.dao.repository.VehicleRepository;
import com.vsa.dao.service.OwnerService;
import com.vsa.dao.service.VehicleService;
import com.vsa.dao.service.exception.FindException;
import com.vsa.dao.service.exception.InsertException;
import com.vsa.dao.service.exception.InvalidRequestException;
import com.vsa.rest.model.Filter;
import com.vsa.rest.model.Owner;
import com.vsa.rest.model.Vehicle;

/**
 * The implementation of vehcile service
 * 
 * @author Haunamnt
 *
 */
@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private OwnerService ownerService;
	/**
	 * Logger Instance
	 */
	private final Logger logger = LoggerFactory.getLogger(VehicleService.class);

	@Override
	public List<Vehicle> getAllVehicles(Filter filter) {
		// Get vehicles by filter
		List<VehicleModel> pages = null;
		if (filter.getStatus().equalsIgnoreCase("all")) {
			List<Long> owners = filter.getOwners();
			if (null != owners && !owners.isEmpty()) {
				pages = vehicleRepository.findAllByOwners(owners);
			} else {
				pages = vehicleRepository.findAll();
			}
		} else {
			List<Long> owners = filter.getOwners();
			if (null != owners && !owners.isEmpty()) {
				pages = vehicleRepository.findAllByStatusAndOwners(
						filter.getStatus().toLowerCase(), owners);
			} else {
				pages = vehicleRepository
						.findByStatus(filter.getStatus().toLowerCase());
			}
		}
		// Convert dao model to rest model
		List<Vehicle> vehicles = new ArrayList<>();
		if (null != pages) {
			final Iterator<VehicleModel> iterator = pages.iterator();
			while (iterator.hasNext()) {
				VehicleModel vehicleModel = iterator.next();
				Vehicle vehicle = new Vehicle();
				vehicle.setOwner(new Owner());
				vehicleModel.getOwner().setVehicles(null);
				BeanUtils.copyProperties(vehicleModel, vehicle);
				BeanUtils.copyProperties(vehicleModel.getOwner(),
						vehicle.getOwner());
				vehicles.add(vehicle);
			}
		}
		return vehicles;
	}

	@Override
	public Vehicle addVehicle(Vehicle vehicle) {
		if (null == vehicle)
			throw new InvalidRequestException("Vehicle instance is null");

		if (null == vehicle.getOwner())
			throw new InvalidRequestException("Vehicle owner instance is null");
		Owner owner = null;
		try {
			owner = ownerService.getOwner(vehicle.getOwner().getId());
		} catch (Exception e1) {
			throw new InvalidRequestException("Vehicle owner does not exist");
		}

		try {
			VehicleModel vehicleModel = new VehicleModel();
			vehicleModel.setOwner(new OwnerModel());
			BeanUtils.copyProperties(vehicle, vehicleModel);
			BeanUtils.copyProperties(owner, vehicleModel.getOwner());
			vehicleRepository.save(vehicleModel);
			logger.debug("Vehicle details saved successfully");
		} catch (Exception e) {
			logger.error("Failed to save owner", e);
			throw new InsertException("Failed to insert the owner");
		}
		return vehicle;
	}

	@Override
	public void updateVehicle(Vehicle vehicle) {

		if (null == vehicle)
			throw new InvalidRequestException("Vehicle instance is null");

		if (null == vehicle.getOwner())
			throw new InvalidRequestException("Vehicle owner instance is null");
		Owner owner = null;
		try {
			owner = ownerService.getOwner(vehicle.getOwner().getId());
		} catch (Exception e1) {
			throw new InvalidRequestException("Vehicle owner does not exist");
		}
		VehicleModel vehicleModel = null;
		final Optional<VehicleModel> optional = vehicleRepository
				.findById(vehicle.getVehicleId());
		if (!optional.isPresent())
			throw new FindException("Vehcicle does not exist");
		vehicleModel = optional.get();
		try {
			vehicleModel.setOwner(new OwnerModel());
			BeanUtils.copyProperties(vehicle, vehicleModel);
			BeanUtils.copyProperties(owner, vehicleModel.getOwner());
			vehicleRepository.save(vehicleModel);
			logger.debug("Vehicle details saved successfully");
		} catch (Exception e) {
			logger.error("Failed to save owner", e);
			throw new InsertException("Failed to insert the owner");
		}
	}

	@Override
	public Vehicle getVehicleById(String id) {
		final Optional<VehicleModel> optional = vehicleRepository.findById(id);
		if (!optional.isPresent()) {
			logger.error("Vehile does not exist for given id");
			throw new FindException("Vehile does not exist for given id");

		}
		VehicleModel vehicleModel = optional.get();
		Vehicle vehicle = new Vehicle();
		vehicle.setOwner(new Owner());
		vehicleModel.getOwner().setVehicles(null);
		BeanUtils.copyProperties(vehicleModel, vehicle);
		BeanUtils.copyProperties(vehicleModel.getOwner(), vehicle.getOwner());
		return vehicle;
	}

}
