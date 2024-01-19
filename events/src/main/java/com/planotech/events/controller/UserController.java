package com.planotech.events.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.planotech.events.dao.UserDetailsDao;
import com.planotech.events.dto.UserDetails;
import com.planotech.events.service.UserDetailsService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController
{
	@Autowired
	UserDetails userDetails;

	@Autowired
	UserDetailsService detailsService;

	@GetMapping("/signup")
	public String loadignup(UserDetails userDetails, ModelMap map) {
		return "Signup";
	}

	@PostMapping(value = "/signup", produces = MediaType.IMAGE_PNG_VALUE)
	public String signup(@Valid UserDetails userDetails, BindingResult result, ModelMap map) throws Exception {
		if (result.hasErrors()) {
			return "Signup.html";
		}
		return detailsService.signup(userDetails, map);
	}
}
