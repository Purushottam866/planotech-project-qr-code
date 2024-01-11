package com.planotech.events.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.planotech.events.dao.UserDetailsDao;
import com.planotech.events.dto.UserDetails;

@Service
public class UserDetailsService 
{
	@Autowired
	UserDetailsDao detailsDao;
	
	public String signup(UserDetails userDetails,ModelMap map)
	{
		List<UserDetails> exuser = detailsDao.findByEmailAndMobile(userDetails.getEmail(), userDetails.getMobile());
		System.out.println(exuser);
		if(!exuser.isEmpty())
		{
			map.put("fail", "Account Already Exist");
			System.out.println("fail");
			return "Signup";
		}
		else
		{
			detailsDao.save(userDetails);
			System.out.println("pass");
			return "Login";
		}
	}
	
}
