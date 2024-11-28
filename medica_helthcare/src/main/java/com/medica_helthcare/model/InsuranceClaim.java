package com.medica_helthcare.model;

import java.time.LocalDate;
import java.util.Arrays;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class InsuranceClaim {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientid;
    private String patientName;
    private String policyNumber;
    private String status;
    @Lob
    private byte[] pdfFile;
    
    private LocalDate submissionDate;

   
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public byte[] getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(byte[] pdfFile) {
        this.pdfFile = pdfFile;
    }
	public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }
	@Override
	public String toString() {
		return "InsuranceClaim [patientid=" + patientid + ", patientName=" + patientName + ", policyNumber=" + policyNumber
				+ ", status=" + status + ", pdfFile=" + Arrays.toString(pdfFile) + ", submissionDate=" + submissionDate
				+ "]";
	}
	
    
}
