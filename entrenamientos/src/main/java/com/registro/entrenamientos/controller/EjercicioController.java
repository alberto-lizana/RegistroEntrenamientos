package com.registro.entrenamientos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.registro.entrenamientos.dto.EjercicioResponseDto;
import com.registro.entrenamientos.service.EjercicioService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController 
@RequestMapping("/ejercicio")
public class EjercicioController {

    private final EjercicioService ejercicioService;

    public EjercicioController(EjercicioService ejercicioService){
        this.ejercicioService = ejercicioService;
    }

    @GetMapping("/obtener-todos")
    ResponseEntity<List<EjercicioResponseDto>> obtener(){
        log.info("Solicitud recibida para obtener todos los registros de ejercicios");
        return ResponseEntity.ok().body(ejercicioService.obtenerTodos());
    }

    @GetMapping("/obtener-{id}")
    ResponseEntity<EjercicioResponseDto> obtenerPorId(@PathVariable Long id){
        log.info("Solicitud recibida para obtener un registro de ejercicio a través se su identificador único");
        return ResponseEntity.ok().body(ejercicioService.obtenerPorId(id));
    }
}
