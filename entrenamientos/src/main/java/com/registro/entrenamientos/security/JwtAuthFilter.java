package com.registro.entrenamientos.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
    Extrae el Token e intenta autenticar la petición

    OncePerRequestFilter: hereda de GenericFilterBean y GenericFilterBean implementa diferentes interfaces como: Filter, BeanNameAware, EnvironmentAware, EnvironmentCapable, ServletContextAware, InitializingBean, DisposableBean
        Es una clase de Spring Web que facilita ejecutar un filtro una vez por petición dentro de su despacho normal. 
        Es útil para comprobar el JWT antes de que la petición llegue al controlador.
    
    Explicación de las inyecciones realizadas 
        JwtService analiza y valida el token.
        CustomUserDetailsService recupera al usuario y sus autoridades.

    protected abstract void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException;
    
*/
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthFilter(
        JwtService jwtService,
        CustomUserDetailsService userDetailsService
    ) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization"); // Encabezado HTTP

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Extraer Identtidad y cargar al usuario
        String token = authHeader.substring(7); // Substring 7 quiere decir que elimina el bearer y conserva tan solo el JWT

        String id = jwtService.extraerId(token);

        if (id != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            /*
                Con el ID del token, recupero al usuario actual y sus roles.
                Esto tiene una ventaja: si un administrador cambia el rol del usuario en la base de datos, 
                una petición posterior puede cargar el rol actualizado. No dependo únicamente de una autoridad almacenada en el JWT durante 12 Horas.  
                Pero también implica que en cada petición autenticada consulto a la base de datos. 
                Es un compromiso razonable para una aplicación que necesita que los cambios de usuario y rol tengan efecto con rapidez.
            */
            UserDetails userDetails =
                    userDetailsService.loadUserById(Long.valueOf(id));

            /* 
                CREAR LA AUTENTICACIÓN 
                    - userDetails: la identidad autenticada.
                    - null: las credenciales no se conservan aquí; no necesitamos volver a almacenar la contraseña.
                    - userDetails.getAuthorities()

                    public UsernamePasswordAuthenticationToken(Object principal, @Nullable Object credentials, Collection<? extends GrantedAuthority> authorities) {
                        super(authorities);
                        this.principal = principal;
                        this.credentials = credentials;
                        super.setAuthenticated(true);
                    }
                UsernamePasswordAuthenticationToken:
                Es una implementación de Authentication, la interfaz que Spring Security utiliza para representar una autenticación.
                Es por ello que se utiliza después de verificar y validar el JWT y cargar el usuario.
            */
            if (jwtService.esTokenValido(token, userDetails)) {

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                /*
                        Añade detalles relacionados con la petición, como información de la solicitud que Spring utiliza en su contexto de autenticación.
                */
                authToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                /*
                    Esta es la línea que conecta la identidad verificada con el resto de Spring Security.
                    El SecurityContextHolder permite que otros componentes consulten quién está autenticado 
                    durante la petición actual.
                    Esta instrucción permite que Spring Security reconozca al usuario como la identidad autenticada de la petición.

                    A partir de ese momento, las reglas como
                        .requestMatchers("/usuario/obtener").authenticated()
                    pueden evaluar la petición como autenticada.
                */
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authToken);
            }
        }
        /*
            filterChain.doFilter(request, response);
            
            public interface FilterChain {
                void doFilter(ServletRequest var1, ServletResponse var2) throws IOException, ServletException;
            }

            Esto permite que la petición continúe por la cadena de filtros hasta alcanzar el controlador, 
            siempre que las demás reglas de seguridad lo permitan.

            Una distinción importante: filtro y autorización
                El filtro no decide directamente si el usuario puede consultar cualquier dato del sistema.
                El filtro establece su identidad. SecurityConfig decide si esa identidad puede entrar al endpoint 
                y la lógica de negocio debe comprobar si se puede acceder al recurso concreto.
                Por ejemplo, estar autenticada no debería permitir que el Usuario modifique los registros de entrenamiento de otro usuario.
            
        */

        filterChain.doFilter(request, response);
    }
}
