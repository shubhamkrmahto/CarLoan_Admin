package com.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.entity.EmployeeDetails;
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

	
	@PutMapping("/changeEmployeeDetails/{employeeId}")
	public ResponseEntity<Optional<EmployeeDetails>> changeEmployeeDetails(@PathVariable("employeeId") int  id, 
			                                                               @RequestPart("profilePhoto") MultipartFile photo,
			                                                               @RequestPart("emp") String employeeDetails )
	{
		Optional<EmployeeDetails> details =employeeService.changeEmployeeDetailsFild(id, photo ,employeeDetails);
		
		return new ResponseEntity<Optional<EmployeeDetails>>(details,HttpStatus.ACCEPTED);
	}
}
