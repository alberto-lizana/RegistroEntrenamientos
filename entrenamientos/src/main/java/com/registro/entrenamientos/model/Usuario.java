package com.registro.entrenamientos.model;

import com.registro.entrenamientos.enums.RolEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.registro.entrenamientos.enums.PlanEnum;
import com.registro.entrenamientos.enums.SexoEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String appat;

    @Column(nullable = true)
    private String apmat;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    private LocalDateTime fechaCreacion;

    @Column(unique = true, nullable = false)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;

    @Column (nullable = false)
    private Boolean isActive;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RolEnum rol;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlanEnum plan;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SexoEnum sexo;
    
    @PrePersist
    public void prePersist() {
        fechaCreacion = LocalDateTime.now();
        plan = PlanEnum.FREE;
        isActive = true;
        if (rol == null) {
            rol = RolEnum.USUARIO;
        }
    }
}