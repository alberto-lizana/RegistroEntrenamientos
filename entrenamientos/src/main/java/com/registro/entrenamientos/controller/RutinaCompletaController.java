package com.registro.entrenamientos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.registro.entrenamientos.dto.RutinaCompletaNativaResponseDto;
import com.registro.entrenamientos.dto.RutinaCompletaResponseDto;
import com.registro.entrenamientos.service.RutinaCompletaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
@RestController 
@RequestMapping("/rutina-completa")
public class RutinaCompletaController {

    private final RutinaCompletaService rutinaCompletaService;

    public RutinaCompletaController(RutinaCompletaService rutinaCompletaService){
        this.rutinaCompletaService = rutinaCompletaService;
    }

    @GetMapping("/obtener-todas")
    ResponseEntity<List<RutinaCompletaResponseDto>> obtenerTodasRutinaCompleta(){
        log.info("Solicitud para obtener todas las rutinas completas en formato plano");
        return ResponseEntity.ok().body(rutinaCompletaService.obtenerTodasRutinaCompleta());
    }

    @GetMapping("/obtener-{id}")
    ResponseEntity<List<RutinaCompletaResponseDto>> obtenerRutinaCompletaPorId(@PathVariable Long id){
        log.info("Solicitud para obtener todos los registors asociados a una rutina en formato plano");
        return ResponseEntity.ok().body(rutinaCompletaService.obtenerRutinaCompletaPorId(id));
    }

    @GetMapping("/obtener-queryNativa-{idRutina}")
    ResponseEntity<RutinaCompletaNativaResponseDto> obtenerPorRutinaId(@PathVariable Long idRutina){
        log.info("Solicitud para obtener una rutina especifica mediante o a través de su identificador único con query nativa");
        return ResponseEntity.ok().body(rutinaCompletaService.obtenerPorRutinaId(idRutina));
    }

}