package com.example.concessionaria.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.concessionaria.dto.endereco.EnderecoEntregaDto;
import com.example.concessionaria.mapper.EnderecoEntregaMapper;
import com.example.concessionaria.model.EnderecoEntregaModel;
import com.example.concessionaria.repository.VendaRepository;
import com.example.concessionaria.service.EnderecoEntregaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/concessionaria/enderecos")
public class EnderecoEntregaController{

	private final EnderecoEntregaService enderecoService;
	private final VendaRepository vendaRepository;

	public EnderecoEntregaController(EnderecoEntregaService enderecoService,VendaRepository vendaRepository) {
		this.enderecoService = enderecoService;
		this.vendaRepository = vendaRepository;
		
	}
	
	@PostMapping
	public ResponseEntity<EnderecoEntregaDto> criarEndereco(@Valid @RequestBody EnderecoEntregaDto dto){
		EnderecoEntregaModel salvo = enderecoService.criarEndereco(dto);
		EnderecoEntregaDto response = EnderecoEntregaMapper.toDto(salvo);
		return ResponseEntity.status(201).body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<EnderecoEntregaDto>> listarTodos(){
		List<EnderecoEntregaDto> lista = enderecoService.listarTodos().stream()
			.map(EnderecoEntregaMapper::toDto)
			.toList();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EnderecoEntregaDto> buscarPorId(@PathVariable UUID id){
		return enderecoService.buscarPorId(id)
				.map(endereco -> ResponseEntity.ok(EnderecoEntregaMapper.toDto(endereco)))
				.orElse(ResponseEntity.notFound().build());
	}
	@GetMapping("/bairro/{bairro}")
	public ResponseEntity<List<EnderecoEntregaDto>> buscarPorBairro(@PathVariable String bairro){
		List<EnderecoEntregaDto> lista = enderecoService.buscarPorBairro(bairro)
			.getBody()
			.stream()
			.map(EnderecoEntregaMapper::toDto)
			.toList();
		return ResponseEntity.ok(lista);
	} 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarPorId(@PathVariable UUID id){
			Optional<EnderecoEntregaModel> endereco = enderecoService.buscarPorId(id);
			
			if(endereco.isPresent()){
				boolean emUso = vendaRepository.findAll().stream()
					.anyMatch(v -> v.getEndereco() != null && v.getId().equals(id));
				
				if(emUso){
					throw new IllegalStateException("Endereço em uso em uma venda. Não pode ser deletado.");
				}
				
				enderecoService.deletarPorId(id);
				return ResponseEntity.ok("Endereço deletado com sucesso.");
			}
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço não encontrado.");
		}
		
	}
	


