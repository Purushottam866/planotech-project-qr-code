package com.planotech.events.helper;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.planotech.events.dto.UserDetails;

@Service
public class GenerateQr 
{
	private static int imgid;

	public String generateQRCodeImage(UserDetails user) throws Exception {
		String qrCodeData = "Name: " + user.getName() + "\n Mobile: " + user.getMobile() + " \nGender: "
				+ user.getGender() + " \nEmail: " + user.getEmail() + "\nCompany: " + user.getCompany()
				+ "\nDesignation: " + user.getDesignation() + "\n";

		BufferedImage qrCodeImage = generateQRCodeImage(qrCodeData);
		String folderName = "src/main/resources/static/qrCodes";
		File qrCodeFolder = new File(folderName);
		if (!qrCodeFolder.exists()) {
			qrCodeFolder.mkdirs();
		}
		imgid++;
		File imageFile = new File(qrCodeFolder, user.getName() + imgid + "qrcode.png");
		ImageIO.write(qrCodeImage, "png", imageFile);
		return imageFile.getPath();
	}

	public BufferedImage generateQRCodeImage(String qrCodeData) throws Exception {
		int width = 300;
		int height = 300;

		BitMatrix bitMatrix = new MultiFormatWriter().encode(qrCodeData, BarcodeFormat.QR_CODE, width, height);
		return MatrixToImageWriter.toBufferedImage(bitMatrix);
	}
}
