package com.registro.entrenamientos.security;

import java.time.LocalDateTime;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.registro.entrenamientos.config.AdminProperties;
import com.registro.entrenamientos.enums.RolEnum;
import com.registro.entrenamientos.model.Usuario;
import com.registro.entrenamientos.repository.UsuarioRepository;

@Component
public class AdminInitializer implements ApplicationRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminProperties adminProperties;

    public AdminInitializer(
        UsuarioRepository usuarioRepository,
        PasswordEncoder passwordEncoder,
        AdminProperties adminProperties
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.adminProperties = adminProperties;
    }

    @Override
    public void run(ApplicationArguments args) {

        if (usuarioRepository.existsByEmail(adminProperties.email())) {
            return;
        }

        Usuario admin = Usuario
            .builder()
                .nombre(adminProperties.nombre())
                .appat(adminProperties.appat())
                .apmat(adminProperties.apmat())
                .fechaNacimiento(adminProperties.fechaNacimiento())
                .fechaCreacion(LocalDateTime.now())
                .email(adminProperties.email())
                .password(passwordEncoder.encode(adminProperties.password()))
                .isActive(true)
                .rol(RolEnum.ADMIN)
                .plan(adminProperties.plan())
                .sexo(adminProperties.sexo())
            .build();

        usuarioRepository.save(admin);
    }

}