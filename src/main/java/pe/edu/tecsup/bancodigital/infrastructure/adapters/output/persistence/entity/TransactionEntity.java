package pe.edu.tecsup.bancodigital.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaccion")
@Data @AllArgsConstructor @NoArgsConstructor
public class TransactionEntity {
    @Id
    @Column(name = "transaccion_id")
    private String id;

    @Column(name = "cuenta_origen_id")
    private String cuentaOrigenId;

    @Column(name = "cuenta_destino_id")
    private String cuentaDestinoId;

    private BigDecimal monto;
    private BigDecimal comision;
    private String tipo;
    private String estado;
    private String descripcion;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
}