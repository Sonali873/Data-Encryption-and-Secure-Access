package com.medica_helthcare.controller;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medica_helthcare.model.Patient;
import com.medica_helthcare.service.PatientService;
import com.sun.tools.javac.util.List;
import org.springframework.http.HttpHeaders;
import lombok.var;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping(value = "/register", consumes = "multipart/form-data")
    public ResponseEntity<Patient> registerPatient(
            @RequestPart("patient") String patientJson,
            @RequestPart("insuranceFile") MultipartFile insuranceFile
    ) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Deserialize JSON into a Patient object
        Patient patient = objectMapper.readValue(patientJson, Patient.class);

        // File handling
        if (insuranceFile != null && !insuranceFile.isEmpty()) {
            patient.setInsuranceFile(insuranceFile.getBytes());
            patient.setFileName(insuranceFile.getOriginalFilename());
            patient.setFileExtension(getFileExtension(insuranceFile.getOriginalFilename()));
        }

        Patient registeredPatient = patientService.registerPatient(patient);
        return ResponseEntity.ok(registeredPatient);
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.lastIndexOf('.') == -1) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<Object> getAllPatient(){
    	return ResponseEntity.ok(patientService.getAllPatients());
    }
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        // Dummy example: replace with your logic to fetch file data
        byte[] fileData = "Dummy PDF content".getBytes();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "insurance_details.pdf");
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_PDF);

        return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
    }
}
