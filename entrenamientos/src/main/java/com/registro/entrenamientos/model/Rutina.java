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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "rutina")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor 
@Setter 
@Getter 
@Builder 
public class Rutina {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 125)
    private String titulo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_rutina_id", nullable = false)
    private TipoRutina tipoRutina;

}

/*
    Entidad pensada para establecer un titulo de rutina y el tipo de rutina que será ya sea de 3, 4, 5, 6 dias de entrenamiento a la semana 
    cada una de ellas tendrá una rutina definida y estática en fin la aplciaicon se define como registro de entrenamientos no gestion de rutinas
*/
