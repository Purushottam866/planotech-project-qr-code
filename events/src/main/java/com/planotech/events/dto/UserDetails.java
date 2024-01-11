package com.planotech.events.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Component
@Data
@Entity
public class UserDetails 
{
	@Id
	@GeneratedValue(generator = "slno")
	private int id;
	private String name;
	private long mobile;
	private String gender;
	private String email;
	private String password;
	private String company;
	private String designation;
	
}
