-- Insertar clientes
INSERT INTO cliente (id, nombre, email, documento, fecha_creacion) VALUES
('CLI001', 'Juan Pérez', 'juan.perez@email.com', '12345678', CURRENT_TIMESTAMP),
('CLI002', 'María García', 'maria.garcia@email.com', '87654321', CURRENT_TIMESTAMP),
('CLI003', 'Carlos López', 'carlos.lopez@email.com', '11223344', CURRENT_TIMESTAMP);

-- Insertar cuentas
INSERT INTO cuenta (cuenta_id, cliente_id, numero_cuenta, saldo, estado, fecha_creacion, fecha_actualizacion) VALUES
('CTA001', 'CLI001', '0001-234567', 1000.00, 'ACTIVO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('CTA002', 'CLI002', '0001-234568', 2500.00, 'ACTIVO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('CTA003', 'CLI003', '0001-234569', 500.00, 'ACTIVO', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertar transacción de ejemplo
INSERT INTO transaccion
(transaccion_id, cuenta_origen_id, cuenta_destino_id, monto, comision, tipo, estado, descripcion, fecha_creacion)
VALUES
('TXN001', 'CTA001', 'CTA002', 100.00, 5.00, 'TRANSFERENCIA', 'COMPLETADA', 'Transferencia de prueba', CURRENT_TIMESTAMP);