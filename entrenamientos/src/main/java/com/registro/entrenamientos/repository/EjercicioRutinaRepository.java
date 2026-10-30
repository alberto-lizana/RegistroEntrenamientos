package com.registro.entrenamientos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.registro.entrenamientos.dto.RutinaCompletaResponseDto;
import com.registro.entrenamientos.model.EjercicioRutina;

@Repository 
public interface EjercicioRutinaRepository extends JpaRepository<EjercicioRutina, Long> {

    @Query
    ("""
        SELECT new com.registro.entrenamientos.dto.RutinaCompletaResponseDto 
        (
            r.titulo,
            tr.nombre,
            tr.descripcion,
            tr.diasPorSemana,
            CONCAT(dr.titulo, ' : ', '(', dr.nombre,')'),
            dr.orden,
            e.nombre,
            er.orden,
            er.series,
            er.repeticionesMin,
            er.repeticionesMax,
            er.descansoMin
        )
        FROM EjercicioRutina er
        JOIN er.diaRutina dr
        JOIN er.ejercicio e
        JOIN dr.rutina r
        JOIN r.tipoRutina tr
        ORDER BY r.id, dr.orden, er.orden
        """)
    List<RutinaCompletaResponseDto> obtenerTodos();

    @Query
    ("""
        SELECT new com.registro.entrenamientos.dto.RutinaCompletaResponseDto
        (
            r.titulo,
            tr.nombre,
            tr.descripcion,
            tr.diasPorSemana,
            CONCAT(dr.titulo, ' : (', dr.nombre, ')'),
            dr.orden,
            e.nombre,
            er.orden,
            er.series,
            er.repeticionesMin,
            er.repeticionesMax,
            er.descansoMin
        )
        FROM EjercicioRutina er
        JOIN er.diaRutina dr
        JOIN er.ejercicio e
        JOIN dr.rutina r
        JOIN r.tipoRutina tr
        WHERE dr.id = :idDiaRutina
        ORDER BY er.orden
    """)
    List<RutinaCompletaResponseDto> ejerciciosDelDia(@Param("idDiaRutina") Long idDiaRutina);


    @Query
    ("""
        SELECT er 
        FROM EjercicioRutina er
        JOIN er.diaRutina dr
        JOIN er.ejercicio e
        JOIN dr.rutina r
        JOIN r.tipoRutina tr
        WHERE dr.id = :idDiaRutina AND r.id = :idRutina
        ORDER BY er.orden
    """)
    List<EjercicioRutina> ejerciciosDelDiaJerarquizado(
        @Param("idDiaRutina") Long idDiaRutina,  
        @Param("idRutina") Long idRutina)
    ;

}
