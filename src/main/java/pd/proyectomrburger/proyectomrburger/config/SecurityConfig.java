package pd.proyectomrburger.proyectomrburger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;
import pd.proyectomrburger.proyectomrburger.services.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {


    private CustomUserDetailsService userDetailsServicesImp;


    // esto es para configurar la seguridad
        @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/css/**", "/js/**", "/componentes/**").permitAll()
                        .anyRequest().authenticated() // Privadas
                )
                .formLogin(form -> form
                        .loginPage("/login") // Tu página de login
                        .defaultSuccessUrl("/dashboard") // Después del login
                );
        return http.build();
    }

    // esto es para autenticar al usuario
     @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http
                .getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailsServicesImp)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    // esto es para Encriptar la contraseña
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

 /*   public static void main(String[] args) {
       System.out.println(new BCryptPasswordEncoder().encode("123"));
    }
 */
}