package com.planotech.events.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Component
@Data
@Entity
public class UserDetails 
{
	@Id
	@GeneratedValue(generator = "slno")
	@SequenceGenerator(initialValue = 101, allocationSize = 1, sequenceName = "slno", name = "slno")
	private int id;
	
	@NotEmpty(message = "This is mandatory")
	private String name;
	
	@NotNull(message = "This is mandatory")
	@DecimalMin(value = "6000000000",message = "Enter proper Mobile Number")
	@DecimalMax(value = "9999999999",message = "Enter proper Mobile Number")
	private long mobile;
	
	@NotEmpty(message = "This is mandatory")
	private String gender;
	
	@NotEmpty(message = "This is mandatory")
	@Email(message = "Enter Proper Format")
	private String email;
	
	@NotEmpty(message = "This is mandatory")
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "*Should Match Pattern(Min 8 characters ,UpperCase,LowerCase,Numbers&Special Characters)")
	private String password;
	
	@NotEmpty(message = "This is mandatory")
	private String company;
	
	@NotEmpty(message = "This is mandatory")
	private String designation;
	
	private String qrcode;	
	
}
