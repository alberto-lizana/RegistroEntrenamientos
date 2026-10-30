package com.registro.entrenamientos.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder 
public record RutinaResponseDto(

    Long idRutina,
    String titulo,
    TipoRutinaResponseDto tipoRutina

) {}
