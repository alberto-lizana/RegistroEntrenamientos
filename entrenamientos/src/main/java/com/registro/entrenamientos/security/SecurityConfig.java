package com.registro.entrenamientos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*

    SecurityConfig: El centro de las reglas de seguridad
        Esta clase es la que configura cómo Spring Security procesa las peticiones y qué accesos permite.
    
        @Configuration indica que la clase declara beans de Spring.
        @EnableWebSecurity habilita la integración con Spring Security.

        
        public interface SecurityFilterChain {
            boolean matches(HttpServletRequest request);

            List<Filter> getFilters();
        }

    AuthenticationManager: coordina la autenticación.
    AuthenticationProvider: ejecuta un mecanismo concreto de autenticación.
    UserDetailsService: recupera los datos del usuario.
    PasswordEncoder: compara la contraseña con su hash.

*/
@Configuration
@EnableWebSecurity
// @RequiredArgsConstructor (lombok evita tener q hacer el constructor normalmente)
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(
            JwtAuthFilter jwtAuthFilter,
            CustomUserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    /*
        Define la cadena de filtros de seguridad de la aplicación
        Desactivar CSRF 
            .csrf(csrf -> csrf.disable())
        Esto es habitual en una API sin estado que utiliza JWT en el encabvezado Authorization, porque el navegador no adjunta automáticamente ese oken como lo haría ocn una cookie de sesión tradicional. 
        Sin embargo no es una regla universal. Si después guardamos las credenciales en cookies que el navegador envía automáticamente, debemos revisar la protección CSRF.

        Configurar sesiones sin estado
        .sessionManagement(sm ->
            sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        Esta línea es fundamental, dado que, en una aplicación que se basa en sesiones, el servidor suele mantener una sesión y utiliza una cookie como JSESSIONID para reconocer al usuario.
        En este caso, el cliente representa el JWT en cada petición. Spring Security no necesita mantener una sesión HTTP para recordar la autenticación entre peticiones y, en caso de la expieración del token se envía de nuevo al inicio de sesión.
        Eso no significa que la aplicación no tenga estado en absoluto: la base de datos ocntinúa almacenando usuarios y otros datos. Significa que la autenticación no depende de una sesión HTTP persistente.

        Declarar Endpoints públicos y protegidos:
            .authorizeHttpRequests(auth -> auth
            .requestMatchers("/usuario/registrar").permitAll()
            .requestMatchers("/usuario/obtener").authenticated()

            .requestMatchers("/auth/login").permitAll()
            .requestMatchers("/auth/recuperar").permitAll()
            .requestMatchers("/auth/restablecer").permitAll()

            .anyRequest().authenticated()
        )
        
        - permitAll(): Nio exige que la petición esté autenticada
        - authenticated(): Exige una identidad autenticada
        - anyRequest().authenticated() Exige autenticación para cualquier otra ruta. 

        La politica utilizada es consevadora y segura dado que si olvidamos declarar un endpoint púiblica, quedará protegido dado a la ultima consigna .anyRequest().authenticated(). 
        Además se utiliza una gestión individual por endpoint brindando mayor flexibilidad y capacidad de gestión posteriormente para ir ajustando mientras vayamso probando la aplicación que deberia ser privado y que no etc.
        Por otro lado, en casos donde queremos generalziar y ya esta todo probado y terminado podemos pasar a rutas más resumidas que implica utilizar (**).
            .requestMatchers("/auth/**").authenticated()
        Ese código nos dejaria todo lo relacionado con auth, siendo auth el requestMapping, en privado, es decir, cualquier consulta que comienze con /auth/ lo siguiente requerirá autenticación

        Además podemos pedir autenticación por rol o incluso por plan u combinación de ambas 
            .requestMatchers("/admin/**").hasRole("ADMIN")
        Podría restringir una zona administrativa.
        No obstante, proteger una ruta por rol no basta para todos los casos. Si un usuario puede consultar una rutina, 
        una ficha o un registro de entrenamiento, también deberías verificar que tiene permiso sobre ese recurso concreto.
        La autenticación demuestra quién es; la autorización debe decidir qué puede hacer con cada recurso.
    */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(sm ->
                sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth -> auth
                // USUARIO
                .requestMatchers("/usuario/registrar").permitAll()                           
                .requestMatchers("/usuario/obtener").hasAnyRole("USUARIO", "ADMIN")                     
                .requestMatchers("/usuario/obtener-todos").hasRole("ADMIN")                  
                .requestMatchers("/usuario/obtener-{id}").hasRole("ADMIN")                  
                .requestMatchers("/usuario/modificar-nombre").hasAnyRole("ADMIN", "USUARIO") 
                .requestMatchers("/usuario/modificar-appat").hasAnyRole("ADMIN", "USUARIO") 
                .requestMatchers("/usuario/modificar-apmat").hasAnyRole("ADMIN", "USUARIO") 
                .requestMatchers("/usuario/modificar-fechaNacimiento").hasAnyRole("ADMIN", "USUARIO") 
                .requestMatchers("/usuario/modificar-password").hasAnyRole("ADMIN", "USUARIO") 
                .requestMatchers("/usuario/modificar-plan").hasRole("ADMIN") 
                .requestMatchers("/usuario/modificar-sexo").hasAnyRole("ADMIN", "USUARIO") 
                .requestMatchers(HttpMethod.DELETE, "/usuario/borrado-logico/*").hasRole("ADMIN") 
                .requestMatchers(HttpMethod.DELETE, "/usuario/borrado-fisico/*").hasRole("ADMIN") 

                // AUTH
                .requestMatchers("/auth/login").permitAll()
                .requestMatchers("/auth/recuperar").permitAll()
                .requestMatchers("/auth/restablecer").permitAll()

                // EJERCICIO
                .requestMatchers("/ejercicio/obtener-todos").hasRole("ADMIN")  
                .requestMatchers("/ejercicio/obtener-{id}").hasRole("ADMIN") 

                // RUTINA
                .requestMatchers("/rutina/obtener-todos").hasAnyRole("ADMIN", "USUARIO") 
                .requestMatchers("/rutina/obtener-{id}").hasAnyRole("ADMIN", "USUARIO") 

                // DIA RUTINA PRUEBA 
                .requestMatchers("/rutina/obtener-todos").hasAnyRole("ADMIN", "USUARIO") 
                .requestMatchers("/rutina/obtener-{id}").hasAnyRole("ADMIN", "USUARIO") 

                // EJERCICIO RUTINA PRUEBA 
                .requestMatchers("/ejercicio-rutina/obtener-todos").permitAll() // Rol usuario y admin
                .requestMatchers("/ejercicio-rutina/del-dia-{id}").permitAll() // Rol usuario y admin
                .requestMatchers("/ejercicio-rutina/del-diajer-dia-{idDia}-rutina-{idRutina}").permitAll() // Rol usuario y admin

                // RUTINA COMPLETA PRUEBA 
                .requestMatchers("/rutina-completa/obtener-todas").permitAll() // Rol usuario y admin
                .requestMatchers("/rutina-completa/obtener-{id}").permitAll() // Rol usuario y admin
                
                .requestMatchers("/rutina-completa/obtener-queryNativa-{idRutina}").permitAll() // Rol usuario y admin

                .anyRequest().authenticated()
            )
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(
                jwtAuthFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }

    /*
        El DaoAuthenticationProvider coordina la autenticación tradicional basada en usuario y contraseña.

        El flujo conceptual es:
            1. AuthenticationManager recibe las credenciales.
            2. DaoAuthenticationProvider solicita al UserDetailsService que cargue el usuario utilizando el email.
            3. El servicio consulta la base de datos.
            4. El proveedor compara la contraseña ingresada con el hash almacenado utilizando BCrypt.
            5. Si todo es correcto, devuelve una autenticación satisfactoria.
            6. Si las credenciales son incorrectas, se produce una excepción de autenticación.
    */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    /*
        Expone el administrador de autenticación que Spring configura a partir de los proveedores disponibles.
        Así podemos inyectarlo en AuthServiceImpl y delegar la autenticación, 
        en vez de implementar nosotros la comparación de contraseñas.
    */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


}