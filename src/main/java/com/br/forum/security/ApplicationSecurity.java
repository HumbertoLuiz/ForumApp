package com.br.forum.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class ApplicationSecurity {

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    AuthenticationManager authenticationManager(
        AuthenticationConfiguration authenticationConfiguration)
        throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    private static final String[] SWAGGER_WHITELIST= {
            "/v2/api-docs/**",
            "/swagger-resources/**",
            "/swagger-resources/**",
            "/configuration/ui/**",
            "/configuration/security/**",
            "/swagger-ui.html/**",
            "/swagger-ui/**",
            "/webjars/**"
    };

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());

        http.securityMatchers(requestMatcherCustomizer -> requestMatcherCustomizer
            .requestMatchers("/**"));

        http.authorizeHttpRequests(
            (Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry>) authorizeRequestsCustomizer -> authorizeRequestsCustomizer
                .requestMatchers(SWAGGER_WHITELIST).permitAll()
                .requestMatchers("/**").permitAll()
                .requestMatchers("/auth/login", "/docs/**", "/users").permitAll()
                .requestMatchers(HttpMethod.GET, "/topics").permitAll()
                .requestMatchers(HttpMethod.GET, "/topics/*").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth").permitAll()
                .requestMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/topics/*").hasRole("ADMIN")
                .requestMatchers("/replys").hasAnyRole("AUTHOR", "ADMIN")
                .requestMatchers("/courses").hasRole("ADMIN")
                .requestMatchers("AUTHOR", "ADMIN").authenticated()
                .anyRequest().authenticated());

        http.sessionManagement(sessionManagementCustomizer -> sessionManagementCustomizer
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.exceptionHandling(exceptionHandlingCustomizer -> exceptionHandlingCustomizer
            .authenticationEntryPoint(
                (request, response, ex) -> {
                    response.sendError(
                        HttpServletResponse.SC_UNAUTHORIZED,
                        ex.getMessage());
                }));

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web
            .ignoring()
            .requestMatchers("/**")
            .requestMatchers("/css/**")
            .requestMatchers("/img/**")
            .requestMatchers("/js**")
            .requestMatchers("/webjars/**")

            .requestMatchers(
                "/**.html",
                "/v2/api-docs",
                "/webjars/**",
                "/configuration/**",
                "/swagger-resources/**");
    }
}
