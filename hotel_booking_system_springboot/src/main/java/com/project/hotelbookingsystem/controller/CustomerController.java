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
import com.project.hotelbookingsystem.model.Customer;
import com.project.hotelbookingsystem.model.Employee;
import com.project.hotelbookingsystem.model.Login;
import com.project.hotelbookingsystem.repository.CustomerRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@PersistenceContext
    private EntityManager entityManager;

	@GetMapping("/customers")
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@GetMapping("/customers/search/{name}")
	public List<Customer> getCustomerByName(@PathVariable(value = "name") String customerName) {
			return customerRepository.serchUserByName(customerName);
	}

	
	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") Long customerId)
			throws ResourceNotFoundException {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
		return ResponseEntity.ok().body(customer);
	}

	@PostMapping("/customers")
	public Customer createCustomer(@Valid @RequestBody Customer customer) {
		return customerRepository.save(customer);
	}

	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Long customerId,
			@Valid @RequestBody Customer customerDetails) throws ResourceNotFoundException {
		final Customer updatedCustomer = customerRepository.save(customerDetails);
		return ResponseEntity.ok(updatedCustomer);
	}

	@DeleteMapping("/customers/{id}")
	public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Long customerId)
			throws ResourceNotFoundException {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found for this id :: " + customerId));
		
		customerRepository.delete(customer);
		
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	public ArrayList getLoginData(long login_id) {
		 Query q = entityManager.createQuery("SELECT emp, log from employee emp, login log WHERE employee_id = login_employee_id AND login_id = :login_id");
		 List<Object[]> login = q.setParameter("login_id", login_id).getResultList();
				 ArrayList<HashMap<String, String>> resultArray = new ArrayList();
		 
		
		 for ( Object[] row : login ) {
			  
			 HashMap<String, String> results = new HashMap();
			  Login login_details = (Login)row[ 1 ];
			  Employee employee_details = (Employee)row[ 0 ];
			  
				 System.out.print("Employee ID"+login_details.getLogin_employee_id());

			    results.put("login_id",String.valueOf(login_details.getLogin_id()));
				results.put("login_employee_id",login_details.getLogin_employee_id());
				results.put("login_email",login_details.getLogin_email());
				results.put("login_level_id",login_details.getLogin_level_id());
				
			    results.put("employee_id",String.valueOf(employee_details.getEmployee_id()));
				results.put("employee_sal",employee_details.getEmployee_sal());
				results.put("employee_first_name",employee_details.getEmployee_first_name());
				results.put("employee_middle_name",employee_details.getEmployee_middle_name());
				results.put("employee_last_name",employee_details.getEmployee_last_name());
				results.put("employee_gender",employee_details.getEmployee_gender());
				results.put("employee_address",employee_details.getEmployee_address());
				results.put("employee_village",employee_details.getEmployee_village());
				results.put("employee_state",employee_details.getEmployee_state());
				results.put("employee_country",employee_details.getEmployee_country());
				results.put("employee_landline",employee_details.getEmployee_landline());
				results.put("employee_mobile",employee_details.getEmployee_mobile());
				results.put("employee_email",employee_details.getEmployee_email());
				results.put("employee_status",employee_details.getEmployee_status());
				results.put("employee_deparment",employee_details.getemployee_department());
				results.put("employee_dob",employee_details.getEmployee_dob());
				results.put("employee_nationalty",employee_details.getEmployee_nationalty());
			    resultArray.add(results);
			 
		 }	 
       return resultArray;
	}
	
	@PostMapping("/customer-login")
	public Customer checkLogin(@Valid @RequestBody Login login) {
		Customer loginObj = customerRepository.checkLogin(login.getLogin_email(), login.getLogin_password());
		System.out.print(loginObj);
		return loginObj;//this.getLoginData(loginObj.getLogin_id());
	}
}
