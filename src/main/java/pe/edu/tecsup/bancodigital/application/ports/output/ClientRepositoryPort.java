package pe.edu.tecsup.bancodigital.application.ports.output;

import pe.edu.tecsup.bancodigital.domain.model.Client;
import java.util.Optional;

public interface ClientRepositoryPort {
    Client save(Client client);
    Optional<Client> findClientById(String id);
    boolean existsById(String id);
}