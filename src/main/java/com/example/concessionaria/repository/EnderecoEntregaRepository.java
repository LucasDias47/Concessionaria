package com.example.concessionaria.repository;

import java.util.List;
import java.util.UUID;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.concessionaria.dto.endereco.EnderecoEntregaDto;
import com.example.concessionaria.model.EnderecoEntregaModel;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface EnderecoEntregaRepository extends JpaRepository<EnderecoEntregaModel, UUID> {
	
	List<EnderecoEntregaModel>findAllByBairro(String bairro);

	EnderecoEntregaModel save(
			@Valid @NotNull(message = "O endereço de entrega é obrigatório.") EnderecoEntregaDto enderecoEntregaDto);

}
