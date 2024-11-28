package com.medica_helthcare.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.medica_helthcare.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
