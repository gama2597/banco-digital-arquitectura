package pe.edu.tecsup.bancodigital.infrastructure.adapters.input.rest.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record CreateAccountRequest(
        @NotBlank(message = "El ID del cliente es obligatorio")
        String clientId,

        @NotNull(message = "El saldo inicial es obligatorio")
        @DecimalMin(value = "0.0", message = "El saldo no puede ser negativo")
        BigDecimal initialBalance
) {}