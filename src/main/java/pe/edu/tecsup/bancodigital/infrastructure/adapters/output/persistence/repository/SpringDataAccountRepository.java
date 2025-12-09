package pe.edu.tecsup.bancodigital.infrastructure.adapters.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.tecsup.bancodigital.infrastructure.adapters.output.persistence.entity.AccountEntity;

import java.util.List;
import java.util.Optional;

public interface SpringDataAccountRepository extends JpaRepository<AccountEntity, String> {
    Optional<AccountEntity> findByNumeroCuenta(String numeroCuenta);
    boolean existsByNumeroCuenta(String numeroCuenta);
    List<AccountEntity> findByClientId(String clientId);
}