package com.vsa.rest.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.vsa.dao.service.VehicleService;
import com.vsa.rest.model.Filter;
import com.vsa.rest.model.Owner;
import com.vsa.rest.model.Vehicle;

/**
 * Test case for REST API from OwnerRestController
 * 
 * @author Hanumant
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(VehicleRestController.class)
public class VehicleRestControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private VehicleService service;
	@MockBean
	private Filter filter;

	@Test
	public void testGetVehicles() {
		Owner owner = new Owner();
		owner.setName("Johans Bulk AB");
		owner.setId(1);
		owner.setAddress("Balkvägen 12, 222 22 Stockholm");
		Vehicle v1 = new Vehicle();
		v1.setStatus("Connected");
		v1.setRegNumber("JKL012");
		v1.setOwner(owner);
		v1.setVehicleId("YS2R4X20005388011");
		List<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(v1);
		filter.setStatus("all");
		given(service.getAllVehicles(filter)).willReturn(vehicles);
		try {
			final ResultActions action = mvc
					.perform(get("/vehicle/vehicles?status=all&owners=")
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
			action.andExpect(jsonPath("$.result", hasSize(1)))
					.andExpect(jsonPath("$.result[0].owner.id", is(1)))
					.andExpect(jsonPath("$.result[0].owner.name",
							is(owner.getName())))
					.andExpect(jsonPath("$.result[0].owner.address",
							is(owner.getAddress())));
		} catch (Exception e) {
			fail("Failed to call the API");
		}
	}

	@Test
	public void testGetVehiclesByStatus() {
		Owner owner = new Owner();
		owner.setName("Johans Bulk AB");
		owner.setId(1);
		owner.setAddress("Balkvägen 12, 222 22 Stockholm");
		Vehicle v1 = new Vehicle();
		v1.setStatus("Connected");
		v1.setRegNumber("JKL012");
		v1.setOwner(owner);
		v1.setVehicleId("YS2R4X20005388011");
		List<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(v1);
		filter.setStatus("Connected");
		given(service.getAllVehicles(filter)).willReturn(vehicles);
		try {
			final ResultActions action = mvc
					.perform(get("/vehicle/vehicles?status=all&owners=")
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
			action.andExpect(jsonPath("$.result", hasSize(1)))
					.andExpect(jsonPath("$.result[0].owner.id", is(1)))
					.andExpect(jsonPath("$.result[0].owner.name",
							is(owner.getName())))
					.andExpect(jsonPath("$.result[0].owner.address",
							is(owner.getAddress())));
		} catch (Exception e) {
			fail("Failed to call the API");
		}
	}

	@Test
	public void testGetVehiclesByOwner() {
		Owner owner = new Owner();
		owner.setName("Johans Bulk AB");
		owner.setId(1);
		owner.setAddress("Balkvägen 12, 222 22 Stockholm");
		Vehicle v1 = new Vehicle();
		v1.setStatus("Connected");
		v1.setRegNumber("JKL012");
		v1.setOwner(owner);
		v1.setVehicleId("YS2R4X20005388011");
		List<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(v1);
		List<Long> owners = new ArrayList<Long>();
		owners.add(1l);
		filter.setOwners(owners);
		filter.setStatus("all");
		given(service.getAllVehicles(filter)).willReturn(vehicles);
		try {
			final ResultActions action = mvc
					.perform(get("/vehicle/vehicles?status=all&owners=")
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
			action.andExpect(jsonPath("$.result", hasSize(1)))
					.andExpect(jsonPath("$.result[0].owner.id", is(1)))
					.andExpect(jsonPath("$.result[0].owner.name",
							is(owner.getName())))
					.andExpect(jsonPath("$.result[0].owner.address",
							is(owner.getAddress())));
		} catch (Exception e) {
			fail("Failed to call the API");
		}
	}

	@Test
	public void testGetVehiclesByStatusAndOwner() {
		Owner owner = new Owner();
		owner.setName("Johans Bulk AB");
		owner.setId(1);
		owner.setAddress("Balkvägen 12, 222 22 Stockholm");
		Vehicle v1 = new Vehicle();
		v1.setStatus("Connected");
		v1.setRegNumber("JKL012");
		v1.setOwner(owner);
		v1.setVehicleId("YS2R4X20005388011");
		List<Vehicle> vehicles = new ArrayList<>();
		vehicles.add(v1);
		List<Long> owners = new ArrayList<Long>();
		owners.add(1l);
		filter.setOwners(owners);
		filter.setStatus("Connected");
		given(service.getAllVehicles(filter)).willReturn(vehicles);
		try {
			final ResultActions action = mvc
					.perform(get("/vehicle/vehicles?status=all&owners=")
							.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
			action.andExpect(jsonPath("$.result", hasSize(1)))
					.andExpect(jsonPath("$.result[0].owner.id", is(1)))
					.andExpect(jsonPath("$.result[0].owner.name",
							is(owner.getName())))
					.andExpect(jsonPath("$.result[0].owner.address",
							is(owner.getAddress())));
		} catch (Exception e) {
			fail("Failed to call the API");
		}
	}

}
