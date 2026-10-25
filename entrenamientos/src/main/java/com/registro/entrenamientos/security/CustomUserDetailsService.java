package com.registro.entrenamientos.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.registro.entrenamientos.exception.UsuarioNoEncontradoException;
import com.registro.entrenamientos.model.Usuario;
import com.registro.entrenamientos.repository.UsuarioRepository;

import org.springframework.security.core.userdetails.User;

/* 
    Carga al usuario y sus roles 
    UserDetailsService es una interfaz de Spring Security. Su responsabilidad es cargar la información de un usuario a partir de un identificador de inicio de sesión.
        UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    Esto se puede realizar de dos formas 
        1.- Separando nuestro usuario del usuario de Spring Security pero que se comuniquen y se mantenga el modelo de negocio separado.
            Aquí Usuario sigue siendo tu entidad de negocio y User es una implementación de UserDetails que Spring Security proporciona.
            La ventaja es que podemos cambiar nuestro modelo de base de datos sin que eso implique cambiar necesariamente el modelo de seguridad.
        2.-  Implementar UserDetail directamente en nuestra clase Usuario:
            La ventaja es que hay menos código de conversión. La desventaja es que nuestro modelo de persistencia queda acoplado a Spring Security.

            
    Autenticación: comprobar quién está realizando la petición.
    Autorización: comprobar si esa persona tiene permiso para ejecutar la operación.
    
    UserDetails: la información de un usuario para Spring Security
    UserDetails es una interfaz de Spring Security.
    Define la información que Spring Security necesita para trabajar con una identidad.

    getUsername()
        El identificador que utilizará Spring Security
    getPassword()  
        La contraseña almacenada, normalmente como hash
    getAuthorities()
    	Los roles y permisos
    isEnabled()
    	Si la cuenta está habilitada
    isAccountNonExpired()
    	Si la cuenta ha expirado
    isAccountNonLocked()
    	Si la cuenta está bloqueada
    isCredentialsNonExpired()
    	Si las credenciales han expirado

    No todos esos datos tienen que existir como columnas de la tabla. 
    Depende de las reglas que queramos implementar.

    User es org.springframework.security.core.userdetails.User, 
    una implementación que ya proporciona Spring Security. 
    No es tu entidad com.registro.entrenamientos.model.Usuario.
    Son dos clases distintas, aunque ambas representen información relacionada con la misma persona.

    UserDetailsService: la forma de conseguir ese objeto
    En cambio, UserDetailsService es una interfaz que define una operación:

    La diferencia esencial: 
    UserDetails representa la información de seguridad de un usuario; 
    UserDetailsService es el mecanismo para cargar esa información.
*/

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Usuario usuario = usuarioRepository.buscarUsuarioPorEmail(email)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));

        return User.builder()
                .username(usuario.getId().toString())
                .password(usuario.getPassword())
                .roles(usuario.getRol().name())
                .build();
    }

    public UserDetails loadUserById(Long id) {

        Usuario usuario = usuarioRepository.buscarUsuarioPorId(id)
                .orElseThrow(() ->
                        new UsuarioNoEncontradoException("Usuario no encontrado"));

        return User.builder()
                .username(usuario.getId().toString())
                .password(usuario.getPassword())
                .roles(usuario.getRol().name())
                .build();
    }
}

