package com.example.project.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.project.model.Patient;

public interface PatientRepository  extends MongoRepository<Patient, String>  {

}
