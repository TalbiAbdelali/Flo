package com.ata.flo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import static com.ata.flo.security.ApplicationUserRole.*;
import static com.ata.flo.security.ApplicationUserPermission.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		super();
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/", "index", "/css/*", "/js/*").permitAll() //hna fin tzid les page li bghiti ykono accessible bla authentication
			.antMatchers("/api/**").hasRole(USER.name())
			.antMatchers(HttpMethod.DELETE, "/admin/api/**").hasAuthority(USER_WRITE.getPermission())
			.antMatchers(HttpMethod.POST, "/admin/api/**").hasAuthority(USER_WRITE.getPermission())
			.antMatchers(HttpMethod.PUT, "/admin/api/**").hasAuthority(USER_WRITE.getPermission())
			.antMatchers(HttpMethod.GET, "/admin/api/**").hasAnyRole(ADMIN.name())
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
			.loginPage("/login").permitAll()
			.defaultSuccessUrl("/defaultpage");
	}
	
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder.encode("admin"))
				//.roles(ADMIN.name())// ROLE_ADMIN
				.authorities(ADMIN.getGrantedAuthorities())
				.build();
		UserDetails halfadmin = User.builder()
				.username("halfadmin")
				.password(passwordEncoder.encode("halfadminn"))
				//.roles(USER.name())// ROLE_USER
				.authorities(HALFADMIN.getGrantedAuthorities())
				.build();
		UserDetails user = User.builder()
				.username("user")
				.password(passwordEncoder.encode("user"))
				//.roles(USER.name())// ROLE_USER
				.authorities(USER.getGrantedAuthorities())
				.build();
		
		return new InMemoryUserDetailsManager(
				admin,
				halfadmin,
				user
				);
	}
	
}
