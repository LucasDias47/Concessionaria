package com.example.concessionaria.mapper;

import com.example.concessionaria.dto.endereco.EnderecoEntregaCreateDto;
import com.example.concessionaria.dto.endereco.EnderecoEntregaDto;
import com.example.concessionaria.model.EnderecoEntregaModel;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class EnderecoEntregaMapper {

	public static @Valid @NotNull(message = "O endereço de entrega é obrigatório.") EnderecoEntregaModel toModel(EnderecoEntregaCreateDto dto){
		EnderecoEntregaModel model = new EnderecoEntregaModel();
		model.setRua(dto.rua());
		model.setNumero(dto.numero());
		model.setCidade(dto.cidade());
		model.setBairro(dto.bairro());
		return model;
	}
	
	public static EnderecoEntregaDto toDto(EnderecoEntregaModel model){
		return new EnderecoEntregaDto(
			model.getId(),
			model.getRua(),
			model.getNumero(),
			model.getCidade(),
			model.getBairro()
		);
	}
}
