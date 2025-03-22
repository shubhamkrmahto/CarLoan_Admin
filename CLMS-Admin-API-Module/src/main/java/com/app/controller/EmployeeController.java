package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	

}
