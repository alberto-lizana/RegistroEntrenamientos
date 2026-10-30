package com.registro.entrenamientos.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder 
public record RutinaCompletaResponseDto(

    String tituloRutina,
    String tipoRutina,
    String descripcionTipo,
    Integer diasPorSemana,
    String nombreDia,
    Integer ordenDia,
    String nombreEjercicio,
    Integer ordenEjercicio,
    Integer series,
    Integer repeticionesMin,
    Integer repeticionesMax,
    Double descansoMin
    
) {}
