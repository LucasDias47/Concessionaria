package com.example.concessionaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.concessionaria.user.UsuarioModel;
import com.example.concessionaria.user.UsuarioRepository;


@Service
public class AuthenticationService {

	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	  @Autowired
	    public AuthenticationService(
	        UsuarioRepository usuarioRepository,
	        PasswordEncoder passwordEncoder,
	        AuthenticationManager authenticationManager,
	        JwtService jwtService
	    ) {
	        this.usuarioRepository = usuarioRepository;
	        this.passwordEncoder = passwordEncoder;
	        this.authenticationManager = authenticationManager;
	        this.jwtService = jwtService;
	    }
	
	public String registrar(UsuarioModel usuario){
		usuario.setSenha(passwordEncoder.encode(usuario.getPassword()));
		usuarioRepository.save(usuario);
		return jwtService.gerarToken(usuario);
	}
	
	public String autenticar(String email, String senha){
		authenticationManager.authenticate(
		    new UsernamePasswordAuthenticationToken(email, senha)		
		);
		var usuario = usuarioRepository.findByEmail(email)
							.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
		return jwtService.gerarToken(usuario);
	}
}
