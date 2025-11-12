package com.tecsup.Eval_S12.config;

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

    // Define el codificador de contraseñas (BCrypt) para cumplir con RNF1
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // Restricciones de Acceso basadas en Roles
                        .requestMatchers("/clientes/**").hasAnyAuthority("MOZO", "ADMIN")
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/pedidos/**").hasAnyAuthority("MOZO", "COCINERO")
                        .requestMatchers("/ventas/**").hasAnyAuthority("CAJERO", "ADMIN")
                        .requestMatchers("/inventario/**").hasAuthority("ADMIN")

                        // Permite el acceso libre a la página de login y recursos estáticos
                        .requestMatchers("/login", "/css/**", "/js/**", "/images/**", "/").permitAll()

                        // Cualquier otra ruta requiere autenticación
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")        // Ruta del formulario de login personalizado
                        .defaultSuccessUrl("/", true) // Redirección al inicio después de un login exitoso
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll()
                        .logoutSuccessUrl("/login?logout")
                );

        // Deshabilita CSRF para simplificar (considerar habilitar en producción)
        // http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}