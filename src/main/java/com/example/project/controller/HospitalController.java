package com.example.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.model.Hospital;
import com.example.project.service.HospitalService;

@RestController
@RequestMapping("/auth")
public class HospitalController {
    @Autowired
    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService= hospitalService;
    }

    @PostMapping(value = "/signin")
    public ResponseEntity<Object> authentifier(@RequestBody Hospital hospital){
        return hospitalService.sauvgarder(hospital);
    }
    @PostMapping(value = "/login")
    public ResponseEntity<Object> verifier(@RequestBody Hospital hospital){
        return hospitalService.verifier(hospital);

    }
    
  }
