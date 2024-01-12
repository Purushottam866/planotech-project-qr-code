package com.planotech.events.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.planotech.events.dto.UserDetails;
import com.planotech.events.repository.UserDetailsRepository;

@Component
public class UserDetailsDao 
{
	@Autowired
	UserDetailsRepository detailsRepository;
	
	
	public List<UserDetails> findByEmailOrMobile(String email,long mobile)
	{
		return detailsRepository.findByEmailOrMobile(email,mobile);
	}
	
	public void save(UserDetails userDetails)
	{
		detailsRepository.save(userDetails);
	}
}
