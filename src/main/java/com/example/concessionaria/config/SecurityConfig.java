package com.example.concessionaria.config;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationManager authenticationManager;

	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;

    SecurityConfig(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		return http
			.csrf(csrf -> csrf.disable())
			.authorizedHttpRequests(auth -> auth
				.requestMatchers("/auth/login", "/auth/register").permitAll())
				.anyRequest().authenticated()
			)
			.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authenticationProvider(authencationProvider)
			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthentication.class)
			.build();		
	}
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}