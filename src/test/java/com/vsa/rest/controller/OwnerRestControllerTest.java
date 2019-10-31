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

import com.vsa.dao.service.OwnerService;
import com.vsa.rest.model.Owner;

/**
 * Test case for REST API from OwnerRestController
 * 
 * @author Hanumant
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(OwnerRestController.class)
public class OwnerRestControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private OwnerService service;

	@Test
	public void testGetAllOwners() {
		Owner owner = new Owner();
		owner.setName("Johans Bulk AB");
		owner.setId(1l);
		owner.setAddress("Balkvägen 12, 222 22 Stockholm");
		List<Owner> owners = new ArrayList<>();
		owners.add(owner);
		given(service.getAllOwner()).willReturn(owners);
		try {
			mvc.perform(get("/owner/owners")
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.result", hasSize(1)))
					.andExpect(jsonPath("$.result[0].id", is(1)))
					.andExpect(
							jsonPath("$.result[0].name", is(owner.getName())))
					.andExpect(jsonPath("$.result[0].address",
							is(owner.getAddress())));
		} catch (Exception e) {
			fail("Failed to call the API");
		}
	}
}
