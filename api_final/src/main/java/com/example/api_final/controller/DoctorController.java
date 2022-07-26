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
import com.example.api_final.model.Patient;
import com.example.api_final.service.DoctorService;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
	private DoctorService doctorService;
	
	public DoctorController(DoctorService doctorService) {
		super();
		this.doctorService = doctorService;
	}
	
	// build create doctor REST API
	@PostMapping()
	public ResponseEntity<Doctor> saveDoctor(@RequestBody Doctor doctor){
		return new ResponseEntity<Doctor>(doctorService.saveDoctor(doctor), HttpStatus.CREATED);
	}
	
	// build get all doctor REST API
	@GetMapping
	public List<Doctor> getAllDoctors(){
		System.out.println("in Doctor Controller");
		
		List<Doctor> doctors = new ArrayList<Doctor>();
		Doctor d = new Doctor(1234, "dr.abc", "dr@community.com", "root", new ArrayList<Patient>());
		doctors.add(d);
		
		System.out.println(d.toString());
		
		return doctors;

	}
	@GetMapping("/getInfo/{id}")
	public Map<String, List<String>> getJoinInformation(@PathVariable("id") long doctorId){
			
			try
		    {
			  System.out.println("----- in PatientRetrieveSQL ------");
			  // create our mysql database connection
		      String myDriver = "com.mysql.cj.jdbc.Driver";
		      String myUrl = "jdbc:mysql://localhost:3306/hms?useSSL=false";
		      Connection conn = DriverManager.getConnection(myUrl, "root", "Japanroxy999");
		      
		      String query = "SELECT d.username as Doctorname, p.username as Patientname FROM Doctor d join Patient p on d.id=p.id_fk where d.id="+doctorId;
		      // create the java statement
		      Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		      
		      // execute the query, and get a java resultset
		      ResultSet rs = st.executeQuery(query);
		      Map<String, List<String>> map = new HashMap<String, List<String>>();
		      
		      List<String> arr = new ArrayList<>();

		      // iterate through the java resultset
		      while (rs.next())
		      {

		    	String patName = rs.getString("Patientname");
		        

		        arr.add(patName);
		        
		      }
		      System.out.println(rs.absolute(1));
		      map.put(rs.getString("Doctorname"), arr);
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
	// build get doctor by id REST API
	// http://localhost:8080/api/doctors/1
	@GetMapping("{id}")
	public ResponseEntity<Doctor> getDoctorById(@PathVariable("id") long doctorId){
		return new ResponseEntity<Doctor>(doctorService.getDoctorById(doctorId), HttpStatus.OK);
	}
	
	// build update doctor REST API
	// http://localhost:8080/api/doctors/1
	@PutMapping("{id}")
	public ResponseEntity<Doctor> updateDoctor(@PathVariable("id") long id, @RequestBody Doctor doctor){
		return new ResponseEntity<Doctor>(doctorService.updateDoctor(doctor, id), HttpStatus.OK);
	}
	
	// build delete doctor REST API
	// http://localhost:8080/api/doctors/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteDoctor(@PathVariable("id") long id){
		
		// delete doctor from DB
		doctorService.deleteDoctor(id);
		
		return new ResponseEntity<String>("Doctor deleted successfully!.", HttpStatus.OK);
	}
}
