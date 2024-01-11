package com.planotech.events.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.planotech.events.dto.UserDetails;
import com.planotech.events.service.UserDetailsService;

@Controller
@RequestMapping("/user")
public class UserController
{
	@Autowired
	UserDetails userDetails;
	
	@Autowired
	UserDetailsService detailsService;
	
	@GetMapping("/signup")
	public String loadignup(UserDetails userDetails,ModelMap map)
	{
		return "Signup";
	}
	
	@PostMapping("/signup")
	public String signup(UserDetails userDetails,ModelMap map)
	{
		return detailsService.signup(userDetails, map);
	}
}
