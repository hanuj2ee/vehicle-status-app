package com.vsa.dao.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.vsa.dao.model.OwnerModel;
import com.vsa.dao.model.VehicleModel;
import com.vsa.dao.repository.OwnerRepository;
import com.vsa.dao.repository.VehicleRepository;
import com.vsa.dao.service.impl.OwnerServiceImpl;
import com.vsa.dao.service.impl.VehicleServiceImpl;
import com.vsa.rest.model.Filter;
import com.vsa.rest.model.Owner;
import com.vsa.rest.model.Vehicle;

/**
 * Vehicle service test cases
 * 
 * @author Hanumant
 *
 */
@RunWith(SpringRunner.class)
public class VehicleServiceTest {
	private VehicleModel v1;

	@TestConfiguration
	static class VehicleServiceImplTestContextConfiguration {

		@Bean
		public VehicleService getVehicleService() {
			return new VehicleServiceImpl();
		}
	}

	@TestConfiguration
	static class OwnerServiceImplTestContextConfiguration {

		@Bean
		public OwnerService getOwnerService() {
			return new OwnerServiceImpl();
		}
	}

	@Autowired
	private VehicleService vehicleService;

	@MockBean
	private VehicleRepository vehicleRepository;

	@MockBean
	private OwnerRepository ownerRepository;

	@Before
	public void setUp() throws Exception {
		OwnerModel owner = new OwnerModel();
		owner.setName("Johans Bulk AB");
		owner.setId(1);
		owner.setAddress("Balkvägen 12, 222 22 Stockholm");
		v1 = new VehicleModel();
		v1.setStatus("Connected");
		v1.setRegNumber("JKL012");
		v1.setOwner(owner);
		v1.setVehicleId("YS2R4X20005388011");
		List<VehicleModel> vehicles = new ArrayList<>();
		vehicles.add(v1);
		Mockito.when(ownerRepository.findById(1l))
				.thenReturn(Optional.of(owner));
		Mockito.when(vehicleRepository.findAll()).thenReturn(vehicles);
		List<VehicleModel> vehicles1 = new ArrayList<>();
		vehicles1.add(v1);

		Mockito.when(vehicleRepository.findByStatus("Connected".toLowerCase()))
				.thenReturn(vehicles1);
		List<Long> owners = new ArrayList<Long>();
		owners.add(1l);
		Mockito.when(vehicleRepository.findAllByOwners(owners))
				.thenReturn(vehicles1);
		Mockito.when(vehicleRepository
				.findAllByStatusAndOwners("Connected".toLowerCase(), owners))
				.thenReturn(vehicles1);

		Mockito.when(vehicleRepository.save(v1)).thenReturn(v1);
	}

	@Test
	public void testAddVehicle() {
		Owner owner = new Owner();
		owner.setName("Johans Bulk AB");
		owner.setId(1);
		owner.setAddress("Balkvägen 12, 222 22 Stockholm");
		Vehicle v1 = new Vehicle();
		v1.setStatus("Connected");
		v1.setRegNumber("JKL012");
		v1.setOwner(owner);
		v1.setVehicleId("YS2R4X20005388011");

		Vehicle actual = vehicleService.addVehicle(v1);
		assertNotNull(actual);
		assertNotNull(actual.getOwner());
		assertNotNull(actual.getStatus());
		assertNotNull(actual.getOwner().getName());
		assertNotNull(actual.getOwner().getAddress());
		assertEquals("Johans Bulk AB", actual.getOwner().getName());
		assertEquals(1, actual.getOwner().getId());
		assertEquals("Balkvägen 12, 222 22 Stockholm",
				actual.getOwner().getAddress());
		assertEquals("Connected", actual.getStatus());
		assertEquals("YS2R4X20005388011", actual.getVehicleId());
		assertEquals("JKL012", actual.getRegNumber());

	}

