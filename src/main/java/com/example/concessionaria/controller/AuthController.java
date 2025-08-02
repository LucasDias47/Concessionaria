package com.example.concessionaria.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.concessionaria.dto.login.LoginRequestDto;
import com.example.concessionaria.dto.token.TokenResponseDto;
import com.example.concessionaria.service.AuthenticationService;
import com.example.concessionaria.user.UsuarioModel;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final AuthenticationService authService;
	
	public AuthController(AuthenticationService authService) {
        this.authService = authService;
    }
	
	@PostMapping("/registrar")
	public ResponseEntity<String> registrar(@RequestBody UsuarioModel usuario){
		String token = authService.registrar(usuario);
		return ResponseEntity.ok(token);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequestDto request){
		System.out.println("Username recebido: " + request.username());
		System.out.println("Password recebida: " + request.password());
		
		String token = authService.autenticar(request.username(), request.password());
		return ResponseEntity.ok(token);
	}

}
