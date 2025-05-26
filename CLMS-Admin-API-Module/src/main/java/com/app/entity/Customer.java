package com.app.entity;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Lob;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Customer {
	
	private String customerName;
	private String userName;
	private String password;
	@DateTimeFormat
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	private Integer age;
	private String gender;
	private String state;
	private Long customerContactNumber;
	private Long customerAlternateNumber;
	private String customerEmailId;
	private String emailStatus;
	private String customerPermanentAddress;
	private String customerCity;
	private Integer customerPincode;
	private Long aadharNo;
	private String panCardNo;
	
	@Lob
	private byte[] profilePicture;
	
	private LoanEnquiry le;
	
}
//{
//	"customerName":"Raj",
//	"userName":"Raj01",
//	"password":"Raj@01",
//	"dateOfBirth":"02-05-2000",
//	"age":"24",
//	"gender":"Male",
//	"state":"Maharashtra",
//	"customerContactNumber":"6565733",
//	"customerAlternateNumber":"5668577",
//	"customerEmailId":"raj01@gmail.com",
//  "emailStatus":"NOT_VERIFIED"
//	"customerPermanentAddress":"xyz karve nagar",
//	"customerCity":"pune",
//	"customerPincode":"411052",
//	"le":{
//		"cibil":{}
//	}
//}

