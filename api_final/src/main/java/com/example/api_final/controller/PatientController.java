package com.example.api_final.controller;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_final.model.Doctor;
//import com.example.api_final.model.Doctor;
import com.example.api_final.model.Patient;
//import com.example.api_final.service.DoctorService;
import com.example.api_final.service.PatientService;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
	private PatientService patientService;
	
	public PatientController(PatientService patientService) {
		super();
		this.patientService = patientService;
	}
	
	// build create patient REST API
	@PostMapping()
	public ResponseEntity<Patient> saveDoctor(@RequestBody Patient patient){
		return new ResponseEntity<Patient>(patientService.savePatient(patient), HttpStatus.CREATED);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable("id") long patientId){
		return new ResponseEntity<Patient>(patientService.getPatientById(patientId), HttpStatus.OK);
	}
	
	@GetMapping("/getInfo/{id}")
	public ResponseEntity<String> getJoinInformation(){
		try {
			patientService.getJoinInformation();
			return new ResponseEntity<String>("", HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<String>("A patient cannot request a list of patients.", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	// build update doctor REST API
	// http://localhost:8080/api/doctors/1
	@PutMapping("{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable("id") long id, @RequestBody Patient patient){
		return new ResponseEntity<Patient>(patientService.updatePatient(patient, id), HttpStatus.OK);
	}
	
	// build delete doctor REST API
	// http://localhost:8080/api/doctors/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deletePatient(@PathVariable("id") long id){
		
		// delete doctor from DB
		patientService.deletePatient(id);
		
		return new ResponseEntity<String>("Patient deleted successfully!.", HttpStatus.OK);
	}
}
