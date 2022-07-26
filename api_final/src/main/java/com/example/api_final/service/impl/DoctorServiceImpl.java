package com.example.api_final.service.impl;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.api_final.exception.ResourceNotFoundException;
import com.example.api_final.model.Doctor;
import com.example.api_final.repository.DoctorRepository;
import com.example.api_final.service.DoctorService;

@Service

public class DoctorServiceImpl implements DoctorService{
	private DoctorRepository doctorRepository;
	
	public DoctorServiceImpl(DoctorRepository doctorRepository) {
		super();
		this.doctorRepository = doctorRepository;
	}
	@Override
	public Doctor saveDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}
	
	@Override
	public List<Doctor> getAllDoctors(){
		return doctorRepository.findAll();
	}
	@Override
	public Map<String, List<String>> getJoinInformation(long doctorId){
		try
	    {
			System.out.println("----- in DoctorRetrievePatient ------");
			
	      // create our mysql database connection
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://localhost:3306/test_api?useSSL=false";
	      Connection conn = DriverManager.getConnection(myUrl, "root", "Japanroxy999");
	      System.out.println("Connection established");
	      // our SQL SELECT query. 
	      // if you only need a few columns, specify them by name instead of using "*"
	      String query = "SELECT d.username as Doctorname, p.username as Patientname FROM Doctor d join Patients p where d.email=p.id_fk";
//	      String query = "SELECT username as Doctorname from Doctor;";
	      // create the java statement
	      Statement st = conn.createStatement();
	      
	      // execute the query, and get a java resultset
	      ResultSet rs = st.executeQuery(query);
	      
	      Map<String, List<String>> map = new HashMap<String, List<String>>();
	      String docName = rs.getString("Doctorname");
	      
	      List<String> arr = new ArrayList<>();

	      while (rs.next())
	      {
	        String patName = rs.getString("Patientname");
	        
	        System.out.println("Docname ----- " + docName + " patname ---- "+ patName);
	        arr.add(patName);
	        
	        // print the results
	        System.out.format("DOC: %s, %s \n", docName, patName);
	      }
	      map.put(docName, arr);
	      st.close();
	      return map;
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	      return null;
	    }
		
	  }

	@Override
	public Doctor getDoctorById(long id) {
		return doctorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Doctor", "id", id));
	}
	
	@Override
	public Doctor updateDoctor(Doctor doctor, long id) {
		return null;
	}
	
	@Override
	public void deleteDoctor(long id) {
		
		// check whether a employee exist in a DB or not
		doctorRepository.findById(id).orElseThrow(() -> 
								new ResourceNotFoundException("Doctor", "Id", id));
		doctorRepository.deleteById(id);
	}
}
