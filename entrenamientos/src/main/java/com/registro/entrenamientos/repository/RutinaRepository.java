package com.registro.entrenamientos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.registro.entrenamientos.dto.RutinaCompletaResponseDto;
import com.registro.entrenamientos.dto.projection.RutinaCompletaNativaProjection;
import com.registro.entrenamientos.model.Rutina;

@Repository 
public interface RutinaRepository extends JpaRepository<Rutina, Long> {

    @Query
    ("""
        SELECT r
        FROM Rutina r
        LEFT JOIN FETCH r.tipoRutina
    """)
    List<Rutina> obtenerTodas();

    @Query
    ("""
        SELECT r
        FROM Rutina r
        LEFT JOIN FETCH r.tipoRutina
        WHERE r.id = :idRutina
    """)
    Optional<Rutina> obtenerPorId(@Param("idRutina") Long idRutina);

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
        ORDER BY r.id, er.orden
    """)
    List<RutinaCompletaResponseDto> obtenerTodasRutinaCompleta();

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
        WHERE r.id = :idRutina
        ORDER BY r.id, dr.orden, er.orden
    """)
    List<RutinaCompletaResponseDto> obtenerTodasRutinaCompleta(@Param("idRutina") Long idRutina);


    @Query
    (value = """
    
        SELECT
            r.titulo AS rutinaTitulo,
            tr.nombre AS nombreTipoRutina,
            (
                SELECT JSON_ARRAYAGG(
                    JSON_OBJECT(
                        'orden' VALUE er.orden,
                        'nombre' VALUE e.nombre,
                        'series' VALUE er.series,
                        'repeticionesMin' VALUE er.repeticiones_min,
                        'repeticionesMax' VALUE er.repeticiones_max,
                        'descanso' VALUE er.descanso_min
                        RETURNING CLOB
                    )
                    ORDER BY dr.orden, er.orden
                    RETURNING CLOB
                )
                FROM dia_rutina dr
                JOIN ejercicio_rutina er
                    ON dr.id = er.dia_rutina_id
                JOIN ejercicio e
                    ON e.id = er.ejercicio_id
                WHERE dr.rutina_id = r.id
            ) AS ejercicios
        FROM rutina r
        JOIN tipo_rutina tr
            ON tr.id = r.tipo_rutina_id
        WHERE r.id = :idRutina;

        """, nativeQuery = true)
    Optional<RutinaCompletaNativaProjection> obtenerRutinaCompletaNativa(@Param("idRutina") Long idRutina);

}
