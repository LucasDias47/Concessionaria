package com.example.concessionaria.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.example.concessionaria.dto.carro.CarroRecordDto;
import com.example.concessionaria.dto.venda.VendaRecordDto;
import com.example.concessionaria.dto.venda.VendaRequestDto;
import com.example.concessionaria.dto.venda.VendaResponseDto;
import com.example.concessionaria.model.VendaModel;
import com.example.concessionaria.service.VendaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/concessionaria/vendas")

public class VendaController {

	private final VendaService vendaService;

	public VendaController(VendaService vendaService) {
		
		this.vendaService = vendaService;
	}
	
	@PostMapping
	public ResponseEntity<VendaResponseDto> saveVenda(@RequestBody @Valid VendaRecordDto dto) {
	    VendaResponseDto response = vendaService.saveVendaComTudo(dto);
	    return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<VendaResponseDto>>listarVendas(){
		List<VendaResponseDto> vendas = vendaService.listarTodas();
		return ResponseEntity.ok(vendas);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String>deleteVenda(@PathVariable UUID id){
		boolean removido = vendaService.deleteVenda(id);
		if (removido) {
			return ResponseEntity.ok("Venda deletada com sucesso");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venda não encontrada");
	}
	}
	
}
