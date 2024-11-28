package com.medica_helthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medica_helthcare.model.InsuranceVerification;

@Repository
public interface InsuranceVerificationRepository extends JpaRepository<InsuranceVerification, Long> {
}
