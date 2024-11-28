package com.medica_helthcare.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.medica_helthcare.model.InsuranceClaim;
import com.medica_helthcare.repository.InsuranceClaimRepository;

import java.io.IOException;
import java.util.List;

@Service
public class ClaimService {

    private final InsuranceClaimRepository repository;

    public ClaimService(InsuranceClaimRepository repository) {
        this.repository = repository;
    }

    public List<InsuranceClaim> getAllClaims() {
        return repository.findAll();
    }

    public InsuranceClaim addClaim(InsuranceClaim claim) {
        claim.setStatus("Submitted");
        return repository.save(claim);
    }

    public InsuranceClaim updateClaimStatus(Long id, String status) {
    	InsuranceClaim claim = repository.findById(id).orElseThrow(() -> new RuntimeException("Claim not found"));
        claim.setStatus(status);
        return repository.save(claim);
    }
    
    public InsuranceClaim addClaimWithPdf(String patientName, String policyNumber, MultipartFile pdfFile) throws IOException {
    	InsuranceClaim claim = new InsuranceClaim();
        claim.setPatientName(patientName);
        claim.setPolicyNumber(policyNumber);
        claim.setStatus("Submitted");
        claim.setPdfFile(pdfFile.getBytes());
        return repository.save(claim);
    }

    public byte[] getPdfFile(Long id) {
    	InsuranceClaim claim = repository.findById(id).orElseThrow(() -> new RuntimeException("Claim not found"));
        return claim.getPdfFile();
    }
}
