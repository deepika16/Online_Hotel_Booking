package com.project.hotelbookingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.hotelbookingsystem.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
	
	
	
	@Query(value = "SELECT * FROM location WHERE location_first_name = ?1", nativeQuery = true)
	public List<Location> serchUserByName(String location_name);
	
	// Example of Native Query - SQL
	@Query(value = "SELECT * FROM location, state WHERE state_id = location_state", nativeQuery = true)
	public List<Location> serchUserByState(String location_state);
	

}
