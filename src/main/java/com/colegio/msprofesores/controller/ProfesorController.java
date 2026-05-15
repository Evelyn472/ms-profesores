package com.colegio.msprofesores.controller;

import com.colegio.msprofesores.dto.ProfesorRequestDTO;
import com.colegio.msprofesores.dto.ProfesorResponseDTO;
import com.colegio.msprofesores.service.ProfesorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
@RequiredArgsConstructor
public class ProfesorController {

    private final ProfesorService profesorService;

    // Obtener todos los profesores: GET http://localhost:8083/api/profesores
    @GetMapping
    public ResponseEntity<List<ProfesorResponseDTO>> listarTodos() {
        return ResponseEntity.ok(profesorService.obtenerTodos());
    }

    // Obtener uno por ID: GET http://localhost:8083/api/profesores/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ProfesorResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(profesorService.obtenerPorId(id));
    }

    // Crear un profesor: POST http://localhost:8083/api/profesores
    @PostMapping
    public ResponseEntity<ProfesorResponseDTO> crear(@Valid @RequestBody ProfesorRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(profesorService.guardar(request));
    }
}