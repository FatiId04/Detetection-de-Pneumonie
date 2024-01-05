package com.example.project.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.project.model.Hospital;

import java.util.Optional;

public interface HospitalRepository extends MongoRepository<Hospital,String> {
    Optional<Hospital> findByHospitalNameAndPassword(String hospitalName, String password);

}