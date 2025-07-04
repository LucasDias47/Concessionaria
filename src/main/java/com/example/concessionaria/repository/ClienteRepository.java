package com.example.concessionaria.repository;

import java.util.Optional;
import java.util.UUID;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.concessionaria.model.ClienteModel;

public interface ClienteRepository extends JpaRepository<ClienteModel, UUID>{

	Optional<ClienteModel> findByCpf(String cpf);

	
}
