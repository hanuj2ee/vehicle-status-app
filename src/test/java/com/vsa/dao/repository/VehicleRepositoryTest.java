package com.vsa.dao.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.vsa.dao.model.OwnerModel;
import com.vsa.dao.model.VehicleModel;

/**
 * Tets for Vehicle Repository
 * 
 * @author Hanumant
 *
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
public class VehicleRepositoryTest {
	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private VehicleRepository vehicleRepository;

	@Before
	public void setUp() throws Exception {
		OwnerModel ownerModel = new OwnerModel();
		ownerModel.setName("Johans Bulk AB");
		ownerModel.setId(1);
		ownerModel.setAddress("Balkvägen 12, 222 22 Stockholm");
		testEntityManager.persist(ownerModel);
		testEntityManager.flush();

		VehicleModel v1 = new VehicleModel();
		v1.setStatus("Connected");
		v1.setRegNumber("JKL012");
		v1.setOwner(ownerModel);
		v1.setVehicleId("YS2R4X20005388011");
		testEntityManager.persist(v1);
		testEntityManager.flush();

		VehicleModel v2 = new VehicleModel();
		v2.setStatus("Disconnected");
		v2.setRegNumber("MNO345");
		v2.setOwner(ownerModel);
		v2.setVehicleId("YS2R4X20005387949");
		testEntityManager.persist(v2);
		testEntityManager.flush();

	}

	@Test
	public void testFindByStatus() {

		List<VehicleModel> vModels = vehicleRepository
				.findByStatus("Disconnected".toLowerCase());
		assertNotNull("Vehicles list is null", vModels);
		assertTrue("Vehicles list is empty", !vModels.isEmpty());
		assertTrue("Size of the list is not matching", vModels.size() == 1);
		for (VehicleModel vehicleModel : vModels) {
			assertNotNull(vehicleModel);
			assertNotNull(vehicleModel.getOwner());
			assertNotNull(vehicleModel.getStatus());
			assertNotNull(vehicleModel.getOwner().getName());
			assertNotNull(vehicleModel.getOwner().getAddress());
			assertEquals("Johans Bulk AB", vehicleModel.getOwner().getName());
			assertEquals(1, vehicleModel.getOwner().getId());
			assertEquals("Balkvägen 12, 222 22 Stockholm",
					vehicleModel.getOwner().getAddress());
			assertEquals("Disconnected", vehicleModel.getStatus());
			assertEquals("YS2R4X20005387949", vehicleModel.getVehicleId());
			assertEquals("MNO345", vehicleModel.getRegNumber());
		}
	}

	@Test
	public void testFindByStatusAndOwner() {
		List<Long> owners = new ArrayList<Long>();
		owners.add(1l);
		List<VehicleModel> vModels = vehicleRepository
				.findAllByStatusAndOwners("Disconnected".toLowerCase(), owners);
		assertNotNull("Vehicles list is null", vModels);
		assertTrue("Vehicles list is empty", !vModels.isEmpty());
		assertTrue("Size of the list is not matching", vModels.size() == 1);
		for (VehicleModel vehicleModel : vModels) {
			assertNotNull(vehicleModel);
			assertNotNull(vehicleModel.getOwner());
			assertNotNull(vehicleModel.getStatus());
			assertNotNull(vehicleModel.getOwner().getName());
			assertNotNull(vehicleModel.getOwner().getAddress());
			assertEquals("Johans Bulk AB", vehicleModel.getOwner().getName());
			assertEquals(1, vehicleModel.getOwner().getId());
			assertEquals("Balkvägen 12, 222 22 Stockholm",
					vehicleModel.getOwner().getAddress());
			assertEquals("Disconnected", vehicleModel.getStatus());
			assertEquals("YS2R4X20005387949", vehicleModel.getVehicleId());
			assertEquals("MNO345", vehicleModel.getRegNumber());
		}
	}

	@Test
	public void testFindByAuthorName() {
		List<Long> authors = new ArrayList<Long>();
		authors.add(1l);
		List<VehicleModel> vModels = vehicleRepository.findAllByOwners(authors);
		assertNotNull("Vehicles list is null", vModels);
		assertTrue("Vehicles list is empty", !vModels.isEmpty());
		assertTrue("Size of the list is not matching", vModels.size() == 2);

		for (VehicleModel vehicleModel : vModels) {
			assertNotNull(vehicleModel);
			assertNotNull(vehicleModel.getOwner());
			assertNotNull(vehicleModel.getStatus());
			assertNotNull(vehicleModel.getOwner().getName());
			assertNotNull(vehicleModel.getOwner().getAddress());
		}
	}

	@Test
	public void testFindAll() {
		List<VehicleModel> vModels = vehicleRepository.findAll();
		assertNotNull("Vehicles list is null", vModels);
		assertTrue("Vehicles list is empty", !vModels.isEmpty());
		assertTrue("Size of the list is not matching", vModels.size() == 2);

		for (VehicleModel vehicleModel : vModels) {
			assertNotNull(vehicleModel);
			assertNotNull(vehicleModel.getOwner());
			assertNotNull(vehicleModel.getStatus());
			assertNotNull(vehicleModel.getOwner().getName());
			assertNotNull(vehicleModel.getOwner().getAddress());
		}
	}

	@Test
	public void testFindById() {
		VehicleModel vehicleModel = vehicleRepository
				.findById("YS2R4X20005387949").get();
		assertNotNull(vehicleModel);
		assertNotNull(vehicleModel.getOwner());
		assertNotNull(vehicleModel.getStatus());
		assertNotNull(vehicleModel.getOwner().getName());
		assertNotNull(vehicleModel.getOwner().getAddress());
		assertEquals("Johans Bulk AB", vehicleModel.getOwner().getName());
		assertEquals(1, vehicleModel.getOwner().getId());
		assertEquals("Balkvägen 12, 222 22 Stockholm",
				vehicleModel.getOwner().getAddress());
		assertEquals("Disconnected", vehicleModel.getStatus());
		assertEquals("YS2R4X20005387949", vehicleModel.getVehicleId());
		assertEquals("MNO345", vehicleModel.getRegNumber());

	}

	@Test(expected = NoSuchElementException.class)
	public void testFindByInvalid() {
		vehicleRepository.findById("YS2R4X2000538801").get();
	}

	@Test
	public void testFindByInvalidAuthorName() {
		List<Long> authors = new ArrayList<Long>();
		authors.add(10l);
		List<VehicleModel> vModels = vehicleRepository.findAllByOwners(authors);
		assertNotNull(vModels);
		assertTrue("Vehicles list is not empty", vModels.isEmpty());
	}

	@Test
	public void testFindByInvalidStatus() {
		List<VehicleModel> vModels = vehicleRepository.findByStatus("XYZ");
		assertNotNull(vModels);
		assertTrue("Vehicles list is not empty", vModels.isEmpty());
	}

}
