package com.belajar.springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.belajar.springboot.model.Employee;
import com.belajar.springboot.model.Position;
import com.belajar.springboot.payload.EmployeePayload;
import com.belajar.springboot.payload.ErrorResponse;
import com.belajar.springboot.repository.EmployeeRepo;
import com.belajar.springboot.repository.PositionRepo;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired
	PositionRepo positionRepo;
	
	@GetMapping(path = "/getall", produces = "application/json")
	public ResponseEntity<?> getAll() {
		List<Employee> employees = employeeRepo.findAll();
		
		List <EmployeePayload> response = new ArrayList<EmployeePayload>();
		for (Employee employee : employees) {
			response.add(new EmployeePayload(
					employee.getName(), 
					employee.getAddress(), 
					employee.getNik(), 
					employee.getPosition().getPositionName()));
		}
		
		return new ResponseEntity<List<EmployeePayload>>(response, HttpStatus.OK);
	}
	
	@GetMapping(path = "/get/{id}", produces = "application/json")
	public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
		Employee employee = employeeRepo.findById(id).orElse(null);
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/delete/{id}", produces = "application/json")
	public ResponseEntity<?> deleteById(@PathVariable("id") Integer id) {
		employeeRepo.deleteById(id);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createEmployee(@RequestBody EmployeePayload payload) {
		
		Position position = positionRepo.findByPositionNameIgnoreCase(payload.getPosition());
		if (position == null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					"Position is null", 
					"Posisi yang anda masukan tidak tersedia"), HttpStatus.BAD_REQUEST);
		}
		
		Employee existEmployee = employeeRepo.findByNik(payload.getNik());
		if (existEmployee != null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					"Employee is exists", 
					"Data nik sudah terdaftar"), HttpStatus.BAD_REQUEST);
		}
		
		try {
			Employee newEmployee = new Employee(
					payload.getName(), payload.getAddress(), 
					payload.getNik(), position);
			employeeRepo.save(newEmployee);
		} catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					e.getMessage(), 
					"Maaf request anda tidak dapat diproses"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<EmployeePayload>(payload, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/update/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> createEmployee(@PathVariable("id") Integer id, @RequestBody EmployeePayload payload) {
		Position position = positionRepo.findByPositionNameIgnoreCase(payload.getPosition());
		if (position == null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					"Position is null", 
					"Posisi yang anda masukan tidak tersedia"), HttpStatus.BAD_REQUEST);
		}
		
		Employee existEmployee = employeeRepo.findById(id).orElse(null);
		if (existEmployee == null) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(
					"Employee not found", 
					"Data tidak terdaftar"), HttpStatus.BAD_REQUEST);
		}
		
		existEmployee.setName(payload.getName());
		existEmployee.setAddress(payload.getAddress());
		existEmployee.setNik(payload.getNik());
		existEmployee.setPosition(position);
		employeeRepo.save(existEmployee);
		return new ResponseEntity<EmployeePayload>(payload, HttpStatus.CREATED);
	}

}

