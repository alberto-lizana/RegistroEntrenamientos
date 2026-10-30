package com.registro.entrenamientos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.registro.entrenamientos.model.DiaRutina;

@Repository 
public interface DiaRutinaRepository extends JpaRepository<DiaRutina, Long> {

    @Query
    ("""
        SELECT dr 
        FROM DiaRutina dr 
        LEFT JOIN FETCH Rutina r
        LEFT JOIN FETCH TipoRutina tr
    """)
    List<DiaRutina> obtenerTodos();

    @Query
    ("""
        SELECT dr 
        FROM DiaRutina dr 
        LEFT JOIN FETCH Rutina r
        LEFT JOIN FETCH TipoRutina tr
        WHERE dr.id = :idDiaRutina
    """)
    Optional<DiaRutina> obtenerPorId(@Param("idDiaRutina") Long idDiaRutina);
    
}
