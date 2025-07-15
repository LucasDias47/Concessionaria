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
import com.example.concessionaria.dto.cliente.ClienteResponseDto;
import com.example.concessionaria.mapper.ClienteMapper;
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
	public ResponseEntity<ClienteResponseDto>criarCliente(@RequestBody ClienteRecordDto dto){
		Optional<ClienteModel> existente = clienteService.buscarPorCpf(dto.cpf());
		
		if(existente.isPresent()) {
			ClienteResponseDto response = ClienteMapper.toResponseDto(existente.get());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
		}
		
		ClienteResponseDto novo = clienteService.criarCliente(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(novo);
	}
	
	@GetMapping("/{cpf}")
	public ResponseEntity<?> buscarPorCpf(@PathVariable String cpf){
		Optional<ClienteModel>cliente = clienteService.buscarPorCpf(cpf);
		
		if(cliente.isPresent()) {
			ClienteResponseDto dto = ClienteMapper.toResponseDto(cliente.get());
			return ResponseEntity.ok(dto);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarCliente(@PathVariable UUID id){
		Optional<ClienteModel> cliente = clienteService.buscarPorId(id);
		if(cliente.isPresent()) {
			clienteService.deletarCliente(id);
			return ResponseEntity.ok("Cliente deletado com sucesso.");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteRecordDto>> listarTodos(){
		return ResponseEntity.ok(clienteService.listarClientes());
	}
	
}
