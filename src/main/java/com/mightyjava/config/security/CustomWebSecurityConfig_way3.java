package com.mightyjava.config.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //needed for role based authorization.
public class CustomWebSecurityConfig_way3 extends WebSecurityConfigurerAdapter {
	
	//@Autowired
	//private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
	
	//using in buildt auth provider DaoAuthenticationProvider.
	//@Autowired
	//private AuthenticationProvider authProvider;
	
	//using custom auth provider
	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;
	
	//using custom user details service.
	//@Autowired
	//private CustomUserDetailsServiceImpl customUserDetailsService;
	
	// ############################
	// Authentication section
	 //############################
	 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//way-01 , using in memory auth.
		//auth.inMemoryAuthentication().withUser("punya").password("punya").roles("USER");
		//OR
		//way-02
		// using in buildt DaoAuthenticationProvider class.
		//auth.authenticationProvider(daoAuthenticationProvider());
		//OR 
		//way-03  
		// using custom Authentication Provider class.
		auth.authenticationProvider(customAuthenticationProvider);
		//OR
		//way-04
		// using with springboot 1.x , we must not use with BcryptPasswordEncoder.
		//auth.userDetailsService(userDetailsService);
		//Note - when using springboot 2.x , we must use with BcryptPasswordEncoder.
		//auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
	//OR
	/*@Autowired
	public void configureAutthManager(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
		//OR
		 auth.authenticationProvider(daoAuthenticationProvider());
	}*/
	
	/*@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		//required when using springboot 2.x , where passwordEncoder is must.
		//authProvider.setPasswordEncoder(passwordEncoder);
		return authProvider;
	}*/
	
	 // ############################
	 // Authorization section
	 // ############################
	  
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.headers().frameOptions().disable()
		.and()
		.authorizeRequests()
		.antMatchers("/h2-console/**").permitAll()
		.anyRequest().authenticated()
		//.and().httpBasic().authenticationEntryPoint(customAuthenticationEntryPoint) //ok
		//OR
		.and().httpBasic().authenticationEntryPoint(customAuthenticationEntryPoint())
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	
	@Bean
	public AuthenticationEntryPoint customAuthenticationEntryPoint() {
		return new BasicAuthenticationEntryPoint() {
			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response,AuthenticationException authException) throws IOException, ServletException {
				//This msg can be send in json msg.
				response.addHeader("WWW-Authenticate", "Basic realm - " + getRealmName());
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				PrintWriter writer = response.getWriter();
				writer.println("Http Status 401 " + authException.getMessage());
			}
			@Override
			public void afterPropertiesSet() throws Exception {
				setRealmName("MightyJava");
				super.afterPropertiesSet();
			}
		};
	}
	//OR
	//we can use CustomAuthenticationEntryPoint class.
	
	
	@Autowired
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
} 
