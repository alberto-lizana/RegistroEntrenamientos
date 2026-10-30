package com.registro.entrenamientos.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

@JsonInclude 
@Builder 
public record RutinaCompletaNativaResponseDto(

    String rutinaTitulo,
    String nombreTipoRutina,
    List<EjercicioRutinaNativaResponseDto> ejercicios


) {}
