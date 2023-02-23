package com.nguyenhoang.javaadfinalexam.config.security;

import com.nguyenhoang.javaadfinalexam.config.exception.AuthExceptionHandler;
import com.nguyenhoang.javaadfinalexam.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Component
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)//configue de co the set author ngay tren controller
public class WebSecutiryConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private IStaffService staffService;

	@Autowired
	private AuthExceptionHandler authExceptionHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(staffService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors()
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(authExceptionHandler)
		.accessDeniedHandler(authExceptionHandler)
		.and()
		.authorizeRequests()
			.antMatchers("/api/final/v1/staffs/**").hasAuthority("ADMIN")
			.antMatchers("/api/final/v1/tuyens/city/{from}/{to}/exists").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.GET,"/api/final/v1/tuyens").hasAnyAuthority("PILOT","ADMIN")
			.antMatchers(HttpMethod.GET,"/api/final/v1/tuyens/cities").hasAnyAuthority("PILOT","ADMIN")
			.antMatchers("/api/final/v1/tuyens/**").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.GET,"/api/final/v1/flights").hasAnyAuthority("PILOT","ADMIN")
			.antMatchers("/api/final/v1/flights/**").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.GET,"/api/final/v1/admins").hasAnyAuthority("PILOT","ADMIN")
			.antMatchers("/api/final/v1/admins/**").hasAuthority("ADMIN")
			.antMatchers(HttpMethod.GET,"/api/final/v1/pilots").hasAnyAuthority("PILOT","ADMIN")
			.antMatchers(HttpMethod.GET,"/api/final/v1/pilots/all").hasAnyAuthority("PILOT","ADMIN")
			.antMatchers("/api/final/v1/pilots/**").hasAuthority("ADMIN")
			.anyRequest().authenticated()
			.and()
			.httpBasic()
			.and()
			.csrf().disable();
	}
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
		configuration.applyPermitDefaultValues();

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
