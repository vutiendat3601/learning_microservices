package com.datvutech.licenseservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@Configuration
public class SecurityConfig {
    private final KeycloakRoleConverter keycloakRoleConverter;

    public SecurityConfig(KeycloakRoleConverter keycloakRoleConverter) {
        this.keycloakRoleConverter = keycloakRoleConverter;
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(reqs -> reqs
                        .antMatchers("/actuator/health").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(ssMgnt -> ssMgnt.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2Customizer -> oauth2Customizer
                        .jwt(jwtCutomizer -> jwtCutomizer.jwtAuthenticationConverter(jwtAuthenConverter())));
        return http.build();
    }

    private JwtAuthenticationConverter jwtAuthenConverter() {
        JwtAuthenticationConverter jwtAuthenConverter = new JwtAuthenticationConverter();
        jwtAuthenConverter.setJwtGrantedAuthoritiesConverter(keycloakRoleConverter);
        return jwtAuthenConverter;
    }
}
