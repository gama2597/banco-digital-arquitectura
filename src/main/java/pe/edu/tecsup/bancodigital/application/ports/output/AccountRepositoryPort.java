package pe.edu.tecsup.bancodigital.application.ports.output;

import pe.edu.tecsup.bancodigital.domain.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepositoryPort {
    Account save(Account account);
    Optional<Account> findAccountById(String id);
    Optional<Account> findByAccountNumber(String accountNumber);
    // NUEVO MÉTODO
    List<Account> findByClientId(String clientId);
    boolean existsByAccountNumber(String accountNumber);
}