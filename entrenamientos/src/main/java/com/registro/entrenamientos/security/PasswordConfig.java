package com.registro.entrenamientos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
    PasswordEncoder:
    Es una abstracción de Spring Security para transformar contraseñas en representaciones seguras y comprobar posteriormente si una contraseña ocincide con su hash.
    BCrypt incorpora una sal y un coste computacional, por lo que dos codificaciones de la misma contraseña normalmente producen hashes diferentes.
    Para verificar una contraseña no debes volver a codificarla y comparar las cadenas.
    
    passwordEncoder.matches(
        contraseñaIngresada,
        hashGuardado
    );

    Spring Security hace esta comprobación por nosotros cuando utilizamos DaoAuthenticationProvider.
*/
@Configuration
public class PasswordConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); 
    }
}
