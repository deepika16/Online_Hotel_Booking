package com.project.hotelbookingsystem.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.validation.Valid;
import java.nio.file.Files;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.hotelbookingsystem.exception.ResourceNotFoundException;
import com.project.hotelbookingsystem.model.Center;
import com.project.hotelbookingsystem.model.Company;
import com.project.hotelbookingsystem.model.Employee;
import com.project.hotelbookingsystem.model.Location;
import com.project.hotelbookingsystem.model.Month;
import com.project.hotelbookingsystem.model.Salary;
import com.project.hotelbookingsystem.repository.CenterRepository;
import com.project.hotelbookingsystem.services.FileUploadService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class CenterController {

	@Autowired
	private CenterRepository centerRepository;
	
	@Autowired
	public FileUploadService fileService;
	
	@PersistenceContext
    private EntityManager entityManager;

	@GetMapping("/center")
	public List<Center> getAllCenters() {
		return centerRepository.findAll();
	}

	@GetMapping("/center/search/{name}")
	public List<Center> getCenterByName(@PathVariable(value = "name") String centerName) {
			return centerRepository.serchUserByName(centerName);
	}
	
	
	@GetMapping("/center/{id}")
	public ResponseEntity<Center> getCenterById(@PathVariable(value = "id") Long centerId)
			throws ResourceNotFoundException {
		Center center = centerRepository.findById(centerId)
				.orElseThrow(() -> new ResourceNotFoundException("Center not found for this id :: " + centerId));
		return ResponseEntity.ok().body(center);
	}
	
	@GetMapping("/center/center-details/{id}")
	public ArrayList getCenterDetailsById(@PathVariable(value = "id") Long centerId)
	 {
		String SQL = "SELECT center, loc from center center, location loc WHERE location_id = center_location_id";
		 Query q = entityManager.createQuery(SQL);
		 if(!centerId.equals("0")) {
			 System.out.print("Center Id : "+centerId);

			 SQL = "SELECT center, loc from center center, location loc WHERE location_id = center_location_id AND center_id = :centerId";
			 q = entityManager.createQuery(SQL);
			 q.setParameter("centerId", centerId);
		 } 
		 List<Object[]> center = q.getResultList();
		 ArrayList<HashMap<String, String>> resultArray = new ArrayList();
		 
		 for ( Object[] row : center ) {
			  Center center_details = (Center)row[ 0 ];
			  Location location_details = (Location)row[ 1 ];
			 
			  
			    HashMap<String, String> results = new HashMap();
			   
				results.put("center_id",String.valueOf(center_details.getCenter_id()));
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
				
				results.put("location_name",location_details.getLocation_name());

				
			    resultArray.add(results);
			 
		 }	 

       return resultArray;
	}
	
	@GetMapping("/center/all-center/{id}")
	public ArrayList getAllSalaryFields(@PathVariable(value = "id") String location_id) {
		
		String SQL = "SELECT center, loc from center center, location loc WHERE location_id = center_location_id";
		 Query q = entityManager.createQuery(SQL);
		 if(!location_id.equals("0")) {
			 System.out.print("Employee Id : "+location_id);

			 SQL = "SELECT center, loc from center center, location loc WHERE location_id = center_location_id AND location_id = :location_id";
			 q = entityManager.createQuery(SQL);
			 q.setParameter("location_id", location_id);
		 } 
		 List<Object[]> center = q.getResultList();
		 ArrayList<HashMap<String, String>> resultArray = new ArrayList();
		 
		 for ( Object[] row : center ) {
			  Center center_details = (Center)row[ 0 ];
			  Location location_details = (Location)row[ 1 ];
			 
			    HashMap<String, String> results = new HashMap();
			   
			    results.put("center_id",String.valueOf(center_details.getCenter_id()));
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
				
				results.put("location_name",location_details.getLocation_name());
				
			    resultArray.add(results);
			 
		 }	 

        return resultArray;
	}
	
	@GetMapping("/center/all-search/{search}")
	public ArrayList searchCenter(@PathVariable(value = "search") String search) {
		
		String SQL = "SELECT center, loc from center center, location loc WHERE location_id = center_location_id";
		 Query q = entityManager.createQuery(SQL);
		 if(!search.equals("0")) {

			 SQL = "SELECT center, loc from center center, location loc WHERE location_id = center_location_id AND (center_name LIKE :title OR location_name LIKE :title)";
			 q = entityManager.createQuery(SQL);
			 q.setParameter("title",  "%" + search + "%");
		 } 
		 List<Object[]> center = q.getResultList();
		 ArrayList<HashMap<String, String>> resultArray = new ArrayList();
		 
		 for ( Object[] row : center ) {
			  Center center_details = (Center)row[ 0 ];
			  Location location_details = (Location)row[ 1 ];
			 

			    HashMap<String, String> results = new HashMap();
			   
			    results.put("center_id",String.valueOf(center_details.getCenter_id()));
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
				
				results.put("location_name",location_details.getLocation_name());
				
			    resultArray.add(results);
			 
		 }	 

        return resultArray;
	}
	

	@RequestMapping(value = "/center", method = RequestMethod.POST,
    consumes = {"multipart/form-data"})	
	public Center createCenter(@RequestParam("center_image") MultipartFile center_image, 
			@ModelAttribute("form") Center center) {
		System.out.print("File Data");
		try {
			long unixTime = System.currentTimeMillis() / 1000L;
			String fileName = unixTime+"_" +center_image.getOriginalFilename();
			System.out.print("File URL : ");
			System.out.print(this.fileService.uploadToLocalFileSystem(center_image, fileName));  
            center.setCenter_image_filename(fileName);
		
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return centerRepository.save(center);
	}
	
	 
    // For Downloading Files
    @GetMapping("/center/center_image/{fileName:.+}")
    public Path getFileUrl(@PathVariable(name = "fileName") String fileName) throws IOException {
    	System.out.print("Printing URL");
    	System.out.print(this.fileService.getFileURL(fileName));
    	Path fileLocation = this.fileService.getFileLocation(fileName);
    	File file = new File(fileLocation.toString());
    	InputStreamResource resource = new InputStreamResource(new FileInputStream(fileLocation.toString()));
   
    	return fileLocation;
    }

    @RequestMapping(value = "/center/{id}", method = RequestMethod.POST,
    consumes = {"multipart/form-data"})	
	public Center updateCenter(@RequestParam("center_image") MultipartFile center_image, 
			@ModelAttribute("form") Center center) {
		System.out.print("File Data");
		try {
			if(!center_image.isEmpty()) {
				long unixTime = System.currentTimeMillis() / 1000L;
				String fileName = unixTime+"_" +center_image.getOriginalFilename();
				this.fileService.uploadToLocalFileSystem(center_image, fileName);
	            center.setCenter_image_filename(fileName);
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return centerRepository.save(center);
	}
    
    @PutMapping("/center/{id}")
	public ResponseEntity<Center> updateCenter(@PathVariable(value = "id") Long centerId,
			@Valid @RequestBody Center centerDetails) throws ResourceNotFoundException {
		final Center updatedCenter = centerRepository.save(centerDetails);
		return ResponseEntity.ok(updatedCenter);
	}

	@DeleteMapping("/center/{id}")
	public Map<String, Boolean> deleteCenter(@PathVariable(value = "id") Long centerId)
			throws ResourceNotFoundException {
		Center center = centerRepository.findById(centerId)
				.orElseThrow(() -> new ResourceNotFoundException("Center not found for this id :: " + centerId));

		centerRepository.delete(center);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
