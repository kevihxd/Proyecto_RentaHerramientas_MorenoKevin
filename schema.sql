-- 1. Creación de la Base de Datos
CREATE DATABASE IF NOT EXISTS db_renta_herramientas;
USE db_renta_herramientas;

-- 2. Tabla de Usuarios
-- Almacena Clientes, Proveedores y Administradores
CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL, -- Almacenará el hash de BCrypt
    rol ENUM('CLIENTE', 'PROVEEDOR', 'ADMIN') NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- 3. Tabla de Herramientas
-- El campo 'imagen_url' guardará la ruta del archivo físico en /uploads/
CREATE TABLE IF NOT EXISTS herramientas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio_dia DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL DEFAULT 1,
    imagen_url VARCHAR(255), 
    estado ENUM('DISPONIBLE', 'MANTENIMIENTO', 'DEBAJA') DEFAULT 'DISPONIBLE',
    proveedor_id BIGINT,
    CONSTRAINT fk_herramienta_proveedor FOREIGN KEY (proveedor_id) 
        REFERENCES usuarios(id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- 4. Tabla de Reservas/Alquileres
CREATE TABLE IF NOT EXISTS reservas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    herramienta_id BIGINT NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    costo_total DECIMAL(10, 2) NOT NULL,
    estado ENUM('PENDIENTE', 'CONFIRMADA', 'ALQUILADA', 'FINALIZADA', 'CANCELADA') DEFAULT 'PENDIENTE',
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_reserva_cliente FOREIGN KEY (cliente_id) 
        REFERENCES usuarios(id),
    CONSTRAINT fk_reserva_herramienta FOREIGN KEY (herramienta_id) 
        REFERENCES herramientas(id)
) ENGINE=InnoDB;

-- 5. Tabla de Facturación
CREATE TABLE IF NOT EXISTS facturas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    reserva_id BIGINT NOT NULL UNIQUE,
    folio_unico VARCHAR(50) NOT NULL UNIQUE,
    fecha_emision TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    monto_pagado DECIMAL(10, 2) NOT NULL,
    metodo_pago VARCHAR(50) DEFAULT 'Simulado/Digital',
    CONSTRAINT fk_factura_reserva FOREIGN KEY (reserva_id) 
        REFERENCES reservas(id)
) ENGINE=InnoDB;

-- 6. Inserción de datos iniciales (Opcional - para pruebas)
-- La contraseña de ejemplo es 'admin123' (hash de prueba)
INSERT INTO usuarios (nombre, apellido, email, password, rol) VALUES 
('Admin', 'Campus', 'admin@herramientas.com', '$2a$10$8.UnVuG9HHgffUDAlk8q6uy5iy.E.S9mCH8mS2.L.B9z/i0S45uO.', 'ADMIN');

-- Verificación de la estructura
SHOW TABLES;
