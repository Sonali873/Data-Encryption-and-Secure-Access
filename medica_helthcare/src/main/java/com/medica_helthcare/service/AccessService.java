package com.medica_helthcare.service;

import java.io.File;
import java.io.IOException;

import javax.crypto.SecretKey;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.medica_helthcare.util.EncryptionUtil;
@Service
public class AccessService {

	
	public String encryptDocument(MultipartFile file) throws Exception {
		final SecretKey secretKey = EncryptionUtil.generateKey();

	    // Define a directory to store uploaded and encrypted files
	    String uploadDir = "C:/uploads/";
	    File uploadDirectory = new File(uploadDir);
	    if (!uploadDirectory.exists()) {
	        uploadDirectory.mkdirs(); // Create the directory if it doesn't exist
	    }

	    // Save the uploaded file
	    File inputFile = new File(uploadDir + "uploaded-" + file.getOriginalFilename());
	    file.transferTo(inputFile);

	    // Save the encrypted file in the same directory
	    File encryptedFile = new File(uploadDir + "encrypted-" + file.getOriginalFilename());
	    EncryptionUtil.encryptFile(secretKey, inputFile, encryptedFile);

	    System.out.println("File encrypted successfully!"); // Log success
	    return "Document encrypted successfully!";
	}

}
