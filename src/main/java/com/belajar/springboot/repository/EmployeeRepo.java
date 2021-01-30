package com.belajar.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.belajar.springboot.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>{
	public Employee findByNik(int nik);

}
