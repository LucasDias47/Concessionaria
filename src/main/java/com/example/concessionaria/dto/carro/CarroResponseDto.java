package com.example.concessionaria.dto.carro;

import java.util.UUID;

public record CarroResponseDto(
		UUID id,
		String modelo,
		String placa
){}
