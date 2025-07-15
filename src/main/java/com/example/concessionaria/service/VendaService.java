package com.example.concessionaria.service;

import java.util.HashSet;



import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.concessionaria.dto.carro.CarroRecordDto;
import com.example.concessionaria.dto.cliente.ClienteRecordDto;
import com.example.concessionaria.dto.cliente.ClienteResponseDto;
import com.example.concessionaria.dto.venda.VendaRecordDto;
import com.example.concessionaria.dto.venda.VendaResponseDto;
import com.example.concessionaria.model.CarroModel;
import com.example.concessionaria.model.ClienteModel;
import com.example.concessionaria.model.EnderecoEntregaModel;
import com.example.concessionaria.model.VendaModel;
import com.example.concessionaria.repository.EnderecoEntregaRepository;
import com.example.concessionaria.repository.VendaRepository;
import com.example.concessionaria.mapper.ClienteMapper;
import com.example.concessionaria.mapper.VendaMapper;

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
	public VendaResponseDto saveVendaComTudo(VendaRecordDto dto) {

		// 1. Buscar cliente pelo CPF
		ClienteModel clienteExistenteOuNovo;
		Optional<ClienteModel> clienteExistente = clienteService.buscarPorCpf(dto.cliente().cpf());
		
		
		if(clienteExistente.isPresent()) {
			clienteExistenteOuNovo = clienteExistente.get();
		}else {
			clienteExistenteOuNovo = ClienteMapper.toModel(dto.cliente());
			clienteExistenteOuNovo = clienteService.salvarModel(clienteExistenteOuNovo);
		}
		final ClienteModel cliente = clienteExistenteOuNovo;
		
		// 2. Salva o endereço de entrega
		EnderecoEntregaModel endereco = enderecoEntregaService.criarEndereco(dto.enderecoEntrega());

		// 3. Salva os carros
		Set<CarroModel> carros = dto.carros().stream()
			.map(carroDto -> {
				CarroModel carro = carroService.criar(carroDto);
				carro.setCliente(cliente);
				return carro;
			}).collect(Collectors.toSet());
		

		// 4. Cria e salva a venda
		VendaModel venda = new VendaModel();
		venda.setDataVenda(dto.dataVenda());
		venda.setValorTotal(dto.valorTotal());
		venda.setEndereco(endereco);
		venda.setCliente(cliente);
		venda.setCarros(carros);

		

		// 5. Retornar DTO de resposta
		VendaModel vendaSalva = vendaRepository.save(venda);
		return VendaMapper.toResponseDto(vendaSalva);
	}
	
	public List<VendaResponseDto> listarTodas(){
		return vendaRepository.findAll()
			.stream()
			.map(VendaMapper::toResponseDto)
			.toList();
	}
	
	public boolean deleteVenda(UUID id) {
		if(vendaRepository.existsById(id)) {
			vendaRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
}
	