package com.app.service;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.app.entity.EmployeeDetails;

public interface EmployeeServiceI {

	public String saveEmployee(String emp, MultipartFile photo);

	public Optional<EmployeeDetails> changeEmployeeDetailsFild(int id, MultipartFile photo,
			String employeeDetails);

	

}
