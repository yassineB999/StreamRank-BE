package com.streamrank.infrastructure.utility;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Component
public class Utility {
    @Value("${spring.application.name}")
    public String applicationName;

    @Value("${spring.application.logoURL}")
    public String applicationLogoUrl;

    @Value("${spring.application.supportMail}")
    public String applicationSupportEmail;

    @Value("${spring.application.domainURL}")
    public String applicationDomainUrl;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String text, boolean isHtml) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(applicationSupportEmail);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, isHtml);

        javaMailSender.send(message);
    }

    public static String generateRandomAlphanumeric(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }
}