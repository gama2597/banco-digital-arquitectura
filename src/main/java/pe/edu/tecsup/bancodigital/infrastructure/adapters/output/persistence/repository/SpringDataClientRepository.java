package pe.edu.tecsup.bancodigital.infrastructure.adapters.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.tecsup.bancodigital.infrastructure.adapters.output.persistence.entity.ClientEntity;

public interface SpringDataClientRepository extends JpaRepository<ClientEntity, String> {
}