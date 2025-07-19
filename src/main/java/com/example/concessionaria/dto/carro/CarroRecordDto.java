package com.example.concessionaria.dto.carro;

import java.util.Set;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CarroRecordDto(
		
		@NotBlank(message = "O modelo é obrigatório")
		String modelo,
		
		@NotBlank(message = "A placa é obrigatória")
		@Pattern(
			regexp = "^[A-Z]{3}-\\d{4}$",
			message = "A placa deve estar no formato ABC-1234"
		)
		String placa) {

}
