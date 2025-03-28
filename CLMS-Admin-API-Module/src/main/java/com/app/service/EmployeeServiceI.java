package com.app.service;

import java.util.List;
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
	
	public void sendOTP(String email);
	
	public EmployeeDetails verifyOTP(String otp);
	
	public void deleteData(Integer employeeId);

	public EmployeeDetails getEmployee(String employeeEmail, String employeePassword);
	
	public EmployeeDetails getEmployee(String employeeEmail);

	public List<EmployeeDetails> getAllEmployee(String email, String pass);

}
