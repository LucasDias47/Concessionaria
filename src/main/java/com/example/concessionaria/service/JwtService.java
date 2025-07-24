package com.example.concessionaria.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
public class JwtService {

	@Value("${jwt.secret}")
	private String secretString;
	
	private SecretKey secretKey;
	
	@PostConstruct
	public void init(){
		this.secretKey = Keys.hmacShaKeyFor(secretString.getBytes());
	}
	
	public String gerarToken(UserDetails userDetails){
		return Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 86400000))
				.signWith(secretKey, SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String extrairEmail(String token){
		return Jwts
				.parserBuilder()
				.setSigningKey(secretKey)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
	
	public boolean tokenValido(String token, UserDetails userDetails) {
		final String email = extrairEmail(token);
		return email.equals(userDetails.getUsername());
	}
}
