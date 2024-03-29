package com.danielqueiroz.madqueenserver.security;

import static com.danielqueiroz.madqueenserver.constants.RoleCons.USER;
import static com.danielqueiroz.madqueenserver.constants.SecurityConstants.HEADER_AUTHORIZATION;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

import com.danielqueiroz.madqueenserver.auth.ApplicationUserService;
import com.danielqueiroz.madqueenserver.jwt.JwtConfig;
import com.danielqueiroz.madqueenserver.jwt.JwtTokenVerifier;
import com.danielqueiroz.madqueenserver.jwt.JwtUsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;
	private final ApplicationUserService applicationUserService;
	private final SecretKey secretKey;
	private final JwtConfig jwtConfig;

	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService,
			SecretKey secretKey, JwtConfig jwtConfig) {

		this.passwordEncoder = passwordEncoder;
		this.applicationUserService = applicationUserService;
		this.secretKey = secretKey;
		this.jwtConfig = jwtConfig;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		CorsConfiguration cors = new CorsConfiguration().applyPermitDefaultValues();
		cors.addAllowedMethod("*");
		cors.addExposedHeader(HEADER_AUTHORIZATION);
		http.csrf().disable().cors().configurationSource(request -> { return cors; })
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.addFilter(new JwtUsernamePasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
				.addFilterAfter(new JwtTokenVerifier(jwtConfig, secretKey), JwtUsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers("/status*", "/email*", "/login*", "/docs*", "/swagger-ui*").permitAll()
				.antMatchers(HttpMethod.GET, "/user*").permitAll()
				.antMatchers(HttpMethod.POST, "/user*").permitAll()
				.antMatchers("/artist*").hasAnyAuthority(USER)
				.antMatchers("/music*", "/band*" ).hasAnyAuthority(USER)
				.antMatchers("/admin*").hasAnyRole("ADMIN").anyRequest().authenticated();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(applicationUserService);
		return provider;
	}
	

}
