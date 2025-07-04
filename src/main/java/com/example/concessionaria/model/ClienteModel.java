package com.example.concessionaria.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_CLIENTE")
public class ClienteModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false, unique = true)
	private String cpf;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<CarroModel> carros = new HashSet<>();

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<VendaModel> vendas = new HashSet<>();

	// Getters e Setters

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Set<CarroModel> getCarros() {
		return carros;
	}

	public void setCarros(Set<CarroModel> carros) {
		this.carros = carros;
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
		if (!(o instanceof ClienteModel)) return false;
		ClienteModel that = (ClienteModel) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
