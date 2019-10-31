package com.vsa.dao.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.vsa.dao.model.OwnerModel;

/**
 * Test the OwnerRepository
 * 
 * @author Hanumant
 *
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
public class OwnerRepositoryTest {
	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private OwnerRepository ownerRepository;

	@Before
	public void setUp() throws Exception {
		OwnerModel ownerModel = new OwnerModel();
		ownerModel.setName("Johans Bulk AB");
		ownerModel.setId(1);
		ownerModel.setAddress("Balkvägen 12, 222 22 Stockholm");
		testEntityManager.persist(ownerModel);
		testEntityManager.flush();
	}

	@Test
	public void testFindAllOwner() {
		List<OwnerModel> ownerModels = ownerRepository.findAll();
		assertNotNull("List of all owners is null", ownerModels);
		assertTrue("List of all owners is empty", !ownerModels.isEmpty());
		assertTrue("List size is not matching", ownerModels.size() == 1);
		assertNotNull(ownerModels.get(0).getName());
		assertNotNull(ownerModels.get(0).getAddress());
		assertEquals("Johans Bulk AB", ownerModels.get(0).getName());
		assertEquals(1, ownerModels.get(0).getId());
		assertEquals("Balkvägen 12, 222 22 Stockholm",
				ownerModels.get(0).getAddress());
	}
}
