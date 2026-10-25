package com.registro.entrenamientos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.registro.entrenamientos.model.PasswordResetToken;

@Repository 
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    @Query
    ("""
        SELECT prt 
        FROM PasswordResetToken prt
        WHERE prt.token = :token
    """)
    Optional<PasswordResetToken> encontrarPorToken(@Param("token") String token);

    @Query
    ("""
        SELECT prt 
        FROM PasswordResetToken prt
        WHERE prt.usuario.id = :usuarioId
    """)
    Optional<PasswordResetToken> encontrarCodigoPorIdUsuario(@Param("usuarioId") Long usuarioId);

    @Modifying
    @Query
    ("""
        DELETE FROM PasswordResetToken p 
        WHERE p.usuario.id = :usuarioId
    """)
    void eliminarPorUsuarioId(@Param("usuarioId") Long usuarioId);
}