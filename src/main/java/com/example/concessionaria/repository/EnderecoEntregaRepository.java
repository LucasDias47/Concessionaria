package com.example.concessionaria.repository;

import java.util.List;
import java.util.UUID;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.concessionaria.model.EnderecoEntregaModel;

public interface EnderecoEntregaRepository extends JpaRepository<EnderecoEntregaModel, UUID> {
	
	List<EnderecoEntregaModel>findAllByBairro(String bairro);

}
