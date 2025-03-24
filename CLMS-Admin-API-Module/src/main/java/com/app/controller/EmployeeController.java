package com.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.app.entity.EmployeeDetails;
import com.app.enums.EmployeeType;
import com.app.service.EmployeeServiceI;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeServiceI employeeService;
	
	@PostMapping("/save_employee")
	public ResponseEntity<String> saveEmployee(@RequestPart("emp") String emp,
			                                   @RequestPart("photo") MultipartFile photo){
	
		String msg = employeeService.saveEmployee(emp,photo);
		return new ResponseEntity<String>(msg,HttpStatus.CREATED);
		
	}
	
	@PatchMapping("/updatename/{id}/{name}")
	public ResponseEntity<String> updateEmployeeName(@PathVariable("id") Integer id, @PathVariable("name") String name){
		
		String msg = employeeService.UpdateEmpName(id, name);
		
		return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
	}
	
	@PatchMapping("/updatemail/{id}/{email}")
	public ResponseEntity<String> updateEmployeeEmail(@PathVariable("id") Integer id, @PathVariable("email") String email){
		
		String msg = employeeService.UpdateEmpEmail(id, email);
		
		return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
	}
	
	@PatchMapping("/updateempType/{id}/{type}")
	public ResponseEntity<String> updateEmployeeType(@PathVariable("id") Integer id, @PathVariable("type") EmployeeType type){
		
		String msg = employeeService.UpdateEmpType(id, type);
		
		return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
	}
	
	@PatchMapping("/updatepassword/{id}/{pass}")
	public ResponseEntity<String> updateEmployeePass(@PathVariable("id") Integer id, @PathVariable("pass") String pass){
		
		String msg = employeeService.UpdateEmpPass(id, pass);
		
		return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
	}
	
	@PatchMapping("/updatephoto/{id}")
	public ResponseEntity<String> updateEmployeePhoto(@PathVariable("id") Integer id, @RequestPart("photo") MultipartFile photo){
		
		String msg = employeeService.UpdateEmpPhoto(id, photo);
		
		return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
	}
	
	
	
	@PutMapping("/changeEmployeeDetails/{employeeId}")
	public ResponseEntity<Optional<EmployeeDetails>> changeEmployeeDetails(@PathVariable("employeeId") int  id, 
			                                                               @RequestPart("profilePhoto") MultipartFile photo,
			                                                               @RequestPart("emp") String employeeDetails )
	{
		Optional<EmployeeDetails> details =employeeService.changeEmployeeDetailsFild(id, photo ,employeeDetails);
		
		return new ResponseEntity<Optional<EmployeeDetails>>(details,HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{employeeId}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("employeeId")Integer employeeId)
	{     
	     employeeService.deleteData(employeeId);
	
        return new 	ResponseEntity<String>("Data is Deleted",HttpStatus.OK);
     }
	
	
	 @GetMapping("/getEmployeeDetails/{email}/{pass}")
	 public ResponseEntity<EmployeeDetails> getEmlpoyeeDetails(@PathVariable("email") String employeeEmail,@PathVariable("pass") String employeePassword)
	 {
		EmployeeDetails employeeDetails =  employeeService.getEmployee(employeeEmail,employeePassword);
		 return new ResponseEntity<EmployeeDetails>(employeeDetails,HttpStatus.OK);
	 }
	 
	 @PostMapping("/sendOTP/{email}")
	 public ResponseEntity<String> sendOTP(@PathVariable("email") String email)
	 {
		 employeeService.sendOTP(email);
		 
		 return new ResponseEntity<String>("OTP has been sent to your email.", HttpStatus.ACCEPTED);
	 }
	 
	 @PostMapping("/verifyOTP/{otp}")
	 public ResponseEntity<EmployeeDetails> validateOTP(@PathVariable("otp") String otp)
	 {
		 
		 EmployeeDetails ed =  employeeService.verifyOTP(otp);
		 
		 if(!ed.equals(null))
		 {
			 return new ResponseEntity<EmployeeDetails>(ed, HttpStatus.OK);
		 }else {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		 }
		 
		 
	 }
	
}