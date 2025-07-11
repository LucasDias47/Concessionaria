package com.example.concessionaria.dto.venda;

import java.time.LocalDate;

import java.util.List;
import java.util.UUID;

import com.example.concessionaria.dto.carro.CarroRecordDto;
import com.example.concessionaria.dto.cliente.ClienteRecordDto;
import com.example.concessionaria.dto.endereco.EnderecoEntregaDto;
import com.example.concessionaria.model.EnderecoEntregaModel;

public record VendaRecordDto(
		LocalDate dataVenda,
		Double valorTotal,
		EnderecoEntregaDto enderecoEntrega,
		ClienteRecordDto cliente,
		List<CarroRecordDto> carros) {

}
