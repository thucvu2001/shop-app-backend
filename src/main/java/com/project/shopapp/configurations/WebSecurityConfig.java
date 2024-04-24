package com.project.shopapp.configurations;

import com.project.shopapp.filters.JwtTokenFilter;
import com.project.shopapp.models.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Value("${api.prefix}")
    private String apiPrefix;
    private final JwtTokenFilter jwtTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(
                        requests -> {
                            requests.requestMatchers(String.format("%s/users/register", apiPrefix)).permitAll();
                            requests.requestMatchers(String.format("%s/users/login", apiPrefix)).permitAll();

                            requests.requestMatchers(GET, String.format("%s/categories/**", apiPrefix)).hasAnyRole(Role.USER, Role.ADMIN);
                            requests.requestMatchers(POST, String.format("%s/categories/**", apiPrefix)).hasAnyRole(Role.ADMIN);
                            requests.requestMatchers(PUT, String.format("%s/categories/**", apiPrefix)).hasAnyRole(Role.ADMIN);
                            requests.requestMatchers(DELETE, String.format("%s/categories/**", apiPrefix)).hasAnyRole(Role.ADMIN);

                            requests.requestMatchers(GET, String.format("%s/products/**", apiPrefix)).hasAnyRole(Role.ADMIN, Role.USER);
                            requests.requestMatchers(POST, String.format("%s/products/**", apiPrefix)).hasAnyRole(Role.ADMIN);
                            requests.requestMatchers(PUT, String.format("%s/products/**", apiPrefix)).hasAnyRole(Role.ADMIN);
                            requests.requestMatchers(DELETE, String.format("%s/products/**", apiPrefix)).hasAnyRole(Role.ADMIN);

                            requests.requestMatchers(POST, String.format("%s/orders/**", apiPrefix)).hasAnyRole(Role.USER);
                            requests.requestMatchers(GET, String.format("%s/orders/**", apiPrefix)).hasAnyRole(Role.USER, Role.ADMIN);
                            requests.requestMatchers(PUT, String.format("%s/orders/**", apiPrefix)).hasRole(Role.ADMIN);
                            requests.requestMatchers(DELETE, String.format("%s/orders/**", apiPrefix)).hasRole(Role.ADMIN);

                            requests.requestMatchers(GET, String.format("%s/order_details/**", apiPrefix)).hasAnyRole(Role.ADMIN, Role.USER);
                            requests.requestMatchers(POST, String.format("%s/order_details/**", apiPrefix)).hasRole(Role.ADMIN);
                            requests.requestMatchers(PUT, String.format("%s/order_details/**", apiPrefix)).hasRole(Role.ADMIN);
                            requests.requestMatchers(DELETE, String.format("%s/order_details/**", apiPrefix)).hasRole(Role.ADMIN);

                            requests.anyRequest().authenticated();
                        }
                );

        return httpSecurity.build();
    }
}
