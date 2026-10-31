package com.registro.entrenamientos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.registro.entrenamientos.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    @Query
    ("""
        SELECT u 
        FROM Usuario u
        WHERE u.email = :email
    """)
    Optional<Usuario> buscarUsuarioPorEmail(@Param("email") String email);

    @Query
    ("""
        SELECT u 
        FROM Usuario u
        WHERE u.id = :id
    """)
    Optional<Usuario> buscarUsuarioPorId(@Param("id") Long id);

    @Query
    ("""
        SELECT u 
        FROM Usuario u
    """)
    List<Usuario> obtenerTodo();
}