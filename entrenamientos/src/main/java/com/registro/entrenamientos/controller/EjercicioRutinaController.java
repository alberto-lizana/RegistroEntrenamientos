package com.registro.entrenamientos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.registro.entrenamientos.dto.EjercicioRutinaResponseDto;
import com.registro.entrenamientos.dto.RutinaCompletaResponseDto;
import com.registro.entrenamientos.service.EjercicioRutinaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
@RestController 
@RequestMapping("/ejercicio-rutina")
public class EjercicioRutinaController {

    private final EjercicioRutinaService ejercicioRutinaService;

    public EjercicioRutinaController(EjercicioRutinaService ejercicioRutinaService){
        this.ejercicioRutinaService = ejercicioRutinaService;
    }

    @GetMapping("/obtener-todos")
    ResponseEntity<List<RutinaCompletaResponseDto>> obtener(){
        log.info("Solicitud recibida para obtener todas las rutinas de forma completa");
        return ResponseEntity.ok().body(ejercicioRutinaService.obtenerTodos());
    }

    /*
        D6:  1 - 7   =  Push - Pull - Legs 
        D5:  8 - 14  =  Push - Pull - Legs - Push - Density
        D4:  15 - 21 =  Torso - Pierna
        D3:  22 - 28 =  Full Body
    */
    @GetMapping("/del-dia-{id}") 
    ResponseEntity<List<RutinaCompletaResponseDto>> ejerciciosDelDia(@PathVariable Long id){
        log.info("Solicitud recibida para obtener los ejercicos de un día especifico");
        return ResponseEntity.ok().body(ejercicioRutinaService.ejerciciosDelDia(id));
    }


    @GetMapping("/del-diajer-dia-{idDia}-rutina-{idRutina}") 
    ResponseEntity<List<EjercicioRutinaResponseDto>> ejerciciosDelDiaJerarquizado(@PathVariable Long idDia, @PathVariable Long idRutina){
        log.info("Solicitud recibida para obtener los ejercicos de un día y rutina específica");
        return ResponseEntity.ok().body(ejercicioRutinaService.ejerciciosDelDiaJerarquizado(idDia, idRutina));
    }

}
