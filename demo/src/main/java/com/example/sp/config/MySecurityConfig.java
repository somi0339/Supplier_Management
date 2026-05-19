package com.example.sp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
//debug this config file use this
@EnableWebSecurity
public class MySecurityConfig {
//Stateful
    //1.Session based Authentication using security filter chain i.e finded by delegatory filter proxy
   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http .cors(Customizer.withDefaults())//it looks to the bean corsconfigurationsource
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        //replaced by antmatchers
                        .requestMatchers("/actuator/**").permitAll()
                        .anyRequest().authenticated()

                )
                //.formLogin(form->form.disable())
//                .formLogin(form -> form
//                                .defaultSuccessUrl("http://localhost:4300", true)
////                        // The 'true' forces the redirect even if the user was trying to go somewhere else
//              )
                .logout(logout -> logout
                        .logoutSuccessUrl("http://localhost:4300") // Redirect here after logout too
                );

        return http.build();

    }
    //2.Basic Auth using in memory authentication
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.builder()
                .username("user")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder() ;
    }





    //we can inject this anywhere in class
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of("http://localhost:4300"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true); // REQUIRED for sessions(allows cookies from frontend )
//
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;

    }
    @Bean
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }

}
