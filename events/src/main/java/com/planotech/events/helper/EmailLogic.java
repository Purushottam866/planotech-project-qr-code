package com.planotech.events.helper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import com.planotech.events.dto.UserDetails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service
public class EmailLogic 
{
	@Autowired
	JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
    private String mailUsername;

	private final TemplateEngine templateEngine;

	public EmailLogic(JavaMailSender mailSender, TemplateEngine templateEngine) {
		this.mailSender = mailSender;
		this.templateEngine = templateEngine;
	}

	public void sendEmail(UserDetails user, String path) {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = null;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setFrom(mailUsername, "Planotech Group of Company");
			helper.setTo(user.getEmail());
			helper.setSubject("Welcome to Planotech Group of Company");
			
			// Read the image file as bytes
			byte[] imageBytes = Files.readAllBytes(Paths.get(path));

			// Set the HTML content with inline Base64-encoded image
			String htmlContent = "<div class=\"name\"><h1>Hello "+user.getName()+"</h1></div>\r\n"
					+ "			<h2>Welcome to Planotech Group of Companies</h2>\r\n"
					+ "			<ul type=\"none\">\r\n"
					+ "				<li>\r\n"
					+ "					<strong>Planotech Events and Marketing (OPC) Pvt Ltd:</strong>Crafting\r\n"
					+ "					unforgettable events with precision and creativity.\r\n"
					+ "				</li>\r\n"
					+ "				<li>\r\n"
					+ "					<strong>Extent Digital Marketing & Communications: </strong>Pioneering\r\n"
					+ "					digital strategies to elevate your brand in the digital realm\r\n"
					+ "				</li>\r\n"
					+ "				<li>\r\n"
					+ "					<strong>D Viswa Karunasagara Dharma Foundation:</strong>Dedicated to\r\n"
					+ "					enriching lives and nurturing sustainable communities\r\n"
					+ "				</li>\r\n"
					+ "				<li>\r\n"
					+ "					<strong>Planotech Media House:</strong>Producing captivating content\r\n"
					+ "					that resonates and inspires.\r\n"
					+ "				</li>\r\n"
					+ "			</ul>\r\n"
					+ "			<p id=\"p1\">\r\n"
					+ "				We extend our sincerest gratitude to you for taking the time to generate\r\n"
					+ "				the QR Code. It is with great pleasure that we confirm your attendance.\r\n"
					+ "			</p>\r\n"
					+ "			<p id=\"p2\">\r\n"
					+ "				You may like to keep this mail safe, so you can take a printout or on\r\n"
					+ "				mobile the badge anytime,Your Reference QRCode is in attachments\r\n"
					+ "			</p>\r\n"
					+ "			<strong>Best Regards</strong><br>\r\n"
					+ "			<strong>Team Planotech</strong>";

			helper.setText(htmlContent, true);
			// Attach the image as a ByteArrayResource with a specified filename
			ByteArrayResource imageResource = new ByteArrayResource(imageBytes);
			helper.addInline("qrcode", imageResource, "image/png; name=qr_code.png");

		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mailSender.send(message);

	}
}
