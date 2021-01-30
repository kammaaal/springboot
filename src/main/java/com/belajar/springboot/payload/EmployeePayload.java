package com.belajar.springboot.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmployeePayload {
	@JsonProperty("name")
	private String name;
	@JsonProperty("address")
	private String address;
	@JsonProperty("nik")
	private int nik;
	@JsonProperty("position")
	private String position;
	
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public int getNik() {
		return nik;
	}
	public String getPosition() {
		return position;
	}
}
