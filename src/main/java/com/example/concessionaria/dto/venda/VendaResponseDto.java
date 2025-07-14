package com.example.concessionaria.dto.venda;
import com.example.concessionaria.dto.carro.CarroRecordDto;
import com.example.concessionaria.dto.cliente.ClienteRecordDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record VendaResponseDto(
    UUID id,
    LocalDate dataVenda,
    BigDecimal valorTotal,
    ClienteRecordDto cliente,
    List<CarroRecordDto> carros
) {

}
