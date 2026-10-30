package com.registro.entrenamientos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.registro.entrenamientos.dto.DiaRutinaResponseDto;
import com.registro.entrenamientos.service.DiaRutinaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
@RestController 
@RequestMapping("/dia-rutina")
public class DiaRutinaController {

    private final DiaRutinaService diaRutinaService;

    public DiaRutinaController(DiaRutinaService diaRutinaService){
        this.diaRutinaService = diaRutinaService;
    }

    @GetMapping("/obtener-todos")
    ResponseEntity<List<DiaRutinaResponseDto>> obtener(){
        log.info("Solicitud recibida para obtener todos los registros de un dia rutina.");
        return ResponseEntity.ok().body(diaRutinaService.obtenerTodos());
    }

    @GetMapping("/obtener-{id}")
    ResponseEntity<DiaRutinaResponseDto> obtenerPorId(@PathVariable Long id){
        log.info("Solicitud recibida para obtener un registro específico de un dia rutina por id.");
        return ResponseEntity.ok().body(diaRutinaService.obtenerPorId(id));
    }
}
