package com.example.concessionaria.dto.endereco;

import java.util.UUID;

public record EnderecoEntregaDto(
		UUID id,
		String rua, 
		int numero, 
		String cidade, 
		String bairro) {

}
