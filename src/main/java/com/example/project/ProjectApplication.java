package com.example.project;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
		File file = new File("C:/Users/fatim/Downloads");
		System.out.println("Espace disque disponible : " + file.getFreeSpace() + " bytes");

		
	}

}
