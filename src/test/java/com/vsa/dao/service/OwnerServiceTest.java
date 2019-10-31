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
import com.vsa.dao.repository.OwnerRepository;
import com.vsa.dao.service.impl.OwnerServiceImpl;
import com.vsa.rest.model.Owner;

/**
 * Owner service test case
 * 
 * @author Hanumant
 *
 */
@RunWith(SpringRunner.class)
public class OwnerServiceTest {
	@TestConfiguration
	static class OwnerServiceImplTestContextConfiguration {

		@Bean
		public OwnerService getOwnerService() {
			return new OwnerServiceImpl();
		}
	}

	@Autowired
	private OwnerService service;

	@MockBean
	private OwnerRepository ownerRepository;

	@Before
	public void setUp() throws Exception {
		OwnerModel owner = new OwnerModel();
		owner.setName("Johans Bulk AB");
		owner.setId(1);
		owner.setAddress("Balkvägen 12, 222 22 Stockholm");
		Mockito.when(ownerRepository.findById(1l))
				.thenReturn(Optional.of(owner));
		List<OwnerModel> owners = new ArrayList<>();
		owners.add(owner);
		Mockito.when(ownerRepository.findAll()).thenReturn(owners);
		Mockito.when(ownerRepository.save(owner)).thenReturn(owner);
	}

	@Test
	public void testAddOwner() {
		Owner expected = new Owner();
		expected.setName("Johans Bulk AB");
		expected.setId(1);
		expected.setAddress("Balkvägen 12, 222 22 Stockholm");
		Owner owner = service.addOwner(expected);
		assertNotNull(owner);
		assertNotNull(owner.getName());
		assertNotNull(owner.getAddress());
		assertEquals("Johans Bulk AB", owner.getName());
		assertEquals(1, owner.getId());
		assertEquals("Balkvägen 12, 222 22 Stockholm", owner.getAddress());
	}

	@Test
	public void testGetOwner() {
		Owner owner = service.getOwner(1l);
		assertNotNull(owner);
		assertNotNull(owner.getName());
		assertNotNull(owner.getAddress());
		assertEquals("Johans Bulk AB", owner.getName());
		assertEquals(1, owner.getId());
		assertEquals("Balkvägen 12, 222 22 Stockholm", owner.getAddress());
	}

	@Test
	public void testGetAllOwner() {
		List<Owner> owners = service.getAllOwner();
		assertNotNull(owners);
		assertTrue(!owners.isEmpty());
		assertTrue(owners.size() == 1);
		for (Owner owner : owners) {
			assertNotNull(owner.getName());
			assertNotNull(owner.getAddress());
			assertEquals("Johans Bulk AB", owner.getName());
			assertEquals(1, owner.getId());
			assertEquals("Balkvägen 12, 222 22 Stockholm", owner.getAddress());
		}
	}
}
