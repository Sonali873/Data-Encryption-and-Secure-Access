package com.medica_helthcare.service;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class OTPService {

    private final ConcurrentHashMap<String, String> otpStorage = new ConcurrentHashMap<>();
    
    @Autowired
    private JavaMailSender mailSender;

    public String generateAndSendOTP(String email) {
        // Generate a random 6-digit OTP
       // String otp = RandomStringUtils.randomNumeric(6);
        String otp = String.valueOf(ThreadLocalRandom.current().nextInt(100000, 1000000));
        otpStorage.put(email, otp);
        System.out.println("OTP for " + email + ": " + otp);
        //System.out.println(otpStorage.get(email)+""+otpStorage.get(otp));

        // Send OTP to user
        SimpleMailMessage message = new SimpleMailMessage();
       
        message.setTo(email);
        
        message.setSubject("Your One-Time Password (OTP)");
        
        message.setText("Your OTP is: " + otp);
        mailSender.send(message);
        return otp;
    }

    public boolean verifyOTP(String email, String otp) {
    	 System.out.println(email);
    
        String storedOtp = otpStorage.get(email);
        System.out.println(otpStorage.get(email));
        if (storedOtp != null && storedOtp.equals(otp)) {
            otpStorage.remove(email); // Remove OTP after verification
            return true;
        }
        return false;
    }
}
