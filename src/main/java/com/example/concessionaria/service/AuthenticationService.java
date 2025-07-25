package com.example.concessionaria.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.concessionaria.user.UsuarioModel;
import com.example.concessionaria.user.UsuarioRepository;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public String registrar(UsuarioModel usuario){
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
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
