package com.example.concessionaria.user;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioDetails implements UserDetails {

	private final UsuarioModel usuario;
	
	public UsuarioDetails(UsuarioModel usuario){
		this.usuario = usuario;
	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRole()));
		return authorities;
	}

	@Override
	public String getPassword() {
		return usuario.getPassword();
	}

	@Override
	public String getUsername() {
		return usuario.getUsername();
	}
	
	@Override
	public boolean isAccountNonExpired(){
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked(){
		return true;
	}
	
	@Override 
	public boolean isCredentialsNonExpired(){
		return true;
	}
	
	@Override
	public boolean isEnabled(){
		return true;
	}
	
	public UsuarioModel getUsuario(){
		return usuario;
	}
	
	
}
