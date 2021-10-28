package com.ata.flo.security;

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

import com.ata.flo.jwt.JwtTokenVerifier;
import com.ata.flo.jwt.JwtUsernameAndPasswordAuthenticationFilter;

import static com.ata.flo.security.ApplicationUserRole.*;

import static com.ata.flo.security.ApplicationUserPermission.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final PasswordEncoder passwordEncoder;
	private final MyUserDetailsService myUserDetailsService;
	
	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder,
			MyUserDetailsService myUserDetailsService) {
		this.myUserDetailsService = myUserDetailsService;
		this.passwordEncoder = passwordEncoder;
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager()))
			.addFilterAfter(new JwtTokenVerifier(),JwtUsernameAndPasswordAuthenticationFilter.class)
			.authorizeRequests()
			.antMatchers("/", "/api/signup", "index", "/css/*", "/js/*").permitAll() //hna fin tzid les page li bghiti ykono accessible bla authentication
			.antMatchers("/api/**").hasAnyRole(USER.name(),ADMIN.name())
			.antMatchers(HttpMethod.DELETE, "/admin/api/**").hasAuthority(USER_WRITE.getPermission())
			.antMatchers(HttpMethod.POST, "/admin/api/**").hasAuthority(USER_WRITE.getPermission())
			.antMatchers(HttpMethod.PUT, "/admin/api/**").hasAuthority(USER_WRITE.getPermission())
			.antMatchers(HttpMethod.GET, "/admin/api/**").hasAnyRole(ADMIN.name(), HALFADMIN.name())
			.anyRequest()
			.authenticated();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(myUserDetailsService);
		
		return provider;
	}

	
}
