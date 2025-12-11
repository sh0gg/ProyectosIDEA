import clases.Usuario;
import logica.GestorConexiones;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.TipoSGBD.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conexion = GestorConexiones.getConnection(MYSQL, "dbpruebas", "root", "abc123.,")) {

            /*String sqlCreateTable = "CREATE TABLE usuarios (" +
                    "id INT PRIMARY KEY, " +
                    "nombre VARCHAR(50), " +
                    "apellido VARCHAR(50), " +
                    "email VARCHAR(100), " +
                    "fecha_nacimiento DATE, " +
                    "ciudad VARCHAR(50), " +
                    "pais VARCHAR(50), " +
                    "saldo DECIMAL(10,2)" +
                    ")";

            PreparedStatement psCreate = conexion.prepareStatement(sqlCreateTable);
            psCreate.executeUpdate();
            System.out.println("Tabla 'datosUsuarios' creada correctamente");

            String sqlInsert = "INSERT INTO usuarios (id, nombre, apellido, email, fecha_nacimiento, ciudad, pais, saldo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement psInsert = conexion.prepareStatement(sqlInsert);

// Ejemplo de insertar varios registros
            Object[][] datosUsuarios = {
                    {1, "Ana", "García", "ana.garcia@email.com", "1990-05-12", "Madrid", "España", 1500.50},
                    {2, "Luis", "Martínez", "luis.martinez@email.com", "1985-03-23", "Barcelona", "España", 2300.00},
                    {3, "María", "López", "maria.lopez@email.com", "1992-07-15", "Valencia", "España", 800.75},
                    {4, "Juan", "Pérez", "juan.perez@email.com", "1988-11-30", "Sevilla", "España", 1200.00},
                    {5, "Carmen", "Sánchez", "carmen.sanchez@email.com", "1995-02-10", "Bilbao", "España", 950.30},
                    {6, "David", "Gómez", "david.gomez@email.com", "1983-09-17", "Málaga", "España", 3100.00},
                    {7, "Laura", "Ruiz", "laura.ruiz@email.com", "1991-12-05", "Zaragoza", "España", 450.00},
                    {8, "Pedro", "Díaz", "pedro.diaz@email.com", "1987-06-22", "Granada", "España", 670.50},
                    {9, "Lucía", "Fernández", "lucia.fernandez@email.com", "1993-04-18", "Córdoba", "España", 1250.00},
                    {10, "Jorge", "Torres", "jorge.torres@email.com", "1989-08-09", "Valladolid", "España", 990.00},
                    {11, "Sofía", "Ramírez", "sofia.ramirez@email.com", "1994-10-28", "Alicante", "España", 700.25},
                    {12, "Alberto", "Morales", "alberto.morales@email.com", "1986-01-13", "Salamanca", "España", 150.75},
                    {13, "Patricia", "Castro", "patricia.castro@email.com", "1990-03-02", "Oviedo", "España", 2100.00},
                    {14, "Fernando", "Vargas", "fernando.vargas@email.com", "1982-07-21", "Pamplona", "España", 5000.50},
                    {15, "Isabel", "Méndez", "isabel.mendez@email.com", "1991-05-30", "Santander", "España", 320.00},
                    {16, "Raúl", "Hernández", "raul.hernandez@email.com", "1988-12-11", "Burgos", "España", 1300.90},
                    {17, "Elena", "Jiménez", "elena.jimenez@email.com", "1995-09-08", "Almería", "España", 840.20},
                    {18, "Sergio", "Ruiz", "sergio.ruiz@email.com", "1984-02-25", "Logroño", "España", 2700.00},
                    {19, "Marta", "Ortega", "marta.ortega@email.com", "1993-11-14", "Castellón", "España", 400.50},
                    {20, "Ricardo", "Navarro", "ricardo.navarro@email.com", "1987-04-07", "Toledo", "España", 950.00}
            };

            for (Object[] u : datosUsuarios) {
                psInsert.setInt(1, (Integer) u[0]);
                psInsert.setString(2, (String) u[1]);
                psInsert.setString(3, (String) u[2]);
                psInsert.setString(4, (String) u[3]);
                psInsert.setDate(5, Date.valueOf((String) u[4])); // Convierte String a java.sql.Date
                psInsert.setString(6, (String) u[5]);
                psInsert.setString(7, (String) u[6]);
                psInsert.setDouble(8, (Double) u[7]);
                int filasInsertadas = psInsert.executeUpdate();
                System.out.println("Filas insertadas: " + filasInsertadas);
            }*/

            String sqlSelect = "SELECT * FROM usuarios WHERE ciudad LIKE 'A%'";
            // String sqlSelect = "SELECT * FROM departamento";
            PreparedStatement psSelect = conexion.prepareStatement(sqlSelect);

            ResultSet rs = psSelect.executeQuery();

            int filas = 0;
            while (rs.next()) {
                filas++;
                int id =  rs.getInt("id");
                String nombre = rs.getString("nombre") + " " + rs.getString("apellido");
                String ciudad =  rs.getString("ciudad");
                System.out.println("[" + id + " - " + nombre + "] " + ciudad
                );
            }
            System.out.println("Total de usuarios: " + filas);

        } catch (SQLException ex) {
            System.out.println("Error al realizar la conexión " + ex.getMessage());
        }

    }


//            ResultSet rs = st.executeQuery("");
//            int filas = 0;
//            while (rs.next()) {
//                filas++;
//                System.out.println(" | NumDepartamento: " + rs.getString(1) +
//                                   " | NomeDepartamento: " + rs.getString(2) +
//                                   " | NSSDirector: "  + rs.getString(3) +
//                                   " | Nome: "  + rs.getString(4) +
//                                   " | Apelido1: "  + rs.getString(5));
//            }
//            System.out.println("Numero de resultados: " + filas);
//            String sqlInsert = "";
//            PreparedStatement psInsert = conexion.prepareStatement(sqlInsert);
}