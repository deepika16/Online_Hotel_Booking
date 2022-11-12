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

@Table(name = "center")
@Entity(name = "center")

public class Center {

	private long center_id;
	private String center_name;	
	private String center_location_id;
	private String center_company_id;
	private String center_contact;
	private String center_email;
	private String center_total_capacity;
	private String center_services;
	private String center_description;
	private MultipartFile center_image;
	private String center_image_filename;
	private String center_booking_cost;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getCenter_id() {
		return center_id;
	}
	
	public Center() {
		
	}
	
	public void setCenter_id(long center_id) {
		this.center_id = center_id;
	}
	public String getCenter_name() {
		return center_name;
	}
	public void setCenter_name(String center_name) {
		this.center_name = center_name;
	}
	public String getCenter_location_id() {
		return center_location_id;
	}
	public void setCenter_location_id(String center_location_id) {
		this.center_location_id = center_location_id;
	}
	public String getCenter_company_id() {
		return center_company_id;
	}
	public void setCenter_company_id(String center_company_id) {
		this.center_company_id = center_company_id;
	}
	public String getCenter_contact() {
		return center_contact;
	}
	public void setCenter_contact(String center_contact) {
		this.center_contact = center_contact;
	}
	public String getCenter_email() {
		return center_email;
	}
	public void setCenter_email(String center_email) {
		this.center_email = center_email;
	}
	public String getCenter_total_capacity() {
		return center_total_capacity;
	}
	public void setCenter_total_capacity(String center_total_capacity) {
		this.center_total_capacity = center_total_capacity;
	}
	public String getCenter_services() {
		return center_services;
	}
	public void setCenter_services(String center_services) {
		this.center_services = center_services;
	}
	public String getCenter_description() {
		return center_description;
	}
	public void setCenter_description(String center_description) {
		this.center_description = center_description;
	}
	
	public void setCenter_image(MultipartFile center_image) {
		this.center_image = center_image;
	}
	public String getCenter_image_filename() {
		return center_image_filename;
	}
	public void setCenter_image_filename(String center_image_filename) {
		this.center_image_filename = center_image_filename;
	}
	public String getCenter_booking_cost() {
		return center_booking_cost;
	}
	public void setCenter_booking_cost(String center_booking_cost) {
		this.center_booking_cost = center_booking_cost;
	}
	@Override
	public String toString() {
		return "Center [center_id=" + center_id + ", center_name=" + center_name + ", center_location_id="
				+ center_location_id + ", center_company_id=" + center_company_id + ", center_contact=" + center_contact
				+ ", center_email=" + center_email + ", center_total_capacity=" + center_total_capacity
				+ ", center_services=" + center_services + ", center_description=" + center_description
				+ ", center_image=" + center_image + ", center_image_filename=" + center_image_filename
				+ ", center_booking_cost=" + center_booking_cost + "]";
	}
	
	public Center(long center_id, String center_name, String center_location_id, String center_company_id,
			String center_contact, String center_email, String center_total_capacity, String center_services,
			String center_description, MultipartFile center_image, String center_image_filename,
			String center_booking_cost) {
		super();
		this.center_id = center_id;
		this.center_name = center_name;
		this.center_location_id = center_location_id;
		this.center_company_id = center_company_id;
		this.center_contact = center_contact;
		this.center_email = center_email;
		this.center_total_capacity = center_total_capacity;
		this.center_services = center_services;
		this.center_description = center_description;
		this.center_image = center_image;
		this.center_image_filename = center_image_filename;
		this.center_booking_cost = center_booking_cost;
	}
	
	
	
	
}
