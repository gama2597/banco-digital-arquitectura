package pe.edu.tecsup.bancodigital.infrastructure.adapters.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.tecsup.bancodigital.infrastructure.adapters.output.persistence.entity.ClientEntity;
import java.util.List;

public interface SpringDataClientRepository extends JpaRepository<ClientEntity, String> {

    // Búsqueda "Google-style": Busca si el texto coincide con ID, Documento o parte del Nombre
    @Query("SELECT c FROM ClientEntity c WHERE " +
            "LOWER(c.id) = LOWER(:texto) OR " +
            "c.documento = :texto OR " +
            "LOWER(c.nombre) LIKE LOWER(CONCAT('%', :texto, '%'))")
    List<ClientEntity> searchByText(@Param("texto") String texto);
}