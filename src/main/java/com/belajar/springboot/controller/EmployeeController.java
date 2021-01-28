package com.belajar.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
	List<EmployeeDto> employees = new ArrayList<EmployeeDto>();
	
	@GetMapping(path = "", produces = "application/json")
	public String home() {
		return "HELLOO INI WEB PERTAMA SAYA";
	}

	@PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<EmployeeDto>> createEmployee(@RequestBody EmployeeDto employee) {
		employees.add(employee);
		return new ResponseEntity<List<EmployeeDto>>(employees, HttpStatus.CREATED);
	}

}

class EmployeeDto {
	@JsonProperty("id")
	private int id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("address")
	private String address;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}

