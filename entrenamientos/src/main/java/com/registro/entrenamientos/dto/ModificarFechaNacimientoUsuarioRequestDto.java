package com.registro.entrenamientos.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record ModificarFechaNacimientoUsuarioRequestDto (

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual")
    LocalDate fechaNacimiento
    
) {}
