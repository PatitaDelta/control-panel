package es.guillermoll.control_panel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated())
        .formLogin(Customizer.withDefaults())
        // .formLogin(form -> form
        //         .loginPage("/auth/login") // Página personalizada para el login
        //         .loginProcessingUrl("/auth/login") // URL que Spring Security procesará para el formulario de
        //         .defaultSuccessUrl("/api/v1/home", true) // Redirigir después de un inicio de sesión exitoso
        //         .failureUrl("/auth/login?error=true") // Redirigir en caso de error de autenticación
        //         .permitAll())
        .logout(logout -> logout
                .logoutUrl("/auth/logout") // URL para cerrar sesión
                .logoutSuccessUrl("/auth/login") // Redirigir después de cerrar sesión
                .permitAll());

        return http.build();
    }

}
