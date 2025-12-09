package pe.edu.tecsup.bancodigital.infrastructure.adapters.input.rest.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record TransferRequest(
        @NotBlank(message = "Cuenta origen obligatoria")
        String sourceAccountId,

        @NotBlank(message = "Cuenta destino obligatoria")
        String targetAccountNumber,

        @NotNull
        @DecimalMin(value = "0.01", message = "El monto debe ser mayor a 0")
        BigDecimal amount,

        String description
) {}