package com.medica_helthcare.controller;

import org.springframework.web.bind.annotation.*;

import com.medica_helthcare.model.InsuranceVerification;
import com.medica_helthcare.service.InsuranceService;

import java.util.List;

@RestController
@RequestMapping("/api/insurance")
public class InsuranceController {

    private final InsuranceService service;

    public InsuranceController(InsuranceService service) {
        this.service = service;
    }

    @GetMapping("/getInsurance")
    public List<InsuranceVerification> getAll() {
        return service.getAllVerifications();
    }

    @PostMapping("/addInsurance")
    public InsuranceVerification add(@RequestBody InsuranceVerification verification) {
        return service.addVerification(verification);
    }
}
