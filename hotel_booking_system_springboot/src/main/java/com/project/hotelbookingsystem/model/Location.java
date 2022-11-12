package com.project.hotelbookingsystem.model;

import javax.persistence.CascadeType;
//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

@Table(name = "location")
@Entity(name = "location")

public class Location {

	private long location_id;
	private String location_name;
	private String location_description;
	private MultipartFile location_image;
	private String location_image_filename;
	
	@Override
	public String toString() {
		return "Location [location_id=" + location_id + ", location_name=" + location_name + ", location_description="
				+ location_description + ", location_image=" + location_image + ", location_image_filename="
				+ location_image_filename + "]";
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getLocation_id() {
		return location_id;
	}
	
	public Location() {
		
	}
	public Location(long location_id, String location_name, String location_description, MultipartFile location_image,
			String location_image_filename) {
		super();
		this.location_id = location_id;
		this.location_name = location_name;
		this.location_description = location_description;
		this.location_image = location_image;
		this.location_image_filename = location_image_filename;
	}

	public void setLocation_id(long location_id) {
		this.location_id = location_id;
	}
	public String getLocation_name() {
		return location_name;
	}
	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
	public String getLocation_description() {
		return location_description;
	}
	public void setLocation_description(String location_description) {
		this.location_description = location_description;
	}
	
	public void setLocation_image(MultipartFile location_image) {
		this.location_image = location_image;
	}
	public String getLocation_image_filename() {
		return location_image_filename;
	}
	public void setLocation_image_filename(String location_image_filename) {
		this.location_image_filename = location_image_filename;
	}
	
	
}
