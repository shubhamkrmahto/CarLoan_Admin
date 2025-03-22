package com.app.service;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;
import com.app.entity.EmployeeDetails;
import com.app.enums.EmployeeType;

public interface EmployeeServiceI {

	public String saveEmployee(String emp, MultipartFile photo);
	
	public Optional<EmployeeDetails> changeEmployeeDetailsFild(int id, MultipartFile photo,
			String employeeDetails);

	
	public String UpdateEmpName(Integer id, String name);
	
	public String UpdateEmpEmail(Integer id, String email);
	
	public String UpdateEmpType(Integer id, EmployeeType empType);
	
	public String UpdateEmpPass(Integer id, String pass);
	
	public String UpdateEmpPhoto(Integer id, MultipartFile photo);
	
	public void deleteData(Integer employeeId);

}
