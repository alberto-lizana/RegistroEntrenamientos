package com.registro.entrenamientos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.registro.entrenamientos.model.Ejercicio;

@Repository 
public interface EjercicioRepository extends JpaRepository<Ejercicio, Long> {

    @Query 
    ("""
        SELECT e 
        FROM Ejercicio e
    """)
    List<Ejercicio> obtenerTodos();

    @Query 
    ("""
        SELECT e 
        FROM Ejercicio e
        WHERE e.id = :idEjercicio
    """)
    Optional<Ejercicio> obtenerEjercicioPorId(@Param("idEjercicio") Long idEjercicio);
}
