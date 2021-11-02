package com.ata.flo.jwt;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private final AuthenticationManager authenticationManager;
	
	
	public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
					.readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);
			Authentication authentication = new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(),
					authenticationRequest.getPassword()
					);
			Authentication authenticate = authenticationManager.authenticate(authentication);
			return authenticate;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response,
			FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String key = "whenithurtsitdoesnolongermatterifyouhurttoothersjustkeepquietanddowhatyouhavetodoasyoulearnitandkeepprogressing";
		
		String token = Jwts.builder()
				.setSubject(authResult.getName())
				.claim("authorities", authResult.getAuthorities())
				.setIssuedAt(new Date(0))
				//.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
				.setExpiration(new Date(System.currentTimeMillis() + 10*60*1000)) // 1 minute expiration
				.signWith(Keys.hmacShaKeyFor(key.getBytes()))
				.compact();
		
		response.addHeader("Authorization", "Bearer " + token);
		response.setContentType("application/json");
		Map<String, String> tokens = new HashMap<String, String>();
		tokens.put("token",token);
		new ObjectMapper().writeValue(response.getOutputStream(), tokens);
	}
}
