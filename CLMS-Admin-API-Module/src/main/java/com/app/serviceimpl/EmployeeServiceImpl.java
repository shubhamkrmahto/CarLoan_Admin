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
	public Optional<EmployeeDetails> changeEmployeeDetailsFild(int id, MultipartFile photo,
			String employeeDetails) {
		
		Optional<EmployeeDetails> employeeDeta = employeeRepo.findById(id);
		
		EmployeeDetails details = employeeDeta.get();
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			
			
		    EmployeeDetails employee = mapper.readValue(employeeDetails,EmployeeDetails.class);
			
			
		    details.setEmployeeName(employee.getEmployeeName());
		    details.setEmail(employee.getEmail());
		    details.setEmployeeType(employee.getEmployeeType());
		
		    details.setPassword(employee.getPassword());
		
		    details.setProfilePhoto(photo.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		employeeRepo.save(details);
		return Optional.of(details);
	}

	
	
	
	
	

}
