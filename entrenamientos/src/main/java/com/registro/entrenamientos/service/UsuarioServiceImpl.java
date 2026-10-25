package com.registro.entrenamientos.service;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.registro.entrenamientos.dto.ModificarApmatUsuarioRequestDto;
import com.registro.entrenamientos.dto.ModificarAppatUsuarioRequestDto;
import com.registro.entrenamientos.dto.ModificarFechaNacimientoUsuarioRequestDto;
import com.registro.entrenamientos.dto.ModificarNombreUsuarioRequestDto;
import com.registro.entrenamientos.dto.ModificarPasswordUsuarioRequestDto;
import com.registro.entrenamientos.dto.ModificarPlanUsuarioRequestDto;
import com.registro.entrenamientos.dto.ModificarSexoUsuarioRequestDto;
import com.registro.entrenamientos.dto.RegistrarRequestDto;
import com.registro.entrenamientos.dto.UsuarioResponseDto;
import com.registro.entrenamientos.enums.RolEnum;
import com.registro.entrenamientos.exception.CodigoExpiradoException;
import com.registro.entrenamientos.exception.CodigoInvalidoException;
import com.registro.entrenamientos.exception.CodigoNoEncontradoException;
import com.registro.entrenamientos.exception.EmailYaExisteException;
import com.registro.entrenamientos.exception.UsuarioNoEncontradoException;
import com.registro.entrenamientos.exception.UsuarioNoExisteException;
import com.registro.entrenamientos.model.PasswordResetToken;
import com.registro.entrenamientos.model.Usuario;
import com.registro.entrenamientos.repository.PasswordResetTokenRepository;
import com.registro.entrenamientos.repository.UsuarioRepository;
import com.registro.entrenamientos.security.UsuarioAutenticadoService;

