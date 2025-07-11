package com.example.concessionaria.dto.cliente;

import java.util.UUID;

public record ClienteResponseDto(
		UUID id,
		String nome,
		String cpf
	){}
