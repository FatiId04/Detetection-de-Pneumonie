package com.example.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.model.Patient;
import com.example.project.repository.HistoRepository;

@Service
public class HistoService {
    private final HistoRepository histoRepository;

    @Autowired
    public HistoService(HistoRepository histoRepository) {
        this.histoRepository = histoRepository;
    }

    public List<Patient> getHistoryByHospitalName(String hospitalName) {
        return histoRepository.findPatientsByHospitalName(hospitalName);
    }

}
