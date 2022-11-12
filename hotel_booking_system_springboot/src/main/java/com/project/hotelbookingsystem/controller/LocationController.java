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
import com.project.hotelbookingsystem.model.Location;
import com.project.hotelbookingsystem.repository.LocationRepository;
import com.project.hotelbookingsystem.services.FileUploadService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class LocationController {

	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	public FileUploadService fileService;
	
	@PersistenceContext
    private EntityManager entityManager;

	@GetMapping("/categories")
	public List<Location> getAllLocations() {
		return locationRepository.findAll();
	}

	@GetMapping("/categories/search/{name}")
	public List<Location> getLocationByName(@PathVariable(value = "name") String locationName) {
			return locationRepository.serchUserByName(locationName);
	}
	
	@GetMapping("/categories/search-state/{state}")
	public List<Location> serchUserByState(@PathVariable(value = "state") String locationState) {
			return locationRepository.serchUserByState(locationState);
	}
	
	
	@GetMapping("/categories/{id}")
	public ResponseEntity<Location> getLocationById(@PathVariable(value = "id") Long locationId)
			throws ResourceNotFoundException {
		Location location = locationRepository.findById(locationId)
				.orElseThrow(() -> new ResourceNotFoundException("Location not found for this id :: " + locationId));
		return ResponseEntity.ok().body(location);
	}
	

	@RequestMapping(value = "/categories", method = RequestMethod.POST,
    consumes = {"multipart/form-data"})	
	public Location createLocation(@RequestParam("location_image") MultipartFile location_image, 
			@ModelAttribute("form") Location location) {
		System.out.print("File Data");
		try {
			long unixTime = System.currentTimeMillis() / 1000L;
			String fileName = unixTime+"_" +location_image.getOriginalFilename();
			System.out.print("File URL : ");
			System.out.print(this.fileService.uploadToLocalFileSystem(location_image, fileName));  
            location.setLocation_image_filename(fileName);
		
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return locationRepository.save(location);
	}
	
	 
    // For Downloading Files
    @GetMapping("/categories/location_image/{fileName:.+}")
    public Path getFileUrl(@PathVariable(name = "fileName") String fileName) throws IOException {
    	System.out.print("Printing URL");
    	System.out.print(this.fileService.getFileURL(fileName));
    	Path fileLocation = this.fileService.getFileLocation(fileName);
    	File file = new File(fileLocation.toString());
    	InputStreamResource resource = new InputStreamResource(new FileInputStream(fileLocation.toString()));
   
    	return fileLocation;
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.POST,
    consumes = {"multipart/form-data"})	
	public Location updateLocation(@RequestParam("location_image") MultipartFile location_image, 
			@ModelAttribute("form") Location location) {
		System.out.print("File Data");
		try {
			if(!location_image.isEmpty()) {
				long unixTime = System.currentTimeMillis() / 1000L;
				String fileName = unixTime+"_" +location_image.getOriginalFilename();
				this.fileService.uploadToLocalFileSystem(location_image, fileName);
	            location.setLocation_image_filename(fileName);
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
		return locationRepository.save(location);
	}
    
    @PutMapping("/categories/{id}")
	public ResponseEntity<Location> updateLocation(@PathVariable(value = "id") Long locationId,
			@Valid @RequestBody Location locationDetails) throws ResourceNotFoundException {
		final Location updatedLocation = locationRepository.save(locationDetails);
		return ResponseEntity.ok(updatedLocation);
	}

	@DeleteMapping("/categories/{id}")
	public Map<String, Boolean> deleteLocation(@PathVariable(value = "id") Long locationId)
			throws ResourceNotFoundException {
		Location location = locationRepository.findById(locationId)
				.orElseThrow(() -> new ResourceNotFoundException("Location not found for this id :: " + locationId));

		locationRepository.delete(location);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
