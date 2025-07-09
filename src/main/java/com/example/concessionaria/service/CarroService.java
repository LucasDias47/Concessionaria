package com.example.concessionaria.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.concessionaria.dto.CarroRecordDto;
import com.example.concessionaria.model.CarroModel;
import com.example.concessionaria.repository.CarroRepository;

@Service
public class CarroService {
	
	private final CarroRepository carroRepository;

	public CarroService(CarroRepository carroRepository) {
		this.carroRepository = carroRepository;
	}
	
	public List<CarroModel> listarTodos(){
		return carroRepository.findAll();
	}
	
	public Optional<CarroModel> buscarPorId(UUID id){
		return carroRepository.findById(id);
	}
	
	public Optional<CarroModel> buscaPorModelo(String modelo){
		return Optional.ofNullable(carroRepository.findCarroModelByModelo(modelo));
	}
	
	public CarroModel criar(CarroRecordDto dto) {
		CarroModel carro = new CarroModel();
		carro.setModelo(dto.modelo());
		carro.setPlaca(dto.placa());
		return carroRepository.save(carro);
		
	}
	
	public CarroModel salvar(CarroModel carro) {
		return carroRepository.save(carro);
	}
	
	public void deletar(UUID id) {
		carroRepository.deleteById(id);
	}
	
	

}
