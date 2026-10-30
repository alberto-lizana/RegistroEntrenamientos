package com.registro.entrenamientos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "dia_rutina")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DiaRutina {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 125)
    private String titulo;

    @Column(nullable = false, length = 125)
    private String nombre;

    @Column(nullable = false)
    private int orden;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rutina_id", nullable = false)
    private Rutina rutina;
}

/*
    Dependiendo de tipo de rutina este contará con distintos dias cada dia tiene una distribucion de ejercicios 
    dependiendo del tipo de rutina seleccionada y también tienen un orden dado que trabajar un dia un musculo y al otro dia el mismo musculo no tiene sentido 
*/
