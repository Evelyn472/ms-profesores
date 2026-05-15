package com.colegio.msprofesores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients; // Importar esto

@SpringBootApplication
@EnableFeignClients // <--- ¡Añade esto aquí!
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}