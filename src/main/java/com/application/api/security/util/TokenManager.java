package com.application.api.security.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import javax.crypto.SecretKey;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Component
public class TokenManager implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Value("${security.secret-key}")
	private String secretKey;
	private Long tokenValidity = 5 * 60 * 60l;
	
//	@Autowired
//	private SecretKey key;
	
	
	public Claims getAllClaimsFromToken(String token) {
//		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
		return Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token).getBody();
	}
	
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
		Claims claims = getAllClaimsFromToken(token);
		return claimResolver.apply(claims);
	}
	
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	public boolean isTokenExpired(String token) {
		Date expirationDate = getExpirationDateFromToken(token);
		return expirationDate.before(new Date());
	}
	
	public String generateToken(CustomUserDetails userDetails) {
		String username = userDetails.getUsername();
		
		return Jwts.builder()
				.setClaims(new HashMap<String, Object>())
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + (tokenValidity * 1000)))
//				.signWith(key).compact();
				.signWith(getSecretKey()).compact();
	}
	
	public boolean validateToken(String token, CustomUserDetails userDetails) {
		String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	public SecretKey getSecretKey() {
		return Keys.hmacShaKeyFor(secretKey.getBytes());
	}
	
}
