package com.example.concessionaria.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioModel,UUID>{
	Optional<UsuarioModel> findByLogin(String email);
}
