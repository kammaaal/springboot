package com.belajar.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "employee")
public class Employee extends Persistence {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "address", nullable = false)	
	private String address;
	
	@Column(name = "nik", nullable = false, unique = true)
	private int nik;
	
	@JoinColumn(name = "position_id", nullable = false)
	@ManyToOne(targetEntity = Position.class, fetch = FetchType.LAZY)
	@JsonIgnore
	private Position position;
	
	public Employee() {
		
	}

	public Employee(String name, String address, int nik, Position position) {
		super();
		this.name = name;
		this.address = address;
		this.nik = nik;
		this.position = position;
	}
	
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
	public int getNik() {
		return nik;
	}
	public void setNik(int nik) {
		this.nik = nik;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	
}
