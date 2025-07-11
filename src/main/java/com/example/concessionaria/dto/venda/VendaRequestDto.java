package com.example.concessionaria.dto.venda;

import com.example.concessionaria.dto.carro.CarroRequestDto;
import com.example.concessionaria.dto.cliente.ClienteRequestDto;
import com.example.concessionaria.dto.endereco.EnderecoEntregaDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record VendaRequestDto(
		LocalDate dataVenda,
		BigDecimal valorTotal,
		ClienteRequestDto cliente,
		EnderecoEntregaDto enderecoEntrega,
		List<CarroRequestDto> carros) {

	
}
