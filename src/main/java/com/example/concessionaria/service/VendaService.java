package com.example.concessionaria.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.concessionaria.dto.CarroRecordDto;
import com.example.concessionaria.dto.VendaRecordDto;
import com.example.concessionaria.model.CarroModel;
import com.example.concessionaria.model.ClienteModel;
import com.example.concessionaria.model.EnderecoEntregaModel;
import com.example.concessionaria.model.VendaModel;
import com.example.concessionaria.repository.EnderecoEntregaRepository;
import com.example.concessionaria.repository.VendaRepository;

import jakarta.transaction.Transactional;

@Service
public class VendaService {

	private final VendaRepository vendaRepository;
	private final EnderecoEntregaService enderecoEntregaService;
	private final ClienteService clienteService;
	private final CarroService carroService;

	public VendaService(CarroService carroService,
			VendaRepository vendaRepository, EnderecoEntregaService enderecoEntregaService, ClienteService clienteService) {

		this.carroService = carroService;
		this.vendaRepository = vendaRepository;
		this.enderecoEntregaService = enderecoEntregaService;
		this.clienteService = clienteService;
	}


	@Transactional
	public VendaModel saveVendaComTudo(VendaRecordDto dto) {

		//1. Buscar cliente pelo CPF
		Optional<ClienteModel> optionalCliente = clienteService.buscarPorCpf(dto.cliente().cpf());
		ClienteModel cliente = optionalCliente.orElseGet(() ->
		clienteService.criarCliente(dto.cliente()));

		// 2. Salva o endereço de entrega
		EnderecoEntregaModel endereco = enderecoEntregaService.criarEndereco(dto.enderecoEntrega());

		// 3. Salva os carros
		Set<CarroModel> carros = new HashSet<>();
		for (CarroRecordDto carroDto : dto.carros()) {
			CarroModel carro = carroService.criar(carroDto);
			carro.setCliente(cliente);
			carros.add(carro);
		}

		// 4. Cria e salva a venda
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


