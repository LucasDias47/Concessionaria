package com.example.concessionaria.controller;

import java.util.List;

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
import com.example.concessionaria.dto.CarroRecordDto;
import com.example.concessionaria.dto.VendaRecordDto;
import com.example.concessionaria.dto.VendaRequestDto;
import com.example.concessionaria.model.VendaModel;
import com.example.concessionaria.service.VendaService;

@RestController
@RequestMapping("/concessionaria/vendas")

public class VendaController {

	private final VendaService vendaService;

	public VendaController(VendaService vendaService) {
		
		this.vendaService = vendaService;
	}
	
	@GetMapping
	public ResponseEntity<List<VendaModel>>getAllVendas(){
		return ResponseEntity.status(HttpStatus.OK).body(vendaService.getAllVendas());
	}
	
	@PostMapping
	public ResponseEntity<VendaModel>saveVenda(@RequestBody VendaRecordDto dto){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(vendaService.saveVendaComTudo(dto));
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String>deleteVenda(@PathVariable UUID id){
		vendaService.deleteVenda(id);
		return ResponseEntity.status(HttpStatus.OK).body("Venda deletada com successo");	
	}
	
}
