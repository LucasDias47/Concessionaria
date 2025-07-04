package com.example.concessionaria.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_CARRO")
public class CarroModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(nullable = false)
	private String modelo;

	@Column(nullable = false, unique = true)
	private String placa;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private ClienteModel cliente;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToMany(mappedBy = "carros", fetch = FetchType.LAZY)
	private Set<VendaModel> vendas = new HashSet<>();

	// Getters e Setters

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public ClienteModel getCliente() {
		return cliente;
	}

	public void setCliente(ClienteModel cliente) {
		this.cliente = cliente;
	}

	public Set<VendaModel> getVendas() {
		return vendas;
	}

	public void setVendas(Set<VendaModel> vendas) {
		this.vendas = vendas;
	}

	// equals e hashCode baseados no id
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CarroModel)) return false;
		CarroModel that = (CarroModel) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
