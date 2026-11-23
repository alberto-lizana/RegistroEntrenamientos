package com.registro.entrenamientos.security;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.registro.entrenamientos.config.AdminProperties;
import com.registro.entrenamientos.enums.RolEnum;
import com.registro.entrenamientos.enums.SexoEnum;
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

        List<Usuario> usuariosBase = crearUsuariosIniciales();

        usuarioRepository.saveAll(usuariosBase);

    }

    private List<Usuario> crearUsuariosIniciales() {

        String password = passwordEncoder.encode("Entrenar2026!");
        return List.of(
            Usuario
                .builder()
                    .nombre("Camila")
                    .appat("Rojas")
                    .apmat("Soto")
                    .fechaNacimiento(LocalDate.of(1995, 3, 12))
                    .fechaCreacion(LocalDateTime.now())
                    .email("camila.rojas@correo.com")
                    .password(password)
                    .isActive(true)
                    .rol(RolEnum.USUARIO)
                    .plan(adminProperties.plan())
                    .sexo(SexoEnum.FEMENINO)
                .build(),
            Usuario
                .builder()
                    .nombre("Matías")
                    .appat("Fuentes")
                    .apmat("Pérez")
                    .fechaNacimiento(LocalDate.of(1992, 7, 25))
                    .fechaCreacion(LocalDateTime.now())
                    .email("matias.fuentes@correo.com")
                    .password(password)
                    .isActive(true)
                    .rol(RolEnum.USUARIO)
                    .plan(adminProperties.plan())
                    .sexo(SexoEnum.MASCULINO)
                .build(),
            Usuario
                .builder()
                    .nombre("Valentina")
                    .appat("Muñoz")
                    .apmat("Araya")
                    .fechaNacimiento(LocalDate.of(1998, 11, 2))
                    .fechaCreacion(LocalDateTime.now())
                    .email("valentina.munoz@correo.com")
                    .password(password)
                    .isActive(true)
                    .rol(RolEnum.USUARIO)
                    .plan(adminProperties.plan())
                    .sexo(SexoEnum.FEMENINO)
                .build(),
            Usuario
                .builder()
                    .nombre("Sebastián")
                    .appat("Contreras")
                    .apmat("Vega")
                    .fechaNacimiento(LocalDate.of(1990, 1, 18))
                    .fechaCreacion(LocalDateTime.now())
                    .email("sebastian.contreras@correo.com")
                    .password(password)
                    .isActive(true)
                    .rol(RolEnum.USUARIO)
                    .plan(adminProperties.plan())
                    .sexo(SexoEnum.MASCULINO)
                .build(),
            Usuario
                .builder()
                    .nombre("Javiera")
                    .appat("Silva")
                    .apmat("Núñez")
                    .fechaNacimiento(LocalDate.of(2000, 5, 30))
                    .fechaCreacion(LocalDateTime.now())
                    .email("javiera.silva@correo.com")
                    .password(password)
                    .isActive(true)
                    .rol(RolEnum.USUARIO)
                    .plan(adminProperties.plan())
                    .sexo(SexoEnum.FEMENINO)
                .build(),
            Usuario
                .builder()
                    .nombre("Diego")
                    .appat("Tapia")
                    .apmat("Lagos")
                    .fechaNacimiento(LocalDate.of(1994, 9, 14))
                    .fechaCreacion(LocalDateTime.now())
                    .email("diego.tapia@correo.com")
                    .password(password)
                    .isActive(true)
                    .rol(RolEnum.USUARIO)
                    .plan(adminProperties.plan())
                    .sexo(SexoEnum.MASCULINO)
                .build(),
            Usuario
                .builder()
                    .nombre("Francisca")
                    .appat("Morales")
                    .apmat("Ibarra")
                    .fechaNacimiento(LocalDate.of(1997, 12, 8))
                    .fechaCreacion(LocalDateTime.now())
                    .email("francisca.morales@correo.com")
                    .password(password)
                    .isActive(true)
                    .rol(RolEnum.USUARIO)
                    .plan(adminProperties.plan())
                    .sexo(SexoEnum.FEMENINO)
                .build(),
            Usuario
                .builder()
                    .nombre("Nicolás")
                    .appat("Herrera")
                    .apmat("Campos")
                    .fechaNacimiento(LocalDate.of(1989, 4, 21))
                    .fechaCreacion(LocalDateTime.now())
                    .email("nicolas.herrera@correo.com")
                    .password(password)
                    .isActive(true)
                    .rol(RolEnum.USUARIO)
                    .plan(adminProperties.plan())
                    .sexo(SexoEnum.MASCULINO)
                .build(),
            Usuario
                .builder()
                    .nombre("Constanza")
                    .appat("Vargas")
                    .apmat("Reyes")
                    .fechaNacimiento(LocalDate.of(1996, 8, 3))
                    .fechaCreacion(LocalDateTime.now())
                    .email("constanza.vargas@correo.com")
                    .password(password)
                    .isActive(true)
                    .rol(RolEnum.USUARIO)
                    .plan(adminProperties.plan())
                    .sexo(SexoEnum.FEMENINO)
                .build(),
            Usuario
                .builder()
                    .nombre("Felipe")
                    .appat("Sandoval")
                    .apmat("Bravo")
                    .fechaNacimiento(LocalDate.of(1993, 6, 27))
                    .fechaCreacion(LocalDateTime.now())
                    .email("felipe.sandoval@correo.com")
                    .password(password)
                    .isActive(true)
                    .rol(RolEnum.USUARIO)
                    .plan(adminProperties.plan())
                    .sexo(SexoEnum.MASCULINO)
                .build()
        );
    }

}