package com.medica_helthcare.service;

import org.springframework.stereotype.Service;

import com.medica_helthcare.model.InsuranceVerification;
import com.medica_helthcare.repository.InsuranceVerificationRepository;

import java.util.List;

@Service
public class InsuranceService {

    private final InsuranceVerificationRepository repository;

    public InsuranceService(InsuranceVerificationRepository repository) {
        this.repository = repository;
    }

    public List<InsuranceVerification> getAllVerifications() {
        return repository.findAll();
    }

    public InsuranceVerification addVerification(InsuranceVerification verification) {
        return repository.save(verification);
    }
}
