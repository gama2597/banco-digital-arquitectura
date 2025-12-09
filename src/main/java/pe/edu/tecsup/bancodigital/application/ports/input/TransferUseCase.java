package pe.edu.tecsup.bancodigital.application.ports.input;

import lombok.Builder;
import lombok.Getter;
import pe.edu.tecsup.bancodigital.domain.model.Transaction;
import java.math.BigDecimal;

public interface TransferUseCase {

    Transaction transfer(TransferCommand command);

    @Getter
    @Builder
    class TransferCommand {
        private String sourceAccountId;
        private String targetAccountNumber; // El usuario suele enviar el número de cuenta destino, no el ID interno
        private BigDecimal amount;
        private String description;
    }
}