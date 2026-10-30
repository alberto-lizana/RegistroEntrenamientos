package com.registro.entrenamientos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.registro.entrenamientos.dto.RutinaResponseDto;
import com.registro.entrenamientos.service.RutinaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
@RestController 
@RequestMapping("/rutina")
public class RutinaController {

    private final RutinaService rutinaService;

    public RutinaController(RutinaService rutinaService){
        this.rutinaService = rutinaService;
    }

    @GetMapping("/obtener-todos")
    ResponseEntity<List<RutinaResponseDto>> obtener(){
        log.info("Solicitud para obtener todos los registors de rutinas creadas");
        return ResponseEntity.ok().body(rutinaService.obtenerTodas());
    }

    @GetMapping("/obtener-{id}")
    ResponseEntity<RutinaResponseDto> obtenerPorId(@PathVariable Long id){
        log.info("Solicitud para obtener una rutina especifica mediante o a través de su identificador único");
        return ResponseEntity.ok().body(rutinaService.obtenerPorId(id));
    }
    
}
