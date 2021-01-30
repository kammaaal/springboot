package com.belajar.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.belajar.springboot.model.Position;

public interface PositionRepo extends JpaRepository<Position, Integer>{
	public Position findByPositionNameIgnoreCase(String name);
}
