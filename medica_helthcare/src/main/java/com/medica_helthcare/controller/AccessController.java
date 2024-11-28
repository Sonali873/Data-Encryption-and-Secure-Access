package com.medica_helthcare.controller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import javax.crypto.SecretKey;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.medica_helthcare.service.AccessService;
import com.medica_helthcare.service.OTPService;
import com.medica_helthcare.util.EncryptionUtil;

@RestController
@RequestMapping("/api")
public class AccessController {
	@Autowired
	private  AccessService accessservice;
	
	@Autowired
    private OTPService otpService;
	
	@GetMapping("/sayhello")
	public String Hello() throws IOException {
		File file1  = new File("myFile.txt");
        file1.createNewFile();
        System.out.println(file1);
		return "Welcome to controller";
	}
	
	@PostMapping("/encrypt")
    public ResponseEntity<String> encryptDocument(@RequestParam("file") MultipartFile file) throws Exception {
		System.out.println(file);
        return ResponseEntity.ok(accessservice.encryptDocument(file)) ;
    }
	
	 @PostMapping("/access")
	    public ResponseEntity<String> accessDocument(@RequestParam String email, @RequestParam String otp,
	    		@RequestParam("file") MultipartFile filename) throws Exception {
		 if (otpService.verifyOTP(email, otp)) {
		        // Save the uploaded file to the server
		        String uploadDir = "C:\\uploads\\";
		        File uploadDirFile = new File(uploadDir);
		        if (!uploadDirFile.exists()) {
		            uploadDirFile.mkdirs(); // Create the directory if it doesn't exist
		        }

		        // Save the uploaded file
		        String originalFilename = filename.getOriginalFilename();
		        if (originalFilename == null || originalFilename.isEmpty()) {
		            return ResponseEntity.badRequest().body("Invalid file provided!");
		        }

		        File uploadedFile = new File(uploadDir, originalFilename);
		        filename.transferTo(uploadedFile);

		        // Prepare paths for encryption and decryption
		        File encryptedFile = new File(uploadDir, "encrypted-" + originalFilename);
		        File decryptedFile = new File(uploadDir, "decrypted-" + originalFilename);

		        // Proceed with decryption
		        final SecretKey secretKey = EncryptionUtil.generateKey();
		        EncryptionUtil.decryptFile(secretKey, uploadedFile, decryptedFile);

		        return ResponseEntity.ok("Document decrypted successfully! File saved at: " + decryptedFile.getAbsolutePath());
		    } else {
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP!");
		    }
	    }
	 
	 @PostMapping("/send-otp")
	    public String sendOtp(@RequestParam String email) {
	
	        otpService.generateAndSendOTP(email);
	        return "OTP sent to email: " + email;
	    }
}
