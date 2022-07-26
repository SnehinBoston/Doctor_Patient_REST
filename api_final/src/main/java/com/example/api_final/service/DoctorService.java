package com.example.api_final.service;

import java.util.List;
import java.util.Map;

import com.example.api_final.model.Doctor;

public interface DoctorService {
	Doctor saveDoctor(Doctor doctor);
	List<Doctor> getAllDoctors();
	Doctor getDoctorById(long id);
	Doctor updateDoctor(Doctor doctor, long id);
	void deleteDoctor(long id);
	Map<String, List<String>> getJoinInformation(long doctorId);
}
