package com.example.aluracursos.forohub.ProyectoForoHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {
		"com.example.aluracursos.forohub.ProyectoForoHub.dominio.topico",
		"com.example.aluracursos.forohub.ProyectoForoHub.dominio.usuario"})
public class ProyectoForoHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoForoHubApplication.class, args);
	}

}
