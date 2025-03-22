package com.app.service;

import org.springframework.web.multipart.MultipartFile;

public interface EmployeeServiceI {

	public String saveEmployee(String emp, MultipartFile photo);

	public void deleteData(Integer employeeId);

}
