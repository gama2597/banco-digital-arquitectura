package pe.edu.tecsup.bancodigital.application.ports.output;

import pe.edu.tecsup.bancodigital.domain.model.Transaction;

public interface TransactionRepositoryPort {
    Transaction save(Transaction transaction);
}