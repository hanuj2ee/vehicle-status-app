
package com.vsa.dao.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vsa.dao.model.OwnerModel;
import com.vsa.dao.repository.OwnerRepository;
import com.vsa.dao.service.OwnerService;
import com.vsa.dao.service.exception.FindException;
import com.vsa.dao.service.exception.InsertException;
import com.vsa.dao.service.exception.InvalidRequestException;
import com.vsa.rest.model.Owner;

/**
 * The implementation of owner service
 * 
 * @author Hanumant
 *
 */
@Service
@Transactional
public class OwnerServiceImpl implements OwnerService {
	@Autowired
	private OwnerRepository ownerRepository;
	/**
	 * Logger Instance
	 */
	private final Logger logger = LoggerFactory.getLogger(OwnerService.class);

	@Override
	public Owner addOwner(Owner owner) {
		if (null == owner)
			throw new InvalidRequestException("Owner instance is null");
		try {
			owner.setVehicles(null);
			OwnerModel model = new OwnerModel();

			BeanUtils.copyProperties(owner, model);
			ownerRepository.save(model);
			logger.debug("Owner details saved successfully");
		} catch (Exception e) {
			logger.debug("Failed to insert the owner", e);
			throw new InsertException("Failed to insert the owner");
		}
		return owner;
	}

	@Override
	public Owner getOwner(long id) {
		try {
			// if Owner doesnot exist then throw error
			final Optional<OwnerModel> optional = ownerRepository.findById(id);
			if (!optional.isPresent())
				throw new FindException("Owner does not exist");

			// Return owner
			OwnerModel ownerModel = optional.get();
			Owner model = new Owner();
			BeanUtils.copyProperties(ownerModel, model);
			logger.debug("Owner details saved successfully");
			return model;
		} catch (Exception e) {
			throw new FindException("Failed to get the owner");
		}
	}

	@Override
	public List<Owner> getAllOwner() {
		List<OwnerModel> ownerModels = ownerRepository.findAll();
		List<Owner> owners = new ArrayList<>();
		// Get all owners
		for (OwnerModel ownerModel : ownerModels) {
			Owner model = new Owner();
			BeanUtils.copyProperties(ownerModel, model);
			model.setVehicles(null);
			owners.add(model);
		}
		return owners;
	}

}
