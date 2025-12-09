package pe.edu.tecsup.bancodigital.application.ports.input;

import pe.edu.tecsup.bancodigital.domain.model.Account;

public interface GetAccountUseCase {
    /**
     * Permite consultar el saldo y estado de una cuenta.
     * @param id ID de la cuenta (Ej: CTA001)
     * @return La entidad Cuenta del dominio
     */
    Account getAccount(String id);
}