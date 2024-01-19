package com.planotech.events.controller;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.http.HttpHeaders;

import javax.imageio.ImageIO;

 
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.planotech.events.dto.UserDetails;
import com.planotech.events.repository.UserDetailsRepository;

import ch.qos.logback.core.model.Model;

@Controller
public class CommonController
{
	@GetMapping("/")
	public String loadHome()
	{
		return "Home";
	}
	

	@GetMapping("/aboutus")
	public String loadAbout()
	{
		return "Aboutus";
	}
	
}
