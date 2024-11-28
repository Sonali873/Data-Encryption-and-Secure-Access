package com.medica_helthcare.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.medica_helthcare.model.InsuranceClaim;
import com.medica_helthcare.service.ClaimService;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    private final ClaimService service;

    public ClaimController(ClaimService service) {
        this.service = service;
    }

    @GetMapping("/getClam")
    public List<InsuranceClaim> getAll() {
        return service.getAllClaims();
    }

    @PostMapping("/addClam")
    public InsuranceClaim add(@RequestBody InsuranceClaim claim) {
        return service.addClaim(claim);
    }

    @PutMapping("/{id}")
    public InsuranceClaim updateStatus(@PathVariable Long id, @RequestParam String status) {
        return service.updateClaimStatus(id, status);
    }
    
    @PostMapping(value = "/claimWithPdf",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public InsuranceClaim addClaimWithPdf(
            @RequestParam String patientName,
            @RequestParam String policyNumber,
            @RequestParam MultipartFile pdfFile) throws IOException {
        return service.addClaimWithPdf(patientName, policyNumber, pdfFile);
    }

}
