package com.example.concessionaria.mapper;

import com.example.concessionaria.dto.cliente.ClienteRecordDto;
import com.example.concessionaria.dto.cliente.ClienteResponseDto;
import com.example.concessionaria.model.ClienteModel;

public class ClienteMapper {
	
	public static ClienteRecordDto toDto(ClienteModel model) {
		return new ClienteRecordDto(model.getNome(), model.getCpf());
	}
	
	public static ClienteModel toModel(ClienteRecordDto dto) {
		ClienteModel model = new ClienteModel();
		model.setNome(dto.nome());
		model.setCpf(dto.cpf());
		return model;
	}
	
	public static ClienteResponseDto toResponseDto(ClienteModel model) {
		return new ClienteResponseDto(
			model.getId(),
			model.getNome(),
			model.getCpf()
		);
	}
}
