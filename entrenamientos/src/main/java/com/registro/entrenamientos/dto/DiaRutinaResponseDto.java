package com.registro.entrenamientos.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder 
public record DiaRutinaResponseDto(

    Long idDiaRutina,
    String titulo,
    String nombre,
    int orden,
    RutinaResponseDto rutina

) {}
