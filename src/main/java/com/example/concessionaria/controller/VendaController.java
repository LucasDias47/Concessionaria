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
	
	@GetMapping
	public ResponseEntity<Map<String, Object>>getAllVendas(){
		List<VendaModel> vendas = vendaService.getAllVendas();
		
		Map<String, Object> resposta = new HashMap<>();
		resposta.put("mensagem","Lista de vendas recuperada com sucesso");
		resposta.put("vendas", vendas);
		
		return ResponseEntity.status(HttpStatus.OK).body(resposta);
	}
	
	@PostMapping
	public ResponseEntity<VendaResponseDto> saveVenda(@RequestBody @Valid VendaRecordDto dto) {
	    VendaResponseDto response = vendaService.saveVendaComTudo(dto);
	    return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String>deleteVenda(@PathVariable UUID id){
		vendaService.deleteVenda(id);
		return ResponseEntity.status(HttpStatus.OK).body("Venda deletada com successo");	
	}
	
}
