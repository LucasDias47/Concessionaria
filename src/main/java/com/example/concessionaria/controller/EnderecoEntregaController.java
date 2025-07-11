package com.example.concessionaria.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.concessionaria.dto.endereco.EnderecoEntregaDto;
import com.example.concessionaria.model.EnderecoEntregaModel;
import com.example.concessionaria.service.EnderecoEntregaService;

@RestController
@RequestMapping("/concessionaria/enderecos")
public class EnderecoEntregaController{

	private final EnderecoEntregaService enderecoService;

	public EnderecoEntregaController(EnderecoEntregaService enderecoService) {
		this.enderecoService = enderecoService;
	}
	
	@PostMapping
	public ResponseEntity<EnderecoEntregaModel> criarEndereco(@RequestBody EnderecoEntregaDto dto){
		EnderecoEntregaModel salvo = enderecoService.criarEndereco(dto);
		return ResponseEntity.status(201).body(salvo);
	}
	
	@GetMapping
	public ResponseEntity<List<EnderecoEntregaModel>> listarTodos(){
		return ResponseEntity.ok(enderecoService.listarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EnderecoEntregaModel> buscarPorId(@PathVariable UUID id){
		return enderecoService.buscarPorId(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	@GetMapping("/bairro/{bairro}")
	public ResponseEntity<List<EnderecoEntregaModel>> buscarPorBairro(@PathVariable String bairro){
		return enderecoService.buscarPorBairro(bairro);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarPorId(@PathVariable UUID id){
		if(enderecoService.deletarPorId(id)) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
}

