package com.example.concessionaria.mapper;

import com.example.concessionaria.dto.carro.CarroRecordDto;
import com.example.concessionaria.dto.carro.CarroResponseDto;
import com.example.concessionaria.model.CarroModel;

public class CarroMapper {
	
	public static CarroModel toEntity(CarroRecordDto dto) {
		CarroModel carro = new CarroModel();
		carro.setModelo(dto.modelo());
		carro.setPlaca(dto.placa());
		return carro;
	}
	
	public static CarroResponseDto toResponseDto(CarroModel carro){
		return new CarroResponseDto(
				carro.getId(),
				carro.getModelo(),
				carro.getPlaca()
		);
	}

}
