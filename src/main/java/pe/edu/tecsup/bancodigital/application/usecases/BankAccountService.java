package pe.edu.tecsup.bancodigital.application.usecases;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.edu.tecsup.bancodigital.application.ports.input.CreateAccountUseCase;
import pe.edu.tecsup.bancodigital.application.ports.input.GetAccountUseCase;
import pe.edu.tecsup.bancodigital.application.ports.input.TransferUseCase;
import pe.edu.tecsup.bancodigital.application.ports.output.AccountRepositoryPort;
import pe.edu.tecsup.bancodigital.application.ports.output.ClientRepositoryPort;
import pe.edu.tecsup.bancodigital.application.ports.output.NotificationPort;
import pe.edu.tecsup.bancodigital.application.ports.output.TransactionRepositoryPort;
import pe.edu.tecsup.bancodigital.domain.model.Account;
import pe.edu.tecsup.bancodigital.domain.model.Client;
import pe.edu.tecsup.bancodigital.domain.model.Transaction;
import pe.edu.tecsup.bancodigital.domain.model.vo.Money;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor // Inyección de dependencias por constructor automática de Lombok
@Slf4j
public class BankAccountService implements CreateAccountUseCase, TransferUseCase, GetAccountUseCase {

    private final ClientRepositoryPort clientRepository;
    private final AccountRepositoryPort accountRepository;
    private final TransactionRepositoryPort transactionRepository;
    private final NotificationPort notificationPort;

    @Override
    public Account create(CreateAccountCommand command) {
        // 1. Validar que el cliente exista (Integridad referencial)
        if (clientRepository.findClientById(command.getClientId()).isEmpty()) {
            throw new IllegalArgumentException("Cliente no encontrado: " + command.getClientId());
        }

        // 2. Generar datos únicos
        String accountId = UUID.randomUUID().toString();
        String accountNumber = generateUniqueAccountNumber();

        // 3. Crear Entidad de Dominio
        Account newAccount = Account.createNew(
                accountId,
                command.getClientId(),
                accountNumber,
                Money.of(command.getInitialBalance().doubleValue())
        );

        // 4. Guardar
        return accountRepository.save(newAccount);
    }

    @Override
    public Transaction transfer(TransferCommand command) {
        // 1. Recuperar Cuentas
        Account source = accountRepository.findAccountById(command.getSourceAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Cuenta origen no existe"));

        Account target = accountRepository.findByAccountNumber(command.getTargetAccountNumber())
                .orElseThrow(() -> new IllegalArgumentException("Cuenta destino no existe"));

        if (source.getId().equals(target.getId())) {
            throw new IllegalArgumentException("No puedes transferir a la misma cuenta");
        }

        // 2. Preparar Montos (Value Objects)
        Money transferAmount = Money.of(command.getAmount().doubleValue());
        Money commission = Money.of(5.00); // Regla de negocio fija
        Money totalDebit = transferAmount.add(commission);

        // 3. Ejecutar Lógica de Dominio (Aquí salta la Excepción si no hay saldo)
        // Origen paga: Monto + Comisión
        source.debit(totalDebit);
        // Destino recibe: Solo Monto
        target.credit(transferAmount);

        // 4. Persistir Cambios de Saldo
        accountRepository.save(source);
        accountRepository.save(target);

        // 5. Crear y Guardar Transacción (Auditoría)
        Transaction transaction = Transaction.createTransfer(
                UUID.randomUUID().toString(),
                source.getId(),
                target.getId(),
                transferAmount,
                command.getDescription()
        );
        transactionRepository.save(transaction);

        // 6. Notificar
        notificationPort.notify("Transferencia realizada: " + transferAmount.amount() + " (+5.00 comisión)");

        return transaction;
    }

    // Método auxiliar simple para generar número de cuenta
    private String generateUniqueAccountNumber() {
        return String.valueOf(System.currentTimeMillis()).substring(3);
    }

    @Override
    public Account getAccount(String id) {
        // Usamos el puerto de salida que ya teníamos (AccountRepositoryPort)
        return accountRepository.findAccountById(id)
                .orElseThrow(() -> new IllegalArgumentException("La cuenta con ID " + id + " no existe."));
    }

    @Override
    public List<Account> getAccountsByClient(String searchCriteria) {
        // 1. Buscamos clientes que coincidan con el texto (ID, Nombre o DNI)
        List<Client> foundClients = clientRepository.searchClients(searchCriteria);

        if (foundClients.isEmpty()) {
            return List.of(); // Lista vacía si no hay coincidencias
        }

        // 2. Para cada cliente encontrado, buscamos sus cuentas
        List<Account> allAccounts = new ArrayList<>();

        for (Client client : foundClients) {
            List<Account> accounts = accountRepository.findByClientId(client.getId());
            allAccounts.addAll(accounts);
        }

        return allAccounts;
    }
}