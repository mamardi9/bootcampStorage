package com.bootcampstorageult.bootcampstorageult.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login", "/register").permitAll()  // Permitir acceso público
                        .anyRequest().authenticated()  // Requiere autenticación para cualquier otra solicitud
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Página de login personalizada
                        .defaultSuccessUrl("/welcome", true)  // Redirección tras el login exitoso
                        .failureUrl("/login?error=true")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")  // URL para logout
                        .logoutSuccessUrl("/login?logout=true")  // Redirige a la página de login con un parámetro tras logout
                        .invalidateHttpSession(true)  // Invalida la sesión del usuario
                        .deleteCookies("JSESSIONID")  // Elimina la cookie de sesión
                )
                .csrf(csrf -> csrf.disable());  // Desactivar CSRF para simplificar (mejor habilitarlo en producción)

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
