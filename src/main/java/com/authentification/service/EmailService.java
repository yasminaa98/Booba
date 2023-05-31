package com.authentification.service;

import com.authentification.entities.PasswordResetToken;
import com.authentification.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    ResourceLoader resourceLoader ;

    public void sendPasswordResetEmail(User user, PasswordResetToken token, String resetUrl) throws MessagingException, IOException, IOException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(user.getEmail());
        helper.setSubject("Reset your password");

        // Read the contents of the email template from the resources folder
        Resource resource = resourceLoader.getResource("classpath:reset_password_template.html");
        String emailContent = new String(Files.readAllBytes(resource.getFile().toPath()));

        // Replace placeholders in the email template with actual values
        emailContent = emailContent.replace("{username}", user.getUsername());
        emailContent = emailContent.replace("{resetUrl}", resetUrl);

        helper.setText(emailContent, true);

        mailSender.send(message);
    }
}