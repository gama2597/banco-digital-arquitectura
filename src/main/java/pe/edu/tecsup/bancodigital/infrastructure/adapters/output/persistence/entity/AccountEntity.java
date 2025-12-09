package pe.edu.tecsup.bancodigital.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cuenta")
@Data @AllArgsConstructor @NoArgsConstructor
public class AccountEntity {
    @Id
    @Column(name = "cuenta_id")
    private String id;

    @Column(name = "cliente_id")
    private String clientId;

    @Column(name = "numero_cuenta", unique = true)
    private String numeroCuenta;

    private BigDecimal saldo;

    @Column(name = "estado", nullable = false)
    private String estado; // ACTIVO, CERRADO

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
}