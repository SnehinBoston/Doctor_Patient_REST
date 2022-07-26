package com.example.api_final.model;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.api_final.model.Patient;

import lombok.Data;

@Data
@Entity
@Table(name="doctor")
public class Doctor {
	

	public Doctor(long id, String username, String email, String password, List<Patient> patients) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.patients = patients;
	}

	
	
	@Override
	public String toString() {
		return "Doctor [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", patients=" + patients + "]";
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@OneToMany(targetEntity = Patient.class, cascade = CascadeType.ALL)
	@JoinColumn(name="id_fk", referencedColumnName = "id")
	private List<Patient> patients;
}
