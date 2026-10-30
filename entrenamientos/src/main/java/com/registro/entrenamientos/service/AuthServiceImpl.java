package com.registro.entrenamientos.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.registro.entrenamientos.dto.AuthResponseDto;
import com.registro.entrenamientos.dto.CodigoRecuperarResponseDto;
import com.registro.entrenamientos.dto.LoginRequestDto;
import com.registro.entrenamientos.dto.RecuperarPasswordRequestDto;
import com.registro.entrenamientos.dto.RestablecerPasswordRequestDto;
import com.registro.entrenamientos.exception.CodigoExpiradoException;
import com.registro.entrenamientos.exception.CodigoInvalidoException;
import com.registro.entrenamientos.exception.UsuarioNoEncontradoException;
import com.registro.entrenamientos.model.PasswordResetToken;
import com.registro.entrenamientos.model.Usuario;
import com.registro.entrenamientos.repository.PasswordResetTokenRepository;
import com.registro.entrenamientos.repository.UsuarioRepository;
import com.registro.entrenamientos.security.JwtService;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
@Service
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    private final SecureRandom secureRandom = new SecureRandom();

    public AuthServiceImpl(
        UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder,
        AuthenticationManager authenticationManager, JwtService jwtService,
        PasswordResetTokenRepository passwordResetTokenRepository
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public AuthResponseDto loginUsuario(LoginRequestDto request) {
        log.info("Se inicia el proceso para autenticar al usuario");   
        /*
            UsernamePasswordAuthenticationToken contiene credenciales sin verificar.
            presentar credenciales no equivale a haber demostrado la identidad.
        */
        Authentication authentication =
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        log.info("Se genera token para el usuario autenticado");   
        String token = jwtService.generarToken(userDetails);

        return new AuthResponseDto(token);
    }

    @Transactional
    @Override
    public CodigoRecuperarResponseDto recuperarPassword(RecuperarPasswordRequestDto request) {

        log.info("Se inicia el proceso para recuperar password de un usuario");   
        Usuario usuario = usuarioRepository.buscarUsuarioPorEmail(request.email())
            .orElseThrow(() -> {
                log.warn("No se pudo iniciar la recuperación: usuario no encontrado");
                return new UsuarioNoEncontradoException("Usuario no encontrado");
            });
   
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.encontrarCodigoPorIdUsuario(usuario.getId())
            .orElse(null);

        log.debug("Consultando si el usuario ya posee un código de recuperación");    
     
        String codigo = String.valueOf(secureRandom.nextInt(100_000, 1_000_000));

        if (passwordResetToken != null) {

            log.info("Actualizando código de recuperación existente");

            passwordResetToken.setToken(codigo);
            passwordResetToken.setExpiracion(LocalDateTime.now().plusMinutes(10));
         
            passwordResetTokenRepository.save(passwordResetToken);

            log.info("Código de recuperación actualizado correctamente");

            return new CodigoRecuperarResponseDto(passwordResetToken.getToken());
        } 

        log.info("Generando nuevo registro de recuperación de contraseña");
        PasswordResetToken resetToken = PasswordResetToken.builder()
                .token(codigo)
                .expiracion(LocalDateTime.now().plusMinutes(10))
                .usuario(usuario)
                .build();

        PasswordResetToken tokenGuardado = passwordResetTokenRepository.save(resetToken);

        log.info("Registro de recuperación de contraseña creado correctamente");

        return new CodigoRecuperarResponseDto(tokenGuardado.getToken());
    }

    @Transactional
    @Override
    public void restablecerPassword(RestablecerPasswordRequestDto request) {

        log.info("Se inicia el proceso para restablecer la contraseña"); 
        PasswordResetToken resetToken = passwordResetTokenRepository.encontrarPorToken(request.codigo())
            .orElseThrow(() -> {
                log.warn("Código de restablecimiento erróneo o inválido");     
                return new CodigoInvalidoException("Código erróneo o inválido");
            });

        if (resetToken.getExpiracion().isBefore(LocalDateTime.now())) {
            log.warn("Código de restablecimiento esta expirado"); 
            throw new CodigoExpiradoException("El código ha expirado genera uno nuevo");
        }

        Usuario usuario = resetToken.getUsuario();

        if (!usuario.getEmail().equals(request.email())) {
            log.warn("Código de restablecimiento no pertenece al usuario"); 
            throw new CodigoInvalidoException("El código no corresponde al usuario");
        }

        usuario.setPassword(passwordEncoder.encode(request.nuevaPassword()));

        log.info("Proceso de restablecimiento de contraseña finalziado con éxito se procede a borrar el token de restablecimiento");
        usuarioRepository.save(usuario);

        passwordResetTokenRepository.delete(resetToken);
    }
}