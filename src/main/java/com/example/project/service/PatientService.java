package com.example.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.example.project.model.Patient;
import com.example.project.repository.PatientRepository;

@Service
public class PatientService {
	@Autowired
	 private PatientRepository patientRepository;

	    public ResponseEntity<String> sauvgarder(Patient patient) {
	        try{
	            patientRepository.save(patient);
	        }catch (Exception e){
	        	e.printStackTrace();
	            return ResponseEntity.status(500).body("Erreur lors de l'enregistrement des données.");
	        }
	        return ResponseEntity.ok("Données enregistrées avec succès.");
	    }

}
