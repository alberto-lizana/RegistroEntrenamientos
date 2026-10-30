package com.registro.entrenamientos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "ejercicio_rutina",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_ejercicio_dia",
            columnNames = {"ejercicio_id", "dia_rutina_id"}
        ),
        @UniqueConstraint(
            name = "uk_dia_orden",
            columnNames = {"dia_rutina_id", "orden"}
        )
    }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EjercicioRutina {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dia_rutina_id", nullable = false)
    private DiaRutina diaRutina;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ejercicio_id", nullable = false)
    private Ejercicio ejercicio;

    @Column(nullable = false)
    private int orden;

    private Integer series;

    private Integer repeticionesMin;

    private Integer repeticionesMax;

    @Column(nullable = false)
    private Double descansoMin;

}

/*
    Ejercicio especifico de un día de una rutina y además estos cuentan con un orden estrategico 
    de ejecución para maximizar el rendimeinto y gestionar la fatiga.
*/
