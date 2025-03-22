package com.app.entity;

import com.app.enums.EmployeeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employeeId;
	private String employeeName;
	private String email;
	private EmployeeType employeeType;
	private String password;
	@Lob
	@Column(length =99999999)
	private byte[] profilePhoto;
	
}

/*
 * {
 * 
 * "employeeName": "aaaa", "email": "aaaaa@gmail.com", "employeeType":
 * "admin", "password": "12345" }
 */




