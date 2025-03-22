package com.app.serviceimpl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.entity.EmployeeDetails;
import com.app.enums.EmployeeType;
import com.app.repo.EmployeeRepo;
import com.app.service.EmployeeServiceI;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@Service
public class EmployeeServiceImpl implements EmployeeServiceI {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String from;

	@Override
	public String saveEmployee(String emp, MultipartFile photo)  {

		
		try {
			ObjectMapper mapper = new ObjectMapper();
		    EmployeeDetails employee = mapper.readValue(emp,EmployeeDetails.class);
		    employee.setProfilePhoto(photo.getBytes());
		    employeeRepo.save(employee);
		    
		    SimpleMailMessage mail = new SimpleMailMessage();
		    mail.setFrom(from);
		    mail.setTo(employee.getEmail());
		    mail.setSubject("Employee Registration");
		    mail.setText("Dear Employee , Your Account has been Created successfully on Krushna FinCorp");
		    
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Employee Data Saved Successfully";
		
					 
	}

	@Override
	public String UpdateEmpName(Integer id, String name) {

		Optional<EmployeeDetails> getById = employeeRepo.findById(id);
		
		EmployeeDetails employeeDetails = getById.get();
		
		employeeDetails.setEmployeeName(name);
		
		employeeRepo.save(employeeDetails);
		
		return "Your Full Name Has been updated successfully.";
	}

	@Override
	public String UpdateEmpEmail(Integer id, String email) {
		
		Optional<EmployeeDetails> getById = employeeRepo.findById(id);
		
		EmployeeDetails employeeDetails = getById.get();
		
		employeeDetails.setEmail(email);
		
		employeeRepo.save(employeeDetails);
		
		return "Your Email Has been updated successfully.";
	}

	@Override
	public String UpdateEmpType(Integer id, EmployeeType empType) {
		
		Optional<EmployeeDetails> getById = employeeRepo.findById(id);
		
		EmployeeDetails employeeDetails = getById.get();
		
		employeeDetails.setEmployeeType(empType);
		
		employeeRepo.save(employeeDetails);
		
		return "Your Employee Type Has been updated successfully.";
	}

	@Override
	public String UpdateEmpPass(Integer id, String pass) {
		
		Optional<EmployeeDetails> getById = employeeRepo.findById(id);
		
		EmployeeDetails employeeDetails = getById.get();
		
		employeeDetails.setPassword(pass);
		
		employeeRepo.save(employeeDetails);
		
		return "Your Password Has been updated successfully.";
	}

	@Override
	public String UpdateEmpPhoto(Integer id, MultipartFile photo) {
		
		Optional<EmployeeDetails> getById = employeeRepo.findById(id);
		
		EmployeeDetails employeeDetails = getById.get();
		
		try {
			employeeDetails.setProfilePhoto(photo.getBytes());
			employeeRepo.save(employeeDetails);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Your Profile Photo Has been updated successfully.";
	}
	
	
	
	

}
