package com.app.service;

import org.springframework.web.multipart.MultipartFile;

import com.app.enums.EmployeeType;

public interface EmployeeServiceI {

	public String saveEmployee(String emp, MultipartFile photo);

	public String UpdateEmpName(Integer id, String name);
	
	public String UpdateEmpEmail(Integer id, String email);
	
	public String UpdateEmpType(Integer id, EmployeeType empType);
	
	public String UpdateEmpPass(Integer id, String pass);
	
	public String UpdateEmpPhoto(Integer id, MultipartFile photo);
}
