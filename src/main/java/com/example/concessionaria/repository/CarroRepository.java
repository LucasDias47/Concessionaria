package com.example.concessionaria.repository;

import java.util.List;
import java.util.UUID;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.concessionaria.model.CarroModel;

public interface CarroRepository extends JpaRepository<CarroModel, UUID>{

	List<CarroModel> findAllByModelo(String modelo);
}

	

