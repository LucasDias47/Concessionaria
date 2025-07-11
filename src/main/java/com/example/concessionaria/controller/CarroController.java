package com.example.concessionaria.controller;

import java.util.List;
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
	public ResponseEntity<CarroModel> criarCarro(@RequestBody CarroRecordDto dto){
		
		CarroModel carro = new CarroModel();
		carro.setModelo(dto.modelo());
		carro.setPlaca(dto.placa());
		CarroModel salvo = carroService.salvar(carro);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
		
	}
	
	@GetMapping
	public ResponseEntity<List<CarroModel>> listarCarros(){
		return ResponseEntity.ok(carroService.listarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CarroModel> buscarPorId(@PathVariable UUID id){
		return carroService.buscarPorId(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/modelo/{modelo}")
	public ResponseEntity<List<CarroModel>> buscarPorModelo(@PathVariable String modelo){
		return carroService.buscaPorModelo(modelo)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable UUID id){
		if(carroService.buscarPorId(id).isPresent()) {
			carroService.deletar(id);
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}

}
