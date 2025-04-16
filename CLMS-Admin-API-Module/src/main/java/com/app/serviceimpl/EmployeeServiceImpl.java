package com.app.serviceimpl;

import java.io.IOException;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.fasterxml.jackson.databind.ObjectMapper;



@Service
public class EmployeeServiceImpl implements EmployeeServiceI {
	
	private static final Logger log=LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private JavaMailSender mailSender;
	
	//generated otp by methods
	private String generatedOTP;
	
	//User email
	private String emails;
	
	private final Random random = new Random();
	
	public String generateOTP()
	{
		generatedOTP = String.format("%06d", random.nextInt(999999));
		
		return generatedOTP;
	}
	
	public boolean validateOTP(String getOTP)
	{
		return getOTP.equals(generatedOTP);
	}
	
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
		    mail.setText("Dear Employee , Your Account has been Created successfully on Krushna FinCorp.");
		    
		    
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		log.info("Employee has been saved successfully." );
		
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
		
		log.info("Employee has been UPDATED successfully and Enquiry id is : " + id );
		return Optional.of(details);
	}

	public String UpdateEmpName(Integer id, String name) {

		Optional<EmployeeDetails> getById = employeeRepo.findById(id);
		
		EmployeeDetails employeeDetails = getById.get();
		
		employeeDetails.setEmployeeName(name);
		
		employeeRepo.save(employeeDetails);
		
		log.info("Employee has been updated NAME successfully for enquiry id = " + id);
		
		return "Your Full Name Has been updated successfully.";
	}

	@Override
	public String UpdateEmpEmail(Integer id, String email) {
		
		Optional<EmployeeDetails> getById = employeeRepo.findById(id);
		
		EmployeeDetails employeeDetails = getById.get();
		
		employeeDetails.setEmail(email);
		
		employeeRepo.save(employeeDetails);
		
		log.info("Employee has been updated EMAIL successfully for enquiry id = " + id);
		
		return "Your Email Has been updated successfully.";
	}

	@Override
	public String UpdateEmpType(Integer id, EmployeeType empType) {
		
		Optional<EmployeeDetails> getById = employeeRepo.findById(id);
		
		EmployeeDetails employeeDetails = getById.get();
		
		employeeDetails.setEmployeeType(empType);
		
		employeeRepo.save(employeeDetails);
		
		
		log.info("Employee has been updated TYPE successfully for enquiry id = " + id);
		
		return "Your Employee Type Has been updated successfully.";
	}

	@Override
	public String UpdateEmpPass(Integer id, String pass) {
		
		Optional<EmployeeDetails> getById = employeeRepo.findById(id);
		
		EmployeeDetails employeeDetails = getById.get();
		
		employeeDetails.setPassword(pass);
		
		employeeRepo.save(employeeDetails);
		
		log.info("Employee has been updated password successfully for enquiry id = " + id);
		
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
		
		log.info("Employee has been updated Profile Photo successfully for enquiry id = " + id);
		
		return "Your Profile Photo Has been updated successfully.";
	}
	
		
	@Override
	public void deleteData(Integer employeeId) {
		
	   employeeRepo.deleteById(employeeId);
	   
	   log.info("Employee has been Deleted for Employee id : " + employeeId);
	}
		
		

	@Override
	public EmployeeDetails getEmployee(String employeeEmail, String employeePassword) {

	   EmployeeDetails employee = employeeRepo.findByEmail(employeeEmail);
	   if(employee.getEmail().equals(employeeEmail) && employee.getPassword().equals(employeePassword))
	   {
		   
		   log.info(" GET All DATA successfully ");
		   
		   return employee;
	   }
	   else {
		   
		   log.info("Incorrect Infomation  Please Enter Valid Details");
		     
	   		}
		return null;
	}

	@Override
	public void sendOTP(String email) {
		// TODO Auto-generated method stub
		emails=email;
		generateOTP();
		
		SimpleMailMessage mail = new SimpleMailMessage();
	    mail.setFrom(from);
	    mail.setTo(emails);
	    mail.setSubject("Employee Registration");
	    mail.setText("Your One Time Password is "+generatedOTP+".");
	    
	    mailSender.send(mail);
		
	}

	@Override
	public EmployeeDetails verifyOTP(String otp) {
		if(validateOTP(otp)) {
			return getEmployee(emails);
		}
		else
		{
			return null;
		}
	}

	@Override
	public EmployeeDetails getEmployee(String mail) {
		
		return employeeRepo.findByEmail(mail);
	}

	@Override
	public List<EmployeeDetails> getAllEmployee(String email, String pass) {
		

		   if(email.equals("Admin") && pass.equals("Admin")){
			    
		   		return employeeRepo.findAll();
		   	}
		return null;
	}
	
	

}
