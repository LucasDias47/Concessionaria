package com.example.concessionaria.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.concessionaria.dto.ClienteRecordDto;
import com.example.concessionaria.dto.VendaRecordDto;
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

	public ClienteModel criarCliente(ClienteRecordDto dto) {
		ClienteModel novo = new ClienteModel();
		novo.setNome(dto.nome());
		novo.setCpf(dto.cpf());
		return clienteRepository.save(novo);
	}
	
	public List<ClienteModel> listarClientes(){
		return clienteRepository.findAll();
	}
	
	public void deletarCliente(UUID id) {
		clienteRepository.deleteById(id);
	}
	
	
}





