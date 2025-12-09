package pe.edu.tecsup.bancodigital.infrastructure.adapters.input.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AccountResponse(
        String id,
        String numeroCuenta,
        String clienteId,
        BigDecimal saldo,      // <--- EL REQUISITO "CONSULTAR SALDO"
        String estado,
        LocalDateTime fechaCreacion
) {}