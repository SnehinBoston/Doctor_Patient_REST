package com.example.api_final.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.api_final.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{
	
}
