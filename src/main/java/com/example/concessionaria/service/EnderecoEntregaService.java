package com.example.concessionaria.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.concessionaria.dto.EnderecoEntregaDto;
import com.example.concessionaria.model.EnderecoEntregaModel;
import com.example.concessionaria.repository.EnderecoEntregaRepository;

@Service
public class EnderecoEntregaService {

	private final EnderecoEntregaRepository enderecoEntregaRepository;

	public EnderecoEntregaService(EnderecoEntregaRepository enderecoEntregaRepository) {
		this.enderecoEntregaRepository = enderecoEntregaRepository;
	}

	public EnderecoEntregaModel criarEndereco(EnderecoEntregaDto dto) {
		EnderecoEntregaModel endereco = new EnderecoEntregaModel();
		endereco.setRua(dto.rua());
		endereco.setNumero(dto.numero());
		endereco.setCidade(dto.cidade());
		endereco.setBairro(dto.bairro());
		return enderecoEntregaRepository.save(endereco);
	}

	public List<EnderecoEntregaModel> listarTodos(){
		return enderecoEntregaRepository.findAll();
	}

	public Optional<EnderecoEntregaModel> buscarPorId(UUID id){
		return enderecoEntregaRepository.findById(id);
	}
	
	public ResponseEntity<List<EnderecoEntregaModel>> buscarPorBairro(String bairro){
		return ResponseEntity.ok(enderecoEntregaRepository.findAllByBairro(bairro));
	}

	public boolean deletarPorId(UUID id) {
		if (enderecoEntregaRepository.existsById(id)) {
			enderecoEntregaRepository.deleteById(id);
			return true;
		}

		return false;

	}

}


