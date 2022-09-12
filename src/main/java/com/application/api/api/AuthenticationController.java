package com.application.api.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.api.security.entity.JwtRequest;
import com.application.api.security.entity.JwtResponse;
import com.application.api.security.service.CustomUserDetailsService;
import com.application.api.security.util.CustomUserDetails;
import com.application.api.security.util.TokenManager;

@RestController
@RequestMapping("api/authenticate")
public class AuthenticationController {
	
	private TokenManager tokenManager;
	private CustomUserDetailsService userDetailsService;
	private AuthenticationManager authenticationManager;
	
	@Autowired
	public AuthenticationController(TokenManager tokenManager, CustomUserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
		super();
		this.tokenManager = tokenManager;
		this.userDetailsService = userDetailsService;
		this.authenticationManager = authenticationManager;
	}
	
	@PostMapping()
	public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		authenticate(jwtRequest);
		CustomUserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		JwtResponse jwtResponse = new JwtResponse();
		if(userDetails != null) {
			jwtResponse.setToken(tokenManager.generateToken(userDetails));
		}
		
		return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.OK);
	}
	
	public void authenticate(JwtRequest request) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		}catch (DisabledException exp) {
			throw new Exception("User disabled");
		}catch (BadCredentialsException exp) {
			throw exp;
		}
	}
	
}
