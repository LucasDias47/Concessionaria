package com.example.concessionaria.dto.venda;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record VendaResponseDto(
    UUID id,
    LocalDate dataVenda,
    BigDecimal valorTotal,
    ClienteDto cliente,
    List<CarroDto> carros
) {

    public record ClienteDto(String nome, String cpf) {}
    public record CarroDto(String modelo, String placa) {}

}
