package com.example.concessionaria.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.concessionaria.dto.carro.CarroRecordDto;
import com.example.concessionaria.dto.carro.CarroResponseDto;
import com.example.concessionaria.mapper.CarroMapper;
import com.example.concessionaria.model.CarroModel;
import com.example.concessionaria.service.CarroService;

@RestController
@RequestMapping("/concessionaria/carros")
public class CarroController {
	
	private final CarroService carroService;
	
	public CarroController(CarroService carroService) {
		this.carroService = carroService;
	}
	
	@PostMapping
	public ResponseEntity<CarroResponseDto> criarCarro(@RequestBody CarroRecordDto dto){
		CarroResponseDto response = carroService.criarCarro(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
	}
	
	@GetMapping
	public ResponseEntity<List<CarroResponseDto>> listarCarros(){
		return ResponseEntity.ok(carroService.listarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable UUID id){
		Optional<CarroModel> carro = carroService.buscarPorId(id);
		
		if(carro.isPresent()) {
			CarroResponseDto dto = CarroMapper.toResponseDto(carro.get());
			return ResponseEntity.ok(dto);		
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carro não encontrado.");
	}
	
	@GetMapping("/modelo/{modelo}")
	public ResponseEntity<List<CarroModel>> buscarPorModelo(@PathVariable String modelo){
		return carroService.buscaPorModelo(modelo)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarCarro(@PathVariable UUID id){
		Optional<CarroModel> carro = carroService.buscarPorId(id);
		
		if(carro.isPresent()) {
			carroService.deletar(id);
			return ResponseEntity.ok("Carro deletado com sucesso.");
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carro não encontrado.");
		
	}

}
