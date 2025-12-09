package pe.edu.tecsup.bancodigital.infrastructure.adapters.output.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.bancodigital.application.ports.output.AccountRepositoryPort;
import pe.edu.tecsup.bancodigital.application.ports.output.ClientRepositoryPort;
import pe.edu.tecsup.bancodigital.application.ports.output.TransactionRepositoryPort;
import pe.edu.tecsup.bancodigital.domain.model.Account;
import pe.edu.tecsup.bancodigital.domain.model.Client;
import pe.edu.tecsup.bancodigital.domain.model.Transaction;
import pe.edu.tecsup.bancodigital.infrastructure.adapters.output.persistence.entity.AccountEntity;
import pe.edu.tecsup.bancodigital.infrastructure.adapters.output.persistence.entity.ClientEntity;
import pe.edu.tecsup.bancodigital.infrastructure.adapters.output.persistence.mapper.PersistenceMapper;
import pe.edu.tecsup.bancodigital.infrastructure.adapters.output.persistence.repository.SpringDataAccountRepository;
import pe.edu.tecsup.bancodigital.infrastructure.adapters.output.persistence.repository.SpringDataClientRepository;
import pe.edu.tecsup.bancodigital.infrastructure.adapters.output.persistence.repository.SpringDataTransactionRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaPersistenceAdapter implements AccountRepositoryPort, ClientRepositoryPort, TransactionRepositoryPort {

    private final SpringDataAccountRepository accountRepository;
    private final SpringDataClientRepository clientRepository;
    private final SpringDataTransactionRepository transactionRepository;
    private final PersistenceMapper mapper;

    @Override
    public Client save(Client client) {
        ClientEntity entity = mapper.toClientEntity(client);
        return mapper.toClientDomain(clientRepository.save(entity));
    }

    @Override
    public Optional<Client> findClientById(String id) {
        return clientRepository.findById(id).map(mapper::toClientDomain);
    }

    @Override
    public boolean existsById(String id) {
        return clientRepository.existsById(id);
    }

    // --- CUENTA ---
    @Override
    public Account save(Account account) {
        AccountEntity entity = mapper.toAccountEntity(account);
        return mapper.toAccountDomain(accountRepository.save(entity));
    }

    @Override
    public Optional<Account> findAccountById(String id) {
        return accountRepository.findById(id).map(mapper::toAccountDomain);
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return accountRepository.findByNumeroCuenta(accountNumber).map(mapper::toAccountDomain);
    }

    @Override
    public boolean existsByAccountNumber(String accountNumber) {
        return accountRepository.existsByNumeroCuenta(accountNumber);
    }

    // --- TRANSACCION ---
    @Override
    public Transaction save(Transaction transaction) {
        transactionRepository.save(mapper.toTransactionEntity(transaction));
        return transaction;
    }
}