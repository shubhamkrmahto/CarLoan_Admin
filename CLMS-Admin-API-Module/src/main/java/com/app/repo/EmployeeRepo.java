package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entity.Employee;

import jakarta.transaction.Transactional;

import java.util.List;


public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

	public Employee findByEmployeeEmail(String employeeEmail);
	
	@Modifying
	@Transactional
	@Query(
	    value = "INSERT INTO employee (employee_name, employee_email, password, employee_contact, employee_type, profile_photo) " +
	            "VALUES (:employeeName, :employeeEmail, :password, :employeeContact, :employeeType, :profilePhoto)", 
	    nativeQuery = true
	)
	void insertEmployee(
	    @Param("employeeName") String employeeName,
	    @Param("employeeEmail") String employeeEmail,
	    @Param("password") String password,
	    @Param("employeeContact") Long employeeContact,
	    @Param("employeeType") String employeeType,
	    @Param("profilePhoto") byte[] profilePhoto
	);


}
