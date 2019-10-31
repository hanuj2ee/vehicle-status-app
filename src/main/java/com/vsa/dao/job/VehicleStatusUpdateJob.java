package com.vsa.dao.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.vsa.dao.service.VehicleService;
import com.vsa.rest.model.Filter;
import com.vsa.rest.model.Vehicle;

@Configuration
@EnableScheduling
/**
 * The schedular job to update the vehicle status randomely
 * 
 * @author Hanumant
 *
 */
public class VehicleStatusUpdateJob {
	/**
	 * Logger Instance
	 */
	@Autowired
	private VehicleService vehicleService;
	private Random random = new Random(2);
	private final Logger logger = LoggerFactory.getLogger(VehicleService.class);

	@Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}")
	public void schedule() {
		Filter filter = new Filter();
		filter.setStatus("all");
		filter.setOwners(new ArrayList<Long>());
		List<Vehicle> vehicles = vehicleService.getAllVehicles(filter);
		for (Vehicle vehicle : vehicles) {
			int n = random.nextInt(2);
			if (n == 0) {
				vehicle.setStatus("Connected");
			} else {
				vehicle.setStatus("Disconnected");
			}
			vehicleService.updateVehicle(vehicle);
			logger.info("Vehicle [" + vehicle.getRegNumber()
					+ "] status is updated to " + vehicle.getStatus());
		}
		logger.info("------------------------------------------------------");
	}
}
