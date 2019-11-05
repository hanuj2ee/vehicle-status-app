package com.vsa.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vsa.dao.model.OwnerModel;

/**
 * Repository for Owner
 * 
 * @author Hanumant
 *
 */
@Repository
public interface OwnerRepository extends JpaRepository<OwnerModel, Long> {

}
