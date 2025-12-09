package pe.edu.tecsup.bancodigital.domain.model;

import lombok.Builder;
import lombok.Getter;
import pe.edu.tecsup.bancodigital.domain.model.vo.Money;
import java.time.LocalDateTime;

@Getter
@Builder
public class Transaction {

    private final String id;                // transaccion_id
    private final String sourceAccountId;   // cuenta_origen_id
    private final String targetAccountId;   // cuenta_destino_id
    private final Money amount;             // monto
    private final Money commission;         // comision (Regla: $5.00)
    private final String type;              // tipo (TRANSFERENCIA, DEPOSITO, RETIRO)
    private final String state;             // estado (PENDIENTE, COMPLETADA, FALLIDA)
    private final String description;       // descripcion
    private final LocalDateTime createdAt;  // fecha_creacion

    // Factory method para crear una transferencia estandarizada
    public static Transaction createTransfer(String id, String sourceId, String targetId, Money amount, String description) {
        return Transaction.builder()
                .id(id)
                .sourceAccountId(sourceId)
                .targetAccountId(targetId)
                .amount(amount)
                .commission(Money.of(5.00)) // Comisión fija de $5.00
                .type("TRANSFERENCIA")      // Check constraint: tipo IN ('TRANSFERENCIA'...)
                .state("COMPLETADA")        // Check constraint: estado IN ('COMPLETADA'...)
                .description(description)
                .createdAt(LocalDateTime.now())
                .build();
    }
}