@Service 
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioAutenticadoService usuarioAutenticadoService;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    public UsuarioServiceImpl(
        UsuarioRepository usuarioRepository, 
        PasswordEncoder passwordEncoder, 
        UsuarioAutenticadoService usuarioAutenticadoService,
        AuthenticationManager authenticationManager,
        PasswordResetTokenRepository passwordResetTokenRepository
    ) 
    {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioAutenticadoService = usuarioAutenticadoService;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    @Transactional
    @Override
    public UsuarioResponseDto registrarUsuario(RegistrarRequestDto request) {

        usuarioRepository.buscarUsuarioPorEmail(request.email())
                .ifPresent(usuario -> {
                    throw new EmailYaExisteException("El email ya existe");
                });

        Usuario usuarioGuardado = usuarioRepository.save(
            Usuario
                .builder()
                    .nombre(request.nombre())
                    .appat(request.appat())
                    .apmat(request.apmat())
                    .fechaNacimiento(request.fechaNacimiento())
                    .email(request.email())
                    .password(passwordEncoder.encode(request.password()))
                    .rol(RolEnum.USUARIO)
                    .sexo(request.sexo())
                .build()
        );

        return UsuarioResponseDto
            .builder()
                .idUsuario(usuarioGuardado.getId())
                .nombre(usuarioGuardado.getNombre())
                .appat(usuarioGuardado.getAppat())
                .apmat(usuarioGuardado.getApmat())
                .fechaNacimiento(usuarioGuardado.getFechaNacimiento())
                .email(usuarioGuardado.getEmail())
                .plan(usuarioGuardado.getPlan().name())
                .nombreRol(usuarioGuardado.getRol().name())
                .sexo(usuarioGuardado.getSexo().name().toLowerCase())
            .build();
    }

    @Transactional(readOnly = true)
    @Override
    public UsuarioResponseDto obtener() {

        Long id = usuarioAutenticadoService.obtenerUsuarioIdAutenticado();

        return usuarioRepository.buscarUsuarioPorId(id)
                .map(usuario -> UsuarioResponseDto
                    .builder()
                        .idUsuario(usuario.getId())
                        .nombre(usuario.getNombre())
                        .appat(usuario.getAppat())
                        .apmat(usuario.getApmat())
                        .fechaNacimiento(usuario.getFechaNacimiento())
                        .email(usuario.getEmail())
                        .plan(usuario.getPlan().name())
                        .nombreRol(usuario.getRol().name())
                        .sexo(usuario.getSexo().name().toLowerCase())
                    .build())
                .orElseThrow(() ->
                    new UsuarioNoExisteException("Usuario no encontrado"));
    }

    @Transactional
    @Override
    public UsuarioResponseDto modificarNombre(ModificarNombreUsuarioRequestDto dto) {
        Long id = usuarioAutenticadoService.obtenerUsuarioIdAutenticado();

        Usuario usuario = usuarioRepository.buscarUsuarioPorId(id)
            .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));

        usuario.setNombre(dto.nombre());
        
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return UsuarioResponseDto                    
            .builder()
                .idUsuario(usuarioGuardado.getId())
                .nombre(usuarioGuardado.getNombre())
                .appat(usuarioGuardado.getAppat())
                .apmat(usuarioGuardado.getApmat())
                .fechaNacimiento(usuarioGuardado.getFechaNacimiento())
                .email(usuarioGuardado.getEmail())
                .plan(usuarioGuardado.getPlan().name())
                .nombreRol(usuarioGuardado.getRol().name())
                .sexo(usuarioGuardado.getSexo().name().toLowerCase())
            .build();
    }

    @Transactional
    @Override
    public UsuarioResponseDto modificarAppat(ModificarAppatUsuarioRequestDto dto) {
        Long id = usuarioAutenticadoService.obtenerUsuarioIdAutenticado();

        Usuario usuario = usuarioRepository.buscarUsuarioPorId(id)
            .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));

        usuario.setAppat(dto.appat());
        
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return UsuarioResponseDto                    
            .builder()
                .idUsuario(usuarioGuardado.getId())
                .nombre(usuarioGuardado.getNombre())
                .appat(usuarioGuardado.getAppat())
                .apmat(usuarioGuardado.getApmat())
                .fechaNacimiento(usuarioGuardado.getFechaNacimiento())
                .email(usuarioGuardado.getEmail())
                .plan(usuarioGuardado.getPlan().name())
                .nombreRol(usuarioGuardado.getRol().name())
                .sexo(usuarioGuardado.getSexo().name().toLowerCase())
            .build();
    }

    @Transactional
    @Override
    public UsuarioResponseDto modificarApmat(ModificarApmatUsuarioRequestDto dto) {
        Long id = usuarioAutenticadoService.obtenerUsuarioIdAutenticado();

        Usuario usuario = usuarioRepository.buscarUsuarioPorId(id)
            .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));

        usuario.setApmat(dto.apmat());
        
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return UsuarioResponseDto                    
            .builder()
                .idUsuario(usuarioGuardado.getId())
                .nombre(usuarioGuardado.getNombre())
                .appat(usuarioGuardado.getAppat())
                .apmat(usuarioGuardado.getApmat())
                .fechaNacimiento(usuarioGuardado.getFechaNacimiento())
                .email(usuarioGuardado.getEmail())
                .plan(usuarioGuardado.getPlan().name())
                .nombreRol(usuarioGuardado.getRol().name())
                .sexo(usuarioGuardado.getSexo().name().toLowerCase())
            .build();
    }

    @Transactional
    @Override
    public UsuarioResponseDto modificarFechaNacimiento(ModificarFechaNacimientoUsuarioRequestDto dto) {
        Long id = usuarioAutenticadoService.obtenerUsuarioIdAutenticado();

        Usuario usuario = usuarioRepository.buscarUsuarioPorId(id)
            .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));

        usuario.setFechaNacimiento(dto.fechaNacimiento());
        
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return UsuarioResponseDto                    
            .builder()
                .idUsuario(usuarioGuardado.getId())
                .nombre(usuarioGuardado.getNombre())
                .appat(usuarioGuardado.getAppat())
                .apmat(usuarioGuardado.getApmat())
                .fechaNacimiento(usuarioGuardado.getFechaNacimiento())
                .email(usuarioGuardado.getEmail())
                .plan(usuarioGuardado.getPlan().name())
                .nombreRol(usuarioGuardado.getRol().name())
                .sexo(usuarioGuardado.getSexo().name().toLowerCase())
            .build();
    }

    @Transactional
    @Override
    public UsuarioResponseDto modificarPlan(ModificarPlanUsuarioRequestDto dto) {
        Long id = usuarioAutenticadoService.obtenerUsuarioIdAutenticado();

        Usuario usuario = usuarioRepository.buscarUsuarioPorId(id)
            .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));

        usuario.setPlan(dto.plan());
        
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return UsuarioResponseDto                    
            .builder()
                .idUsuario(usuarioGuardado.getId())
                .nombre(usuarioGuardado.getNombre())
                .appat(usuarioGuardado.getAppat())
                .apmat(usuarioGuardado.getApmat())
                .fechaNacimiento(usuarioGuardado.getFechaNacimiento())
                .email(usuarioGuardado.getEmail())
                .plan(usuarioGuardado.getPlan().name())
                .nombreRol(usuarioGuardado.getRol().name())
                .sexo(usuarioGuardado.getSexo().name().toLowerCase())
            .build();
    }

    @Transactional
    @Override
    public UsuarioResponseDto modificarSexo(ModificarSexoUsuarioRequestDto dto) {
        Long id = usuarioAutenticadoService.obtenerUsuarioIdAutenticado();

        Usuario usuario = usuarioRepository.buscarUsuarioPorId(id)
            .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));

        usuario.setSexo(dto.sexo());
        
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return UsuarioResponseDto                    
            .builder()
                .idUsuario(usuarioGuardado.getId())
                .nombre(usuarioGuardado.getNombre())
                .appat(usuarioGuardado.getAppat())
                .apmat(usuarioGuardado.getApmat())
                .fechaNacimiento(usuarioGuardado.getFechaNacimiento())
                .email(usuarioGuardado.getEmail())
                .plan(usuarioGuardado.getPlan().name())
                .nombreRol(usuarioGuardado.getRol().name())
                .sexo(usuarioGuardado.getSexo().name().toLowerCase())
            .build();
    }

    @Transactional 
    @Override
    public UsuarioResponseDto modificarPassword(ModificarPasswordUsuarioRequestDto dto) {
        Long id = usuarioAutenticadoService.obtenerUsuarioIdAutenticado();

        PasswordResetToken codigoCambioContrasenna = passwordResetTokenRepository.encontrarCodigoPorIdUsuario(id)
            .orElseThrow(() -> new CodigoNoEncontradoException("Codigo no encontrado asociado al usuario"));
            
        Usuario usuario = usuarioRepository.buscarUsuarioPorId(id)
            .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));

        if (!codigoCambioContrasenna.getToken().equals(dto.codigo())) {
            throw new CodigoInvalidoException("Código inválido");
        }

        if (codigoCambioContrasenna.getExpiracion().isBefore(LocalDateTime.now())) {
            throw new CodigoExpiradoException("Código expirado");
        }

        if (!dto.password().equals(dto.rPassword())) {
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }

        usuario.setPassword(passwordEncoder.encode(dto.password()));
        
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        passwordResetTokenRepository.delete(codigoCambioContrasenna);

        return UsuarioResponseDto                    
            .builder()
                .idUsuario(usuarioGuardado.getId())
                .nombre(usuarioGuardado.getNombre())
                .appat(usuarioGuardado.getAppat())
                .apmat(usuarioGuardado.getApmat())
                .fechaNacimiento(usuarioGuardado.getFechaNacimiento())
                .email(usuarioGuardado.getEmail())
                .plan(usuarioGuardado.getPlan().name())
                .nombreRol(usuarioGuardado.getRol().name())
                .sexo(usuarioGuardado.getSexo().name().toLowerCase())
            .build();
    }

    @Transactional 
    @Override
    public void borradoLogico(Long id) {

        Usuario usuario = usuarioRepository.buscarUsuarioPorId(id)
            .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));

        usuario.setIsActive(false);
        
        usuarioRepository.save(usuario);
    }

    @Transactional
    @Override
    public void borradoFisico(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNoEncontradoException("Usuario no encontrado");
        }

        passwordResetTokenRepository.eliminarPorUsuarioId(id);

        usuarioRepository.deleteById(id);
    }
}