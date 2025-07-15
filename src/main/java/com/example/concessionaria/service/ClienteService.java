package com.example.concessionaria.service;

import java.util.List;


import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.concessionaria.dto.cliente.ClienteRecordDto;
import com.example.concessionaria.dto.cliente.ClienteResponseDto;
import com.example.concessionaria.mapper.ClienteMapper;
import com.example.concessionaria.model.ClienteModel;
import com.example.concessionaria.repository.ClienteRepository;


@Service
public class ClienteService {
	private final ClienteRepository clienteRepository;

	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	public Optional<ClienteModel> buscarPorCpf(String cpf){
		return clienteRepository.findByCpf(cpf);
	}

	public Optional<ClienteModel> buscarPorId(UUID id){
		return clienteRepository.findById(id);
	}

	public ClienteResponseDto criarCliente(ClienteRecordDto dto) {
		ClienteModel novo = new ClienteModel();
		novo.setNome(dto.nome());
		novo.setCpf(dto.cpf());
		return ClienteMapper.toResponseDto(clienteRepository.save(novo));
	}
	
	public ClienteModel salvarModel(ClienteModel model) {
		return clienteRepository.save(model);
	}
	
	public List<ClienteRecordDto> listarClientes(){
		return clienteRepository.findAll().stream()
			.map(ClienteMapper::toDto)
			.collect(Collectors.toList());
	}
	
	public void deletarCliente(UUID id) {
		clienteRepository.deleteById(id);
	}
	
	
}





