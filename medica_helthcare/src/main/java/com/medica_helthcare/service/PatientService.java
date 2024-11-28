package com.medica_helthcare.service;

import org.apache.el.stream.Optional;
import org.springframework.stereotype.Service;

import com.medica_helthcare.model.Patient;
import com.medica_helthcare.repository.PatientRepository;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient registerPatient(Patient patient) {
        return patientRepository.save(patient);
    }

	public Object getAllPatients() {
		// TODO Auto-generated method stub
		return patientRepository.findAll();
	}
	
	public byte[] downloadInsuranceFile(Long id) {
		 java.util.Optional<Patient> patientOptional = patientRepository.findById(id);
	        if (patientOptional.isEmpty()) {
	            throw new IllegalArgumentException("Patient not found");
	        }
	        return patientOptional.get().getInsuranceFile();
	}
	
	 private String getFileExtension(String fileName) {
	        return fileName.substring(fileName.lastIndexOf(".") + 1);
	    }
}
