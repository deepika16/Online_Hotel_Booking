package com.project.hotelbookingsystem.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hotelbookingsystem.exception.ResourceNotFoundException;
import com.project.hotelbookingsystem.model.Center;
import com.project.hotelbookingsystem.model.Company;
import com.project.hotelbookingsystem.model.Country;
import com.project.hotelbookingsystem.model.Location;
import com.project.hotelbookingsystem.model.Sell;
import com.project.hotelbookingsystem.model.State;
import com.project.hotelbookingsystem.repository.SellRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class SellController {
	@Autowired
	private SellRepository sellRepository;
	
	@PersistenceContext
    private EntityManager entityManager;

	@GetMapping("/sells")
	public List<Sell> getAllEmployees() {
		return sellRepository.findAll();
	}

	@GetMapping("/sells/search/{name}")
	public List<Sell> getEmployeeByName(@PathVariable(value = "name") String sellName) {
			return sellRepository.serchUserByName(sellName);
	}
	
	@GetMapping("/sells/search-state/{state}")
	public List<Sell> serchUserByState(@PathVariable(value = "state") String sellState) {
			return sellRepository.serchUserByState(sellState);
	}
	
	@GetMapping("/sells/all-sells/{id}")
	public ArrayList getAllEmployeeFields(@PathVariable(value = "id") Long orderId) {
		 Query q = entityManager.createQuery("SELECT prod, sel from center prod, sell sel WHERE sell_center_id = center_id AND sell_order_id = ?1");
		 List<Object[]> sell = q.setParameter(1, orderId).getResultList();
		 ArrayList<HashMap<String, String>> resultArray = new ArrayList();
		 
		 for ( Object[] row : sell ) {
			  Sell sell_details = (Sell)row[ 1 ];
			  Center center_details = (Center)row[ 0 ];
			  
			  HashMap<String, String> results = new HashMap();
			    results.put("sell_id",String.valueOf(sell_details.getSell_id()));
			    results.put("sell_order_id",sell_details.getSell_order_id());
			    results.put("sell_center_id",sell_details.getSell_center_id());
			    results.put("sell_units",sell_details.getSell_units());
			    results.put("sell_price_per_unit",sell_details.getSell_price_per_unit());
			    results.put("sell_total_cost",sell_details.getSell_total_cost());
			    results.put("center_id",String.valueOf(center_details.getCenter_id()));
				results.put("center_company_id",center_details.getCenter_company_id());
				results.put("center_description",center_details.getCenter_description());
				results.put("center_name",center_details.getCenter_name());
				results.put("center_image",center_details.getCenter_image_filename());
				results.put("center_name",center_details.getCenter_name());
				results.put("center_company_id",String.valueOf(center_details.getCenter_company_id()));
				results.put("center_location_id",String.valueOf(center_details.getCenter_location_id()));
				results.put("center_image_filename",center_details.getCenter_image_filename());
				results.put("center_description",center_details.getCenter_description());
				results.put("center_booking_cost",center_details.getCenter_booking_cost());
				results.put("center_contact",center_details.getCenter_contact());
				results.put("center_email",center_details.getCenter_email());
				results.put("center_total_capacity",center_details.getCenter_total_capacity());
				results.put("center_services",center_details.getCenter_services());
//				results.put("company_name",company_details.getCompany_title());
//				results.put("location_name",location_details.getLocation_name());
				resultArray.add(results);
			 
		 }	 

        return resultArray;
	}
	
	@GetMapping("/sells/{id}")
	public ResponseEntity<Sell> getEmployeeById(@PathVariable(value = "id") Long sellId)
			throws ResourceNotFoundException {
		Sell sell = sellRepository.findById(sellId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + sellId));
		return ResponseEntity.ok().body(sell);
	}

	@PostMapping("/sells")
	public Sell createEmployee(@Valid @RequestBody Sell sell) {
		return sellRepository.save(sell);
	}

	@PutMapping("/sells/{id}")
	public ResponseEntity<Sell> updateEmployee(@PathVariable(value = "id") Long sellId,
			@Valid @RequestBody Sell sellDetails) throws ResourceNotFoundException {
		final Sell updatedEmployee = sellRepository.save(sellDetails);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/sells/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long sellId)
			throws ResourceNotFoundException {
		Sell sell = sellRepository.findById(sellId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + sellId));

		sellRepository.delete(sell);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
