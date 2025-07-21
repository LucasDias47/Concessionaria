package com.example.concessionaria.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.concessionaria.dto.endereco.EnderecoEntregaCreateDto;
import com.example.concessionaria.dto.endereco.EnderecoEntregaDto;
import com.example.concessionaria.mapper.EnderecoEntregaMapper;
import com.example.concessionaria.model.EnderecoEntregaModel;
import com.example.concessionaria.repository.EnderecoEntregaRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class EnderecoEntregaService {

	private final EnderecoEntregaRepository enderecoEntregaRepository;

	public EnderecoEntregaService(EnderecoEntregaRepository enderecoEntregaRepository) {
		this.enderecoEntregaRepository = enderecoEntregaRepository;
	}

	public EnderecoEntregaModel criarEndereco(EnderecoEntregaModel model) {
	    return enderecoEntregaRepository.save(model);
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


