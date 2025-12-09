package pe.edu.tecsup.bancodigital.application.ports.input;

import lombok.Builder;
import lombok.Getter;
import pe.edu.tecsup.bancodigital.domain.model.Account;
import java.math.BigDecimal;

public interface CreateAccountUseCase {

    Account create(CreateAccountCommand command);

    @Getter
    @Builder
    class CreateAccountCommand {
        private String clientId;
        private BigDecimal initialBalance;
    }
}