	@Test
	public void testGetAllVehicle() {
		Owner owner = new Owner();
		owner.setName("Johans Bulk AB");
		owner.setId(1);
		owner.setAddress("Balkvägen 12, 222 22 Stockholm");
		Vehicle v1 = new Vehicle();
		v1.setStatus("Connected");
		v1.setRegNumber("JKL012");
		v1.setOwner(owner);
		v1.setVehicleId("YS2R4X20005388011");
		List<Long> owners = new ArrayList<Long>();
		Filter f1 = new Filter();
		f1.setStatus("all");
		f1.setOwners(owners);
		List<Vehicle> vehicles = vehicleService.getAllVehicles(f1);
		assertNotNull(vehicles);
		assertTrue(!vehicles.isEmpty());
		for (Vehicle vehicle : vehicles) {
			assertNotNull(vehicle.getOwner());
			assertNotNull(vehicle.getStatus());
			assertNotNull(vehicle.getOwner().getName());
			assertNotNull(vehicle.getOwner().getAddress());
			assertEquals("Johans Bulk AB", vehicle.getOwner().getName());
			assertEquals(1, vehicle.getOwner().getId());
			assertEquals("Balkvägen 12, 222 22 Stockholm",
					vehicle.getOwner().getAddress());
			assertEquals("Connected", vehicle.getStatus());
			assertEquals("YS2R4X20005388011", vehicle.getVehicleId());
			assertEquals("JKL012", vehicle.getRegNumber());
		}

	}

	@Test
	public void testGetVehicleByStatus() {
		List<Long> owners = new ArrayList<Long>();
		Filter f1 = new Filter();
		f1.setStatus("Connected");
		f1.setOwners(owners);
		List<Vehicle> vehicles = vehicleService.getAllVehicles(f1);
		assertNotNull(vehicles);
		assertTrue(!vehicles.isEmpty());
		for (Vehicle vehicle : vehicles) {
			assertNotNull(vehicle.getOwner());
			assertNotNull(vehicle.getStatus());
			assertNotNull(vehicle.getOwner().getName());
			assertNotNull(vehicle.getOwner().getAddress());
			assertEquals("Johans Bulk AB", vehicle.getOwner().getName());
			assertEquals(1, vehicle.getOwner().getId());
			assertEquals("Balkvägen 12, 222 22 Stockholm",
					vehicle.getOwner().getAddress());
			assertEquals("Connected", vehicle.getStatus());
			assertEquals("YS2R4X20005388011", vehicle.getVehicleId());
			assertEquals("JKL012", vehicle.getRegNumber());
		}

	}

	@Test
	public void testGetVehicleByOwner() {
		List<Long> owners = new ArrayList<Long>();
		Filter f1 = new Filter();
		owners.add(1L);
		f1.setStatus("all");
		f1.setOwners(owners);
		List<Vehicle> vehicles = vehicleService.getAllVehicles(f1);
		assertNotNull(vehicles);
		assertTrue(!vehicles.isEmpty());
		for (Vehicle vehicle : vehicles) {
			assertNotNull(vehicle.getOwner());
			assertNotNull(vehicle.getStatus());
			assertNotNull(vehicle.getOwner().getName());
			assertNotNull(vehicle.getOwner().getAddress());
			assertEquals("Johans Bulk AB", vehicle.getOwner().getName());
			assertEquals(1, vehicle.getOwner().getId());
			assertEquals("Balkvägen 12, 222 22 Stockholm",
					vehicle.getOwner().getAddress());
			assertEquals("Connected", vehicle.getStatus());
			assertEquals("YS2R4X20005388011", vehicle.getVehicleId());
			assertEquals("JKL012", vehicle.getRegNumber());
		}

	}

	@Test
	public void testGetVehicleByStatusAndOwner() {
		List<Long> owners = new ArrayList<Long>();
		owners.add(1l);
		Filter f1 = new Filter();
		f1.setStatus("Connected");
		f1.setOwners(owners);
		List<Vehicle> vehicles = vehicleService.getAllVehicles(f1);
		assertNotNull(vehicles);
		assertTrue(!vehicles.isEmpty());
		for (Vehicle vehicle : vehicles) {
			assertNotNull(vehicle.getOwner());
			assertNotNull(vehicle.getStatus());
			assertNotNull(vehicle.getOwner().getName());
			assertNotNull(vehicle.getOwner().getAddress());
			assertEquals("Johans Bulk AB", vehicle.getOwner().getName());
			assertEquals(1, vehicle.getOwner().getId());
			assertEquals("Balkvägen 12, 222 22 Stockholm",
					vehicle.getOwner().getAddress());
			assertEquals("Connected", vehicle.getStatus());
			assertEquals("YS2R4X20005388011", vehicle.getVehicleId());
			assertEquals("JKL012", vehicle.getRegNumber());
		}

	}

}
