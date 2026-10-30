package com.registro.entrenamientos.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder 
public record EjercicioRutinaResponseDto(

    Long idEjercicioRutina,
    DiaRutinaResponseDto diaRutina,
    EjercicioResponseDto ejercicio,
    int orden,
    Integer series,
    Integer repeticionesMin,
    Integer repeticionesMax,
    Double descansoMin
    
) {}
