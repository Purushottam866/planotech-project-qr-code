package com.planotech.events.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.google.zxing.WriterException;
import com.planotech.events.dao.UserDetailsDao;
import com.planotech.events.dto.UserDetails;
import com.planotech.events.helper.AES;
import com.planotech.events.helper.EmailLogic;
import com.planotech.events.helper.GenerateQr;


@Service
public class UserDetailsService 
{
	@Autowired
	UserDetailsDao detailsDao;

	@Autowired
	UserDetails userDetails;

	@Autowired
	GenerateQr generateQR;

	@Autowired
	EmailLogic emailLogic;

	public String signup(UserDetails userDetails, ModelMap map) throws Exception {
		List<UserDetails> exuser = detailsDao.findByEmailOrMobile(userDetails.getEmail(), userDetails.getMobile());

		if (!exuser.isEmpty()) {
			map.put("fail", "Account Already Exist with this details");
			return "Signup";
		} else
		{
			
			try {
				String qrcode = generateQR.generateQRCodeImage(userDetails);
				userDetails.setQrcode(qrcode);
			} catch (IOException | WriterException e) {
				e.printStackTrace();
			}
			
			userDetails.setPassword(AES.encrypt(userDetails.getPassword(), "123"));
			
			detailsDao.save(userDetails);
			String base64Image = encodeFileToBase64Binary(fetchQR(userDetails));
			emailLogic.sendEmail(userDetails, fetchQR(userDetails));
			map.put("user", userDetails);
			map.put("base64Image", base64Image);
			return "ConfirmPage";
		}
	}

	private String encodeFileToBase64Binary(String imagePath) throws IOException {
		byte[] bytes = Files.readAllBytes(Path.of(imagePath));
		return Base64.getEncoder().encodeToString(bytes);
	}

	public String fetchQR(UserDetails user) throws IOException {
		String path = null;
		if (user != null) {
			path = user.getQrcode();
		}

		return path;
	}
}
