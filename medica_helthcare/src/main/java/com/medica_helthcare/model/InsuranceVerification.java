package com.medica_helthcare.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class InsuranceVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientid;
    private String policyNumber;
    private String patientName;
    private boolean coverageStatus;
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public boolean isCoverageStatus() {
		return coverageStatus;
	}
	public void setCoverageStatus(boolean coverageStatus) {
		this.coverageStatus = coverageStatus;
	}

    // Getters and Setters
}
