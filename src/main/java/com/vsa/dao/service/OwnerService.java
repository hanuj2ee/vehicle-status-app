package com.vsa.dao.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.vsa.rest.model.Owner;

/**
 * Interface defines functionality for ower
 * 
 * @author Hanumant
 *
 */
@Service
@Transactional
public interface OwnerService {
	/**
	 * Add owner
	 * 
	 * @param owner - Instance of Owner
	 */
	public Owner addOwner(Owner owner);

	/**
	 * Get the owner by id
	 * 
	 * @param id - Owner id
	 * @return Instance of {@link owner}
	 */
	Owner getOwner(long id);

	/**
	 * Get all owner
	 * 
	 * @return
	 */
	List<Owner> getAllOwner();

}
