package com.example.api_final.service;

import java.util.List;


//import com.example.api_final.model.Doctor;
import com.example.api_final.model.Patient;

public interface PatientService {
	Patient savePatient(Patient patient);
	List<Patient> getAllPatients();
	Patient getPatientById(long id);
	Patient updatePatient(Patient patient, long id);
	void deletePatient(long id);
	void getJoinInformation() throws Exception;
}
