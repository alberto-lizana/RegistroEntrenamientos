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
import com.registro.entrenamientos.exception.UsuarioNoEncontradoException;
import com.registro.entrenamientos.model.PasswordResetToken;
import com.registro.entrenamientos.model.Usuario;
import com.registro.entrenamientos.repository.PasswordResetTokenRepository;
import com.registro.entrenamientos.repository.UsuarioRepository;
import com.registro.entrenamientos.security.JwtService;

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

        String token = jwtService.generarToken(userDetails);

        return new AuthResponseDto(token);
    }

    @Transactional
    @Override
    public CodigoRecuperarResponseDto recuperarPassword(RecuperarPasswordRequestDto request) {

        Usuario usuario = usuarioRepository.buscarUsuarioPorEmail(request.email())
            .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));

        PasswordResetToken passwordResetToken = passwordResetTokenRepository.encontrarCodigoPorIdUsuario(usuario.getId())
            .orElse(null);
        
        String codigo = String.valueOf(secureRandom.nextInt(100_000, 1_000_000));

        if (passwordResetToken != null) {
            passwordResetToken.setToken(codigo);
            passwordResetToken.setExpiracion(LocalDateTime.now().plusMinutes(10));
        
            passwordResetTokenRepository.save(passwordResetToken);

            return new CodigoRecuperarResponseDto(passwordResetToken.getToken());
        } 

        PasswordResetToken resetToken = PasswordResetToken.builder()
                .token(codigo)
                .expiracion(LocalDateTime.now().plusMinutes(10))
                .usuario(usuario)
                .build();

        PasswordResetToken tokenGuardado = passwordResetTokenRepository.save(resetToken);

        return new CodigoRecuperarResponseDto(tokenGuardado.getToken());
    
    }

    @Transactional
    @Override
    public void restablecerPassword(RestablecerPasswordRequestDto request) {

        PasswordResetToken resetToken = passwordResetTokenRepository.encontrarPorToken(request.codigo())
            .orElseThrow(() -> new RuntimeException("Código inválido"));

        if (resetToken.getExpiracion().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("El código ha expirado");
        }

        Usuario usuario = resetToken.getUsuario();

        if (!usuario.getEmail().equals(request.email())) {
            throw new RuntimeException("El código no corresponde al usuario");
        }

        usuario.setPassword(passwordEncoder.encode(request.nuevaPassword()));

        usuarioRepository.save(usuario);

        passwordResetTokenRepository.delete(resetToken);
    }
}