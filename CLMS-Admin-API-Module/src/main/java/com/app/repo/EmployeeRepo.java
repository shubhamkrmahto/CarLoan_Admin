package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.EmployeeDetails;
import java.util.List;


public interface EmployeeRepo extends JpaRepository<EmployeeDetails, Integer> {

	public EmployeeDetails findByEmployeeEmail(String employeeEmail);

}
