package com.example.concessionaria.repository;

import java.util.UUID;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.concessionaria.model.VendaModel;

public interface VendaRepository extends JpaRepository<VendaModel, UUID>{

	
}
