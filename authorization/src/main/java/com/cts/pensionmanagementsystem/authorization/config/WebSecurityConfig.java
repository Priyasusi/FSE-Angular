package com.cts.pensionmanagementsystem.authorization.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cts.pensionmanagementsystem.authorization.filter.AuthTokenFilter;
import com.cts.pensionmanagementsystem.authorization.service.UserDetailsServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailService;

	@Autowired
	private AuthTokenFilter authTokenFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder authtAuthenticationManagerBuilder) throws Exception {

		log.info("START - configure(AuthenticationManagerBuilder) of WebSecurityConfig");

		authtAuthenticationManagerBuilder.userDetailsService(userDetailService)
				.passwordEncoder(new BCryptPasswordEncoder());
		super.configure(authtAuthenticationManagerBuilder);

		log.info("END - configure(AuthenticationManagerBuilder) of WebSecurityConfig");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		log.info("START - passwordEncoder() of WebSecurityConfig");
		log.info("END - passwordEncoder() of WebSecurityConfig");
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
		log.info("START - authenticationManager() of WebSecurityConfig");
		log.info("END - authenticationManager() of WebSecurityConfig");
		return super.authenticationManager();

	}

	// giving access to multiple authenticated end points

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		log.info("START - configure(HttpSecurity) of WebSecurityConfig");
		http.csrf().disable().cors().disable().authorizeRequests().antMatchers("/Authenticate", "/h2-console/**").permitAll()
				.anyRequest().authenticated().and()
				.exceptionHandling().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
		http.headers().frameOptions().disable();

		log.info("END - configure(HttpSecurity) of WebSecurityConfig");

	}

}
