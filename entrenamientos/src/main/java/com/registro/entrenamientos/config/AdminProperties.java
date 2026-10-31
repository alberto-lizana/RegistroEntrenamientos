package com.registro.entrenamientos.config;

import java.time.LocalDate;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.registro.entrenamientos.enums.PlanEnum;
import com.registro.entrenamientos.enums.SexoEnum;

@ConfigurationProperties(prefix = "app.admin")
public record AdminProperties(

    String nombre,
    String appat,
    String apmat,
    LocalDate fechaNacimiento,
    String email,
    String password,
    PlanEnum plan,
    SexoEnum sexo
    
) {}
