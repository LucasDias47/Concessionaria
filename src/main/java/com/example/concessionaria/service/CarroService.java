package com.example.concessionaria.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.concessionaria.dto.carro.CarroRecordDto;
import com.example.concessionaria.dto.carro.CarroResponseDto;
import com.example.concessionaria.mapper.CarroMapper;
import com.example.concessionaria.model.CarroModel;
import com.example.concessionaria.repository.CarroRepository;

@Service
public class CarroService {
	
	private final CarroRepository carroRepository;

	public CarroService(CarroRepository carroRepository) {
		this.carroRepository = carroRepository;
	}
	
	public List<CarroResponseDto> listarTodos(){
		return carroRepository.findAll()
				.stream()
				.map(CarroMapper::toResponseDto)
				.collect(Collectors.toList());
	}
	
	public Optional<CarroModel> buscarPorId(UUID id){
		return carroRepository.findById(id);
	}
	
	public Optional<List<CarroModel>> buscaPorModelo(String modelo){
		return Optional.ofNullable(carroRepository.findAllByModelo(modelo));
	}
	// Método usado por controladores externos — retorna DTO
	public CarroResponseDto criarCarro(CarroRecordDto dto) {
		CarroModel carro = CarroMapper.toEntity(dto);
		carroRepository.save(carro);
		return CarroMapper.toResponseDto(carro);	
	}
	
	public CarroModel criar(CarroRecordDto dto){
		CarroModel carro = CarroMapper.toEntity(dto);
		return carroRepository.save(carro);
	}
	
	public CarroModel salvar(CarroModel carro) {
		return carroRepository.save(carro);
	}
	
	public void deletar(UUID id) {
		carroRepository.deleteById(id);
	}
	
	

}
