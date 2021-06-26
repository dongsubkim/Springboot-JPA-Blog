package com.dskim.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.dskim.blog.config.auth.PrincipalDetailService;

// bean register : allow to control objects in spring container

@Configuration // bean register
@EnableWebSecurity // register security filter
@EnableGlobalMethodSecurity(prePostEnabled = true) // check authentication in advance of access to specific addresses
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PrincipalDetailService principalDetailService;

	@Bean // IoC
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}

	// Security intercepts password and must know hashing algorithm so can compare
	// hashed password in db
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() // csrf token: disable while testing
				.authorizeRequests().antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**", "/dummy/**")
				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc") // Spring Security intercepts login request on this address and
														// process login
				.defaultSuccessUrl("/");
	}
}
