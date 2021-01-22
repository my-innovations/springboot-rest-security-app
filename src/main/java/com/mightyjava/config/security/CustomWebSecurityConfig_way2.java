package com.mightyjava.config.security;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //needed for role based authorization.
public class CustomWebSecurityConfig_way2 extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomAuthenticationEntryPoint entryPoint;

	 //########################################################
	  //Authentication (using in memory authentication)
	  //########################################################
	 

	//Note - comment the security.user.name and security.user.password in application.properties file
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("punya").password("punya").roles("USER");
	}
	
	// ########################################################
	//Authorization 
	//########################################################
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.headers().frameOptions().disable()
		.and()
		.authorizeRequests()
		.antMatchers("/h2-console/**").permitAll()
		.anyRequest().authenticated().and().httpBasic().authenticationEntryPoint(entryPoint);
	}
} */
