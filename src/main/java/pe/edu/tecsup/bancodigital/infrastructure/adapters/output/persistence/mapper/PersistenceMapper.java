package pe.edu.tecsup.bancodigital.infrastructure.adapters.output.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.tecsup.bancodigital.domain.model.Account;
import pe.edu.tecsup.bancodigital.domain.model.Client;
import pe.edu.tecsup.bancodigital.domain.model.Transaction;
import pe.edu.tecsup.bancodigital.infrastructure.adapters.output.persistence.entity.AccountEntity;
import pe.edu.tecsup.bancodigital.infrastructure.adapters.output.persistence.entity.ClientEntity;
import pe.edu.tecsup.bancodigital.infrastructure.adapters.output.persistence.entity.TransactionEntity;

@Mapper(componentModel = "spring")
public interface PersistenceMapper {

    // --- CLIENTE ---
    @Mapping(target = "fechaCreacion", source = "createdAt")
    ClientEntity toClientEntity(Client client);

    @Mapping(target = "createdAt", source = "fechaCreacion")
    Client toClientDomain(ClientEntity entity);

    // --- CUENTA ---
    @Mapping(target = "numeroCuenta", source = "accountNumber")
    @Mapping(target = "estado", expression = "java(account.getStatus() != null ? account.getStatus() : \"ACTIVO\")")
    @Mapping(target = "saldo", expression = "java(account.getBalance().amount())") // Extraer BigDecimal de Money
    @Mapping(target = "fechaCreacion", source = "createdAt")
    @Mapping(target = "fechaActualizacion", source = "updatedAt")
    AccountEntity toAccountEntity(Account account);

    @Mapping(target = "accountNumber", source = "numeroCuenta")
    @Mapping(target = "status", source = "estado")
    @Mapping(target = "balance", expression = "java(pe.edu.tecsup.bancodigital.domain.model.vo.Money.of(entity.getSaldo().doubleValue()))") // Envolver en Money
    @Mapping(target = "createdAt", source = "fechaCreacion")
    @Mapping(target = "updatedAt", source = "fechaActualizacion")
    Account toAccountDomain(AccountEntity entity);

    // --- TRANSACCION ---
    @Mapping(target = "cuentaOrigenId", source = "sourceAccountId")
    @Mapping(target = "cuentaDestinoId", source = "targetAccountId")
    @Mapping(target = "monto", expression = "java(transaction.getAmount().amount())")
    @Mapping(target = "comision", expression = "java(transaction.getCommission().amount())")
    @Mapping(target = "estado", source = "state")
    @Mapping(target = "tipo", source = "type")
    @Mapping(target = "fechaCreacion", source = "createdAt")
    TransactionEntity toTransactionEntity(Transaction transaction);

    // Nota: Normalmente no leemos transacciones en este caso de uso, pero si hiciera falta, agregas el inverso.
}