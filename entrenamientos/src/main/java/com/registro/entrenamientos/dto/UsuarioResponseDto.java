package com.registro.entrenamientos.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Builder
public record UsuarioResponseDto (   

    Long idUsuario,
    String nombre,
    String appat,
    String apmat,
    LocalDate fechaNacimiento,
    String email,
    boolean isActive,
    String plan,
    String nombreRol,
    String sexo

) {}