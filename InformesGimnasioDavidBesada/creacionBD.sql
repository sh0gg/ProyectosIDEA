-- 1. Crear la base de datos (si no existe)
CREATE DATABASE IF NOT EXISTS dint;
USE dint;

-- 2. Borrar tablas si ya existen (para empezar de cero)
DROP TABLE IF EXISTS historial;
DROP TABLE IF EXISTS usuarios;

-- 3. Crear la tabla de Usuarios
-- Esta tabla guarda la información maestra de los clientes
CREATE TABLE usuarios (
    dni VARCHAR(9) PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    edad INT
);

-- 4. Crear la tabla de Historial
-- Esta tabla registra cada movimiento. Tiene una clave foránea (FK) que apunta al DNI del usuario.
CREATE TABLE historial (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dni_usuario VARCHAR(9),
    movimiento ENUM('ENTRADA', 'SALIDA') NOT NULL,
    registro_texto VARCHAR(255),
    fecha_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_usuario_historial 
        FOREIGN KEY (dni_usuario) 
        REFERENCES usuarios(dni) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
);

-- 5. Insertar datos de ejemplo para pruebas
INSERT INTO usuarios (dni, nombre, apellidos, edad) VALUES 
('12345678A', 'Pepe', 'Fanecas Ronchas', 30),
('87654321B', 'Maria', 'Garcia Gomez', 25),
('11122233C', 'David', 'Besada Ramilo', 23);