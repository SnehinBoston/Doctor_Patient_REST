package com.example.api_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api_final.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{
	
}