/*
    interfaz UserDetail: 

    public interface UserDetails extends Serializable {
   Collection<? extends GrantedAuthority> getAuthorities();

   @Nullable String getPassword();

   String getUsername();

   default boolean isAccountNonExpired() {
      return true;
   }

   default boolean isAccountNonLocked() {
      return true;
   }

   default boolean isCredentialsNonExpired() {
      return true;
   }

   default boolean isEnabled() {
      return true;
   }
}

    User: 
        // Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
        package org.springframework.security.core.userdetails;

        public class User implements UserDetails, CredentialsContainer {
           private static final long serialVersionUID = 620L;
           private static final Log logger = LogFactory.getLog(User.class);
           private @Nullable String password;
           private final String username;
           private final Set<GrantedAuthority> authorities;
           private final boolean accountNonExpired;
           private final boolean accountNonLocked;
           private final boolean credentialsNonExpired;
           private final boolean enabled;

           public User(String username, @Nullable String password, Collection<? extends GrantedAuthority> authorities) {
              this(username, password, true, true, true, true, authorities);
           }

           public User(String username, @Nullable String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
              Assert.isTrue(username != null && !"".equals(username), "Cannot pass null or empty values to constructor");
              this.username = username;
              this.password = password;
              this.enabled = enabled;
              this.accountNonExpired = accountNonExpired;
              this.credentialsNonExpired = credentialsNonExpired;
              this.accountNonLocked = accountNonLocked;
              this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
           }

           public Collection<GrantedAuthority> getAuthorities() {
              return this.authorities;
           }

           public @Nullable String getPassword() {
              return this.password;
           }

           public String getUsername() {
              return this.username;
           }

           public boolean isEnabled() {
              return this.enabled;
           }

           public boolean isAccountNonExpired() {
              return this.accountNonExpired;
           }

           public boolean isAccountNonLocked() {
              return this.accountNonLocked;
           }

           public boolean isCredentialsNonExpired() {
              return this.credentialsNonExpired;
           }

           public void eraseCredentials() {
              this.password = null;
           }

           private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
              Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
              SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet(new AuthorityComparator());

              for(GrantedAuthority grantedAuthority : authorities) {
                 Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
                 sortedAuthorities.add(grantedAuthority);
              }

              return sortedAuthorities;
           }

           public boolean equals(@Nullable Object obj) {
              if (obj instanceof User user) {
                 return this.username.equals(user.getUsername());
              } else {
                 return false;
              }
           }

           public int hashCode() {
              return this.username.hashCode();
           }

           public String toString() {
              StringBuilder sb = new StringBuilder();
              sb.append(this.getClass().getName()).append(" [");
              sb.append("Username=").append(this.username).append(", ");
              sb.append("Password=[PROTECTED], ");
              sb.append("Enabled=").append(this.enabled).append(", ");
              sb.append("AccountNonExpired=").append(this.accountNonExpired).append(", ");
              sb.append("CredentialsNonExpired=").append(this.credentialsNonExpired).append(", ");
              sb.append("AccountNonLocked=").append(this.accountNonLocked).append(", ");
              sb.append("Granted Authorities=").append(this.authorities).append("]");
              return sb.toString();
           }

           public static UserBuilder withUsername(String username) {
              return builder().username(username);
           }

           /*******************************************************************
           public static UserBuilder builder() {
              return new UserBuilder();
           }
            ********************************************************************//*

           @Deprecated
           public static UserBuilder withDefaultPasswordEncoder() {
              logger.warn("User.withDefaultPasswordEncoder() is considered unsafe for production and is only intended for sample applications.");
              PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
              UserBuilder var10000 = builder();
              Objects.requireNonNull(encoder);
              return var10000.passwordEncoder(encoder::encode);
           }

           public static UserBuilder withUserDetails(UserDetails userDetails) {
              UserBuilder result = withUsername(userDetails.getUsername()).accountExpired(!userDetails.isAccountNonExpired()).accountLocked(!userDetails.isAccountNonLocked()).authorities(userDetails.getAuthorities()).credentialsExpired(!userDetails.isCredentialsNonExpired()).disabled(!userDetails.isEnabled());
              if (userDetails.getPassword() != null) {
                 result.password(userDetails.getPassword());
              }

              return result;
           }

           private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
              private static final long serialVersionUID = 620L;

              private AuthorityComparator() {
              }

              public int compare(GrantedAuthority g1, GrantedAuthority g2) {
                 if (g2.getAuthority() == null) {
                    return -1;
                 } else {
                    return g1.getAuthority() == null ? 1 : g1.getAuthority().compareTo(g2.getAuthority());
                 }
              }
           }

           public static final class UserBuilder {
              private @Nullable String username;
              private @Nullable String password;
              private List<GrantedAuthority> authorities = new ArrayList();
              private boolean accountExpired;
              private boolean accountLocked;
              private boolean credentialsExpired;
              private boolean disabled;
              private Function<@Nullable String, @Nullable String> passwordEncoder = (password) -> password;

              private UserBuilder() {
              }

              public UserBuilder username(String username) {
                 Assert.notNull(username, "username cannot be null");
                 this.username = username;
                 return this;
              }

              public UserBuilder password(@Nullable String password) {
                 this.password = password;
                 return this;
              }

              public UserBuilder passwordEncoder(Function<@Nullable String, @Nullable String> encoder) {
                 Assert.notNull(encoder, "encoder cannot be null");
                 this.passwordEncoder = encoder;
                 return this;
              }

              public UserBuilder roles(String... roles) {
                 List<GrantedAuthority> authorities = new ArrayList(roles.length);

                 for(String role : roles) {
                    Assert.isTrue(!role.startsWith("ROLE_"), () -> role + " cannot start with ROLE_ (it is automatically added)");
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
                 }

                 return this.authorities((Collection)authorities);
              }

              public UserBuilder authorities(GrantedAuthority... authorities) {
                 Assert.notNull(authorities, "authorities cannot be null");
                 return this.authorities((Collection)Arrays.asList(authorities));
              }

              public UserBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
                 Assert.notNull(authorities, "authorities cannot be null");
                 this.authorities = new ArrayList(authorities);
                 return this;
              }

              public UserBuilder authorities(String... authorities) {
                 Assert.notNull(authorities, "authorities cannot be null");
                 return this.authorities((Collection)AuthorityUtils.createAuthorityList(authorities));
              }

              public UserBuilder accountExpired(boolean accountExpired) {
                 this.accountExpired = accountExpired;
                 return this;
              }

              public UserBuilder accountLocked(boolean accountLocked) {
                 this.accountLocked = accountLocked;
                 return this;
              }

              public UserBuilder credentialsExpired(boolean credentialsExpired) {
                 this.credentialsExpired = credentialsExpired;
                 return this;
              }

              public UserBuilder disabled(boolean disabled) {
                 this.disabled = disabled;
                 return this;
              }

              public UserDetails build() {
                 Assert.notNull(this.username, "username cannot be null");
                 String encodedPassword = this.password != null ? (String)this.passwordEncoder.apply(this.password) : null;
                 return new User(this.username, encodedPassword, !this.disabled, !this.accountExpired, !this.credentialsExpired, !this.accountLocked, this.authorities);
              }
           }
        }
*/