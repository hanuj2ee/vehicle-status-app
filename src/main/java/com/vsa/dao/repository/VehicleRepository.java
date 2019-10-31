package com.vsa.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vsa.dao.model.VehicleModel;

/**
 * Repository for Vehicle
 * 
 * @author Hanumant
 *
 */
@Repository
public interface VehicleRepository extends JpaRepository<VehicleModel, String> {
	@Query("From Vehicle as v where lower(v.status) =:status")
	List<VehicleModel> findByStatus(@Param("status") String status);

	@Query("From Vehicle as v where v.owner.id in :owners")
	List<VehicleModel> findAllByOwners(List<Long> owners);

	@Query("From Vehicle as v where lower(v.status) =:status and v.owner.id in :owners")
	List<VehicleModel> findAllByStatusAndOwners(@Param("status") String status,
			@Param("owners") List<Long> owners);
}
