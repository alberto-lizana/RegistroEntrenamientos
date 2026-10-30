package com.registro.entrenamientos.service;

import java.time.LocalDateTime;
import java.util.List;

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
import com.registro.entrenamientos.mapper.UsuarioMapper;
import com.registro.entrenamientos.model.PasswordResetToken;
import com.registro.entrenamientos.model.Usuario;
import com.registro.entrenamientos.repository.PasswordResetTokenRepository;
import com.registro.entrenamientos.repository.UsuarioRepository;
import com.registro.entrenamientos.security.UsuarioAutenticadoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j 
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

        log.info("Se inicia el proceso para regitrar un usuario nuevo");
        usuarioRepository.buscarUsuarioPorEmail(request.email())
            .ifPresent(usuario -> {
                log.warn("El email del postulante a nuevo usuario ya existe");
                throw new EmailYaExisteException("El email ya existe");
            });

        log.info("Se crea el nuevo usuario de forma exitosa");
        return UsuarioMapper.toDto(
            usuarioRepository.save(Usuario
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
            )
        );
    }

    @Transactional(readOnly = true)
    @Override
    public UsuarioResponseDto obtener() {
        log.info("Se inicia el proceso para obtener la información correspondiente al usuario consultante");
        return usuarioRepository.buscarUsuarioPorId(usuarioAutenticadoService.obtenerUsuarioIdAutenticado())
                .map(UsuarioMapper::toDto)
                    .orElseThrow(() -> {
                        log.error(
                            String.format("No se ha encontrado al usuario con el ID: s%",
                            usuarioAutenticadoService.obtenerUsuarioIdAutenticado())
                        );
                        return new UsuarioNoExisteException("Usuario no encontrado");
                    });
    }

    @Transactional(readOnly = true)
    @Override
    public List<UsuarioResponseDto> obtenerTodos() {
        log.info("Se inicia el proceso para obtener la información correspondiente a todos los usuario");
        return usuarioRepository.obtenerTodo()
            .stream()
            .map(UsuarioMapper::toDto)
            .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public UsuarioResponseDto obtenerPorId(Long id) {

        log.info("Se inicia el proceso para obtener la información correspondiente a un usuario mediante un identificador");

        return UsuarioMapper.toDto(
            usuarioRepository.buscarUsuarioPorId(id)
                .orElseThrow(() -> {
                    log.warn("No se ha encontrado al usuario");
                    return new UsuarioNoExisteException("No se ha encontrado al usuario");
                })
        );
    }

    @Transactional
    @Override
    public UsuarioResponseDto modificarNombre(ModificarNombreUsuarioRequestDto dto) {
        
        log.info("Se inicia el proceso para modificar el nombre al usuario consultante");
        Usuario usuario = obtenerUsuarioPorId();
        
        log.info("Se asigna el nuevo valor");
        usuario.setNombre(dto.nombre());
        
        return UsuarioMapper.toDto(usuario);
    }

    @Transactional
    @Override
    public UsuarioResponseDto modificarAppat(ModificarAppatUsuarioRequestDto dto) {

        log.info("Se inicia el proceso para modificar el apellido paterno al usuario consultante");
        Usuario usuario = obtenerUsuarioPorId();

        log.info("Se asigna el nuevo valor");    
        usuario.setAppat(dto.appat());
        
        return UsuarioMapper.toDto(usuario);
    }

    @Transactional
    @Override
    public UsuarioResponseDto modificarApmat(ModificarApmatUsuarioRequestDto dto) {

        log.info("Se inicia el proceso para modificar el apellido materno al usuario consultante");
        Usuario usuario = obtenerUsuarioPorId();

        log.info("Se asigna el nuevo valor");
        usuario.setApmat(dto.apmat());
        
        return UsuarioMapper.toDto(usuario);
    }

    @Transactional
    @Override
    public UsuarioResponseDto modificarFechaNacimiento(ModificarFechaNacimientoUsuarioRequestDto dto) {

        log.info("Se inicia el proceso para modificar la fecha de nacimiento al usuario consultante");
        Usuario usuario = obtenerUsuarioPorId();

        log.info("Se asigna el nuevo valor");
        usuario.setFechaNacimiento(dto.fechaNacimiento());
        
        return UsuarioMapper.toDto(usuario);
    }

    @Transactional
    @Override
    public UsuarioResponseDto modificarPlan(ModificarPlanUsuarioRequestDto dto) {

        log.info("Se inicia el proceso para modificar el plan al usuario consultante");
        Usuario usuario = obtenerUsuarioPorId();

        log.info("Se asigna el nuevo valor");
        usuario.setPlan(dto.plan());
        
        return UsuarioMapper.toDto(usuario);
    }

    @Transactional
    @Override
    public UsuarioResponseDto modificarSexo(ModificarSexoUsuarioRequestDto dto) {

        log.info("Se inicia el proceso para modificar el sexo al usuario consultante");
        Usuario usuario = obtenerUsuarioPorId();

        log.info("Se asigna el nuevo valor");
        usuario.setSexo(dto.sexo());

        return UsuarioMapper.toDto(usuario);
    }

    @Transactional 
    @Override
    public UsuarioResponseDto modificarPassword(ModificarPasswordUsuarioRequestDto dto) {

        log.info("Se inicia el proceso de modificación de contraseña del usuario autenticado");

        log.debug("Se obtiene el identificador del usuario autenticado");
        Long id = usuarioAutenticadoService.obtenerUsuarioIdAutenticado();
        
        log.info("Se busca el código de recuperación asociado al usuario");
        PasswordResetToken codigoCambioContrasenna = passwordResetTokenRepository.encontrarCodigoPorIdUsuario(id)
            .orElseThrow(() -> {
                log.warn("No se encontró un código de recuperación asociado al usuario");
                return new CodigoNoEncontradoException("Codigo no encontrado asociado al usuario");
            });

        log.info("Se obtiene la información del usuario");
        Usuario usuario = obtenerUsuarioPorId();

        log.info("Se verifica la validez del código de recuperación");
        if (!codigoCambioContrasenna.getToken().equals(dto.codigo())) {
            log.warn("El código de recuperación proporcionado no es válido");
            throw new CodigoInvalidoException("Código inválido");
        }

        log.info("Se verifica la vigencia del código de recuperación");
        if (codigoCambioContrasenna.getExpiracion().isBefore(LocalDateTime.now())) {
            log.warn("El código de recuperación proporcionado ha expirado");
            throw new CodigoExpiradoException("Código expirado");
        }

        log.info("Se verifica que ambas contraseñas coincidan");
        if (!dto.password().equals(dto.rPassword())) {
            log.warn("La modificación de contraseña fue rechazada porque las contraseñas no coinciden");
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }

        log.info("Se codifica y asigna la nueva contraseña al usuario");
        usuario.setPassword(passwordEncoder.encode(dto.password()));
        
        log.info("Se elimina el código de recuperación utilizado");
        passwordResetTokenRepository.delete(codigoCambioContrasenna);

        log.info("Se finaliza correctamente el proceso de modificación de contraseña");
        return UsuarioMapper.toDto(usuario);
    }

    @Transactional 
    @Override
    public void borradoLogico(Long id) {

        Usuario usuario = usuarioRepository.buscarUsuarioPorId(id)
            .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));

        usuario.setIsActive(false);
        
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

    private Usuario obtenerUsuarioPorId(){
        return usuarioRepository.buscarUsuarioPorId(usuarioAutenticadoService.obtenerUsuarioIdAutenticado())
            .orElseThrow(() -> {
                log.warn("No se logró identificar al usuario consultante");
                return new UsuarioNoEncontradoException("Usuario no encontrado");
            });
    }

}