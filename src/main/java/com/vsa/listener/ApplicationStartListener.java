package com.vsa.listener;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vsa.dao.service.OwnerService;
import com.vsa.dao.service.VehicleService;
import com.vsa.rest.model.Owner;
import com.vsa.rest.model.Vehicle;

/**
 * Listener used to to listen the application start event to initialize the
 * database.
 * 
 * @author Hanumant
 *
 */
@Component
public class ApplicationStartListener {
	@Autowired
	private OwnerService ownerService;
	@Autowired
	private VehicleService vehicleService;
	/**
	 * Logger Instance
	 */
	private final Logger logger = LoggerFactory
			.getLogger(ApplicationStartListener.class);

	@EventListener
	public void init(final ApplicationReadyEvent event) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			ClassPathResource resource = new ClassPathResource("/data.json");
			List<Owner> owners = mapper.readValue(resource.getInputStream(),
					new TypeReference<List<Owner>>() {
					});
			for (Owner owner : owners) {
				List<Vehicle> vehicles = owner.getVehicles();
				ownerService.addOwner(owner);
				for (Vehicle vehicle : vehicles) {
					vehicle.setOwner(owner);
					vehicleService.addVehicle(vehicle);
				}
			}

		} catch (IOException e) {
			logger.error("Failed to initalize the database", e);
		}
	}
}
