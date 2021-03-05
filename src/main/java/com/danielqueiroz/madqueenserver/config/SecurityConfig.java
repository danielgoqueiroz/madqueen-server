package com.danielqueiroz.madqueenserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.danielqueiroz.madqueenserver.service.UserDetailsServiceImpl;

@EnableWebSecurity
@EnableResourceServer
@EnableAuthorizationServer
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private HandlerExceptionResolver handlerExceptionResolver;
	
	@Autowired
	public SecurityConfig(final HandlerExceptionResolver handlerExceptionResolver) {
		this.handlerExceptionResolver = handlerExceptionResolver;
	}
	
	@Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
	
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("teste").password(passwordEncoder().encode("123")).roles("USER");
        auth.authenticationProvider(authenticationProvider());
    }

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.httpBasic()
			.and()
				.authorizeRequests()
					.antMatchers("/user/create").permitAll()
					.anyRequest().authenticated();
					
//		http.authorizeRequests()
//			.anyRequest()
//			.authenticated()
//			.and()
//				.httpBasic()
//			.and()
//				.sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//			.and()
//				.csrf()
//				.disable()
//				.exceptionHandling()
//				.authenticationEntryPoint((request, response, exception) -> this.handlerExceptionResolver
//						.resolveException(request, response, null, exception));
	}

}
