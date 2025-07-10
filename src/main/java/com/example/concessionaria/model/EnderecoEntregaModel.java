package com.example.concessionaria.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name="TB_ENDERECO_ENTREGA")
public class EnderecoEntregaModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(nullable = false)
	private String rua;

	@Column(nullable = false)
	private Integer numero;

	@Column(nullable = false)
	private String cidade;
	
	@Column(nullable = false)
	private String bairro;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "endereco", fetch = FetchType.LAZY)
	private Set<VendaModel> vendas = new HashSet<>();

	// Getters e Setters

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Set<VendaModel> getVendas() {
		return vendas;
	}

	public void setVendas(Set<VendaModel> vendas) {
		this.vendas = vendas;
	}

	// equals e hashCode
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof EnderecoEntregaModel)) return false;
		EnderecoEntregaModel that = (EnderecoEntregaModel) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
