package com.example.concessionaria.dto.venda;

import java.time.LocalDate;

import java.util.List;
import java.util.UUID;

import com.example.concessionaria.dto.carro.CarroRecordDto;
import com.example.concessionaria.dto.cliente.ClienteRecordDto;
import com.example.concessionaria.dto.endereco.EnderecoEntregaDto;
import com.example.concessionaria.model.EnderecoEntregaModel;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public record VendaRecordDto(
		
		@NotNull(message = "A data da venda é obrigatória.")
		@PastOrPresent(message = "A data da venda não pode estar no futuro.")
		LocalDate dataVenda,
		
		@NotNull(message = "O valor total é obrigatório.")
		@DecimalMin(value = "0.0", inclusive = false, message = "O valor total deve ser maior que zero.")
		Double valorTotal,
		
		@Valid
		@NotNull(message = "O endereço de entrega é obrigatório.")
		EnderecoEntregaDto enderecoEntrega,
		
		@Valid
		@NotNull(message = "O cliente é obrigatório.")
		ClienteRecordDto cliente,
		
		@Valid
		@NotNull(message = "A lista de carros não pode ser nula.")
		List<CarroRecordDto> carros) {

}
