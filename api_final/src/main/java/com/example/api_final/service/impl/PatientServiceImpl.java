package com.example.api_final.service.impl;

import java.util.List;


import org.springframework.stereotype.Service;

import com.example.api_final.exception.ResourceNotFoundException;
import com.example.api_final.model.Patient;
//import com.example.api_final.repository.DoctorRepository;
import com.example.api_final.repository.PatientRepository;
import com.example.api_final.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService{
	private PatientRepository patientRepository;

	public PatientServiceImpl(PatientRepository patientRepository) {
		super();
		this.patientRepository = patientRepository;
	}
	
	public Patient savePatient(Patient patient) {
		return patientRepository.save(patient);
	}
	
	public List<Patient> getAllPatients(){
		return patientRepository.findAll();
	}
	
	
	public Patient getPatientById(long id) {
		return patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient", "id", id));
	}
	
	public Patient updatePatient(Patient patient, long id) {
		return null;
	}
	
	public void deletePatient(long id) {
		patientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patient", "Id", id));
		patientRepository.deleteById(id);
	}
	public void getJoinInformation() throws Exception{
	     throw new Exception("Patient can't find doctor details");
	}
}
