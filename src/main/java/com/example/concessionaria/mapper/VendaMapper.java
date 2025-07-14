package com.example.concessionaria.mapper;

import com.example.concessionaria.dto.carro.CarroRecordDto;

import com.example.concessionaria.dto.cliente.ClienteRecordDto;

import com.example.concessionaria.dto.venda.VendaRecordDto;
import com.example.concessionaria.dto.venda.VendaResponseDto;

import com.example.concessionaria.model.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class VendaMapper {

	public static VendaModel toModel(
			VendaRecordDto dto,
			ClienteModel cliente,
			EnderecoEntregaModel endereco,
			Set<CarroModel> carros
	){
		VendaModel venda = new VendaModel();
		venda.setDataVenda(dto.dataVenda());
		venda.setValorTotal(dto.valorTotal());
		venda.setCliente(cliente);
		venda.setEndereco(endereco);
		venda.setCarros(carros);
		return venda;		
	}

	public static VendaResponseDto toResponseDto(VendaModel venda) {
		ClienteRecordDto clienteDto = new ClienteRecordDto(
				venda.getCliente().getNome(),
				venda.getCliente().getCpf()
		);
		
		List<CarroRecordDto> carrosDto = venda.getCarros().stream()
				.map(c -> new CarroRecordDto(c.getModelo(), c.getPlaca()))
				.collect(Collectors.toList());
		
		return new VendaResponseDto(
				venda.getId(),
				venda.getDataVenda(),
				BigDecimal.valueOf(venda.getValorTotal()),
				clienteDto,
				carrosDto
		);
	}
}


