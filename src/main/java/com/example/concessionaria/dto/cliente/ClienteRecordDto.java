package com.example.concessionaria.dto.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClienteRecordDto(
		
		@NotBlank(message = "O nome é obrigatório")
		String nome, 
		
		@NotBlank(message = "O CPF é obrigatório.")
		@Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 dígitos numéricos.")
		String cpf
		){

}
