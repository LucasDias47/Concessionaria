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

import com.example.concessionaria.dto.cliente.ClienteRecordDto;
import com.example.concessionaria.model.ClienteModel;
import com.example.concessionaria.service.ClienteService;

@RestController
@RequestMapping("/concessionaria/clientes")
public class ClienteController {

	private final ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@PostMapping
	public ResponseEntity<ClienteModel>criarCliente(@RequestBody ClienteRecordDto dto){
		Optional<ClienteModel> existente = clienteService.buscarPorCpf(dto.cpf());
		if(existente.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(existente.get());
		}
		
		ClienteModel novo = clienteService.criarCliente(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(novo);
	}
	
	@GetMapping("/{cpf}")
	public ResponseEntity<ClienteModel> buscarPorCpf(@PathVariable String cpf){
		return clienteService.buscarPorCpf(cpf)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarCliente(@PathVariable UUID id){
		Optional<ClienteModel> cliente = clienteService.buscarPorId(id);
		if(cliente.isPresent()) {
			clienteService.deletarCliente(id);	
		}
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteModel>> listarTodos(){
		return ResponseEntity.ok(clienteService.listarClientes());
	}
	
}
