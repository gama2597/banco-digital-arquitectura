package pe.edu.tecsup.bancodigital.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "cliente")
@Data @AllArgsConstructor @NoArgsConstructor
public class ClientEntity {
    @Id
    private String id;

    private String nombre;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String documento;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
}