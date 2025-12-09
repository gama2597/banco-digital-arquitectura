package pe.edu.tecsup.bancodigital.infrastructure.adapters.input.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.tecsup.bancodigital.application.ports.input.CreateAccountUseCase;
import pe.edu.tecsup.bancodigital.application.ports.input.GetAccountUseCase;
import pe.edu.tecsup.bancodigital.application.ports.input.TransferUseCase;
import pe.edu.tecsup.bancodigital.domain.model.Account;
import pe.edu.tecsup.bancodigital.domain.model.Transaction;
import pe.edu.tecsup.bancodigital.infrastructure.adapters.input.rest.dto.AccountResponse;
import pe.edu.tecsup.bancodigital.infrastructure.adapters.input.rest.dto.CreateAccountRequest;
import pe.edu.tecsup.bancodigital.infrastructure.adapters.input.rest.dto.TransferRequest;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Tag(name = "Gestión Bancaria", description = "Endpoints para cuentas y transferencias")
@Slf4j
public class AccountController {

    private final CreateAccountUseCase createAccountUseCase;
    private final TransferUseCase transferUseCase;
    private final GetAccountUseCase getAccountUseCase;

    @PostMapping
    @Operation(summary = "Crear Cuenta", description = "Crea una nueva cuenta bancaria para un cliente existente")
    public ResponseEntity<Account> createAccount(@RequestBody @Valid CreateAccountRequest request) {

        var command = CreateAccountUseCase.CreateAccountCommand.builder()
                .clientId(request.clientId())
                .initialBalance(request.initialBalance())
                .build();

        Account account = createAccountUseCase.create(command);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/transfer")
    @Operation(summary = "Realizar Transferencia", description = "Transfiere dinero aplicando comisión de $5.00")
    public ResponseEntity<Transaction> transfer(@RequestBody @Valid TransferRequest request) {

        var command = TransferUseCase.TransferCommand.builder()
                .sourceAccountId(request.sourceAccountId())
                .targetAccountNumber(request.targetAccountNumber())
                .amount(request.amount())
                .description(request.description())
                .build();

        Transaction transaction = transferUseCase.transfer(command);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar Saldo", description = "Obtiene el detalle y saldo actual de una cuenta")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable String id) {

        // 1. Llamar al dominio
        var account = getAccountUseCase.getAccount(id);

        // 2. Mapear a DTO de respuesta (ocultando lógica de dominio)
        var response = new AccountResponse(
                account.getId(),
                account.getAccountNumber(),
                account.getClientId(),
                account.getBalance().amount(), // Extraemos el BigDecimal del Value Object Money
                account.getStatus(),
                account.getCreatedAt()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-client/{clientId}")
    @Operation(summary = "Cuentas por Cliente", description = "Lista todas las cuentas de un cliente")
    public ResponseEntity<List<AccountResponse>> getAccountsByClient(@PathVariable String clientId) {

        List<Account> accounts = getAccountUseCase.getAccountsByClient(clientId);

        List<AccountResponse> response = accounts.stream()
                .map(acc -> new AccountResponse(
                        acc.getId(),
                        acc.getAccountNumber(),
                        acc.getClientId(),
                        acc.getBalance().amount(),
                        acc.getStatus(),
                        acc.getCreatedAt()
                ))
                .toList();

        return ResponseEntity.ok(response);
    }
}