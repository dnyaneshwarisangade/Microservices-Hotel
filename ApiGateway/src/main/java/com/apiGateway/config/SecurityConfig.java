package com.apiGateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){
        http.authorizeExchange().anyExchange()
                .authenticated().and().oauth2Client().and().oauth2ResourceServer().jwt();
        return http.build();

      /*  http
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/auth/**", "/login/**", "/oauth2/**").permitAll()
                        .anyExchange().authenticated()
                )
                .oauth2Login()
                .and()
                .oauth2Client()
                .and()
                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);

        return http.build(); */
    }
}
