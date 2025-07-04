package com.example.concessionaria.service;

import java.util.ArrayList;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.concessionaria.dto.CarroRecordDto;
import com.example.concessionaria.dto.VendaRecordDto;
import com.example.concessionaria.dto.VendaResponseDto;
import com.example.concessionaria.model.CarroModel;
import com.example.concessionaria.model.ClienteModel;
import com.example.concessionaria.model.EnderecoEntregaModel;
import com.example.concessionaria.model.VendaModel;
import com.example.concessionaria.repository.CarroRepository;
import com.example.concessionaria.repository.ClienteRepository;
import com.example.concessionaria.repository.EnderecoEntregaRepository;
import com.example.concessionaria.repository.VendaRepository;

import jakarta.transaction.Transactional;

@Service
public class VendaService {

	private final ClienteRepository clienteRepository;
	private final CarroRepository carroRepository;
	private final VendaRepository vendaRepository;
	private final EnderecoEntregaRepository enderecoEntregaRepository;

	public VendaService(ClienteRepository clienteRepository, CarroRepository carroRepository,
						VendaRepository vendaRepository, EnderecoEntregaRepository enderecoEntregaRepository) {
		this.clienteRepository = clienteRepository;
		this.carroRepository = carroRepository;
		this.vendaRepository = vendaRepository;
		this.enderecoEntregaRepository = enderecoEntregaRepository;
	}

	public List<CarroModel> getAllCarros() {
		return carroRepository.findAll();
	}

	@Transactional
	public VendaModel saveVendaComTudo(VendaRecordDto dto) {
		
		// 1. Verifica se o cliente já existe pelo CPF
		Optional<ClienteModel> optionalCliente = clienteRepository.findByCpf(dto.cliente().cpf());
		ClienteModel cliente;

		if (optionalCliente.isPresent()) {
			cliente = optionalCliente.get();
		} else {
			cliente = new ClienteModel();
			cliente.setNome(dto.cliente().nome());
			cliente.setCpf(dto.cliente().cpf());
			clienteRepository.save(cliente);
		}
		
		// 2. Salva o cliente
		EnderecoEntregaModel endereco = new EnderecoEntregaModel();
		endereco.setRua(dto.enderecoEntrega().rua());
		endereco.setNumero(dto.enderecoEntrega().numero());
		endereco.setCidade(dto.enderecoEntrega().cidade());
		enderecoEntregaRepository.save(endereco);

		// 3. Salva o endereço de entrega
		 endereco = new EnderecoEntregaModel();
		endereco.setRua(dto.enderecoEntrega().rua());
		endereco.setNumero(dto.enderecoEntrega().numero());
		endereco.setCidade(dto.enderecoEntrega().cidade());
		enderecoEntregaRepository.save(endereco);

		// 4. Salva os carros
		Set<CarroModel> carros = new HashSet<>();
		for (CarroRecordDto carroDto : dto.carros()) {
			CarroModel carro = new CarroModel();
			carro.setModelo(carroDto.modelo());
			carro.setPlaca(carroDto.placa());
			carro.setCliente(cliente); // associa ao cliente
			carroRepository.save(carro);
			carros.add(carro);
		}

		// 5. Cria e salva a venda
		VendaModel venda = new VendaModel();
		venda.setDataVenda(dto.dataVenda());
		venda.setValorTotal(dto.valorTotal());
		venda.setEndereco(endereco);
		venda.setCliente(cliente);
		venda.setCarros(carros);

		return vendaRepository.save(venda);
	}
	
	public List<VendaModel> getAllVendas() {
	    return vendaRepository.findAll();
	}

	public void deleteVenda(UUID id) {
	    if (vendaRepository.existsById(id)) {
	        vendaRepository.deleteById(id);
	    } else {
	        throw new RuntimeException("Venda com ID " + id + " não encontrada.");
	    }
	}
	

	}
	

