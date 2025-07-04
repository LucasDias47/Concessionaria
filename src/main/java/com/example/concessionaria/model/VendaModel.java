package com.example.concessionaria.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_VENDA")
public class VendaModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(nullable = false)
	private LocalDate dataVenda;

	@Column(nullable = false)
	private Double valorTotal;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private ClienteModel cliente;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name = "tb_venda_carro",
		joinColumns = @JoinColumn(name = "venda_id"),
		inverseJoinColumns = @JoinColumn(name = "carro_id"))
	private Set<CarroModel> carros = new HashSet<>();

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToOne
	@JoinColumn(name = "endereco_entrega_id")
	private EnderecoEntregaModel endereco;

	// Getters e Setters

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public ClienteModel getCliente() {
		return cliente;
	}

	public void setCliente(ClienteModel cliente) {
		this.cliente = cliente;
	}

	public Set<CarroModel> getCarros() {
		return carros;
	}

	public void setCarros(Set<CarroModel> carros) {
		this.carros = carros;
	}

	public EnderecoEntregaModel getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoEntregaModel endereco) {
		this.endereco = endereco;
	}

	// equals e hashCode
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof VendaModel)) return false;
		VendaModel that = (VendaModel) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
