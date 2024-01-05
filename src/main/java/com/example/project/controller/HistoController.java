package com.example.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.model.Patient;
import com.example.project.service.HistoService;


@RestController
@RequestMapping("/histo")
public class HistoController {

	    private final HistoService histoService;

	    @Autowired
	    public HistoController(HistoService histoService) {
	        this.histoService = histoService;
	    }

	    @GetMapping("/history")
	    public List<Patient> getHistoryByHospitalName(@RequestParam String hospitalName) {
	        return histoService.getHistoryByHospitalName(hospitalName);
	    }
	}

