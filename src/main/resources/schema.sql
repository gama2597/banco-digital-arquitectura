-- 1. Tabla CLIENTE
CREATE TABLE IF NOT EXISTS cliente (
    id VARCHAR(50) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    documento VARCHAR(20) NOT NULL UNIQUE,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CHECK (email LIKE '%@%.%')
);

-- 2. Tabla CUENTA
CREATE TABLE IF NOT EXISTS cuenta (
    cuenta_id VARCHAR(50) PRIMARY KEY,
    cliente_id VARCHAR(50) NOT NULL,
    numero_cuenta VARCHAR(20) NOT NULL UNIQUE,
    saldo DECIMAL(15,2) NOT NULL DEFAULT 0.00,
    estado VARCHAR(20) NOT NULL DEFAULT 'ACTIVO',
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id),
    CHECK (saldo >= 0),
    CHECK (estado IN ('ACTIVO', 'CERRADO'))
);

-- 3. Tabla TRANSACCION
CREATE TABLE IF NOT EXISTS transaccion (
    transaccion_id VARCHAR(50) PRIMARY KEY,
    cuenta_origen_id VARCHAR(50) NOT NULL,
    cuenta_destino_id VARCHAR(50) NOT NULL,
    monto DECIMAL(15,2) NOT NULL,
    comision DECIMAL(15,2) NOT NULL DEFAULT 5.00,
    tipo VARCHAR(20) NOT NULL DEFAULT 'TRANSFERENCIA',
    estado VARCHAR(20) NOT NULL DEFAULT 'PENDIENTE',
    descripcion VARCHAR(255),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (cuenta_origen_id) REFERENCES cuenta(cuenta_id),
    FOREIGN KEY (cuenta_destino_id) REFERENCES cuenta(cuenta_id),
    CHECK (monto > 0),
    CHECK (cuenta_origen_id != cuenta_destino_id),
    CHECK (tipo IN ('TRANSFERENCIA', 'DEPOSITO', 'RETIRO')),
    CHECK (estado IN ('PENDIENTE', 'COMPLETADA', 'FALLIDA', 'CANCELADA'))
);

-- 4. Índices (Performance)
CREATE INDEX IF NOT EXISTS idx_cuenta_cliente ON cuenta(cliente_id);
CREATE INDEX IF NOT EXISTS idx_transaccion_origen ON transaccion(cuenta_origen_id);
CREATE INDEX IF NOT EXISTS idx_transaccion_destino ON transaccion(cuenta_destino_id);
CREATE INDEX IF NOT EXISTS idx_transaccion_fecha ON transaccion(fecha_creacion);