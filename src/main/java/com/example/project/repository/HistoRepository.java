package com.example.project.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.project.model.Patient;

public interface HistoRepository extends MongoRepository<Patient, String> {
	 List<Patient> findPatientsByHospitalName(String hospitalName);
}
