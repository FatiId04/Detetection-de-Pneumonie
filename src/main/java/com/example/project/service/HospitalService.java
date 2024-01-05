package com.example.project.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.project.model.Hospital;
import com.example.project.repository.HospitalRepository;

import java.util.Optional;

@Service
public class HospitalService  {

    @Autowired
    private HospitalRepository hospitalRepository;

    public ResponseEntity<Object> sauvgarder(Hospital hospital) {
        try{
            hospitalRepository.save(hospital);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Erreur");
        }
        return ResponseEntity.ok().body(hospital);
    }

    public ResponseEntity<Object> verifier(Hospital hospital) {
        String hospitalName= hospital.getHospitalName();
        String password= hospital.getPassword();
        Optional<Hospital> hospitalFound=hospitalRepository.findByHospitalNameAndPassword(hospitalName, password);
        if(hospitalFound.isEmpty()){
            return ResponseEntity.ok().body("not found");
        }
        return ResponseEntity.ok().body("found");
    }
}