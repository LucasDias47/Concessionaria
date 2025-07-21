package com.example.concessionaria.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record EnderecoEntregaCreateDto(

	@NotBlank(message = "A rua é obrigatória")
	String rua,
	
	@Positive(message = "O número deve ser maior que zero")
	int numero,
	
	@NotBlank(message = "A cidade é obrigatória")
	String cidade,
	
	@NotBlank(message = "O bairro é obrigatório")
	String bairro

) {}
