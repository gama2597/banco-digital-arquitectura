package pe.edu.tecsup.bancodigital.domain.model;

import lombok.Getter;
import pe.edu.tecsup.bancodigital.domain.exception.InsufficientBalanceException;
import pe.edu.tecsup.bancodigital.domain.model.vo.Money;
import java.time.LocalDateTime;

@Getter
public class Account {

    private final String id;            // cuenta_id
    private final String clientId;      // cliente_id
    private final String accountNumber; // numero_cuenta
    private Money balance;              // saldo
    private String status;              // estado (ACTIVO, CERRADO)
    private final LocalDateTime createdAt; // fecha_creacion
    private LocalDateTime updatedAt;       // fecha_actualizacion

    // Constructor completo para reconstruir desde Base de Datos
    public Account(String id, String clientId, String accountNumber, Money balance, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.clientId = clientId;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Factory para cuenta NUEVA
    public static Account createNew(String id, String clientId, String accountNumber, Money initialBalance) {
        if (initialBalance.isNegative()) {
            throw new IllegalArgumentException("Saldo inicial negativo no permitido");
        }
        LocalDateTime now = LocalDateTime.now();
        // Al crear, fecha_creacion y fecha_actualizacion son iguales
        return new Account(id, clientId, accountNumber, initialBalance, "ACTIVO", now, now);
    }

    public void debit(Money amount) {
        validateActive();
        if (!this.balance.isGreaterThanOrEqual(amount)) {
            throw new InsufficientBalanceException("Saldo insuficiente en cuenta " + accountNumber);
        }
        this.balance = this.balance.subtract(amount);
        this.updatedAt = LocalDateTime.now(); // Actualiza fecha_actualizacion
    }

    public void credit(Money amount) {
        validateActive();
        this.balance = this.balance.add(amount);
        this.updatedAt = LocalDateTime.now(); // Actualiza fecha_actualizacion
    }

    private void validateActive() {
        if (!"ACTIVO".equals(this.status)) {
            throw new IllegalStateException("Cuenta no activa. Estado actual: " + this.status);
        }
    }
}