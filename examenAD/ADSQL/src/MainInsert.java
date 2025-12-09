import logica.GestorConexiones;
import persistencia.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;

import static util.TipoSGBD.*;

public class MainInsert {
    public static void main(String[] args) {
        try (Connection conexion = GestorConexiones.getConnection(MYSQL, "dbpruebas", "root", "abc123.,")) {

            // Insertar un nuevo usuario
            String sqlInsert = "INSERT INTO usuarios (id, nombre, apellido, saldo) VALUES (?, ?, ?, ?)";
            int filasInsertadas = JDBCUtils.executeUpdate(conexion, sqlInsert, 21, "Carlos", "De La Torre", 1500.50);
            System.out.println("Filas insertadas: " + filasInsertadas);

            // Actualizar saldo de un usuario (ejemplo de UPDATE)
            String sqlUpdate = "UPDATE usuarios SET saldo = ? WHERE id = ?";
            int filasActualizadas = JDBCUtils.executeUpdate(conexion, sqlUpdate, 2000.00, 21);
            System.out.println("Filas actualizadas: " + filasActualizadas);

            // Eliminar un usuario (ejemplo de DELETE)
            String sqlDelete = "DELETE FROM usuarios WHERE id = ?";
            int filasEliminadas = JDBCUtils.executeUpdate(conexion, sqlDelete, 21);
            System.out.println("Filas eliminadas: " + filasEliminadas);

        } catch (SQLException ex) {
            System.out.println("Error al realizar la operación: " + ex.getMessage());
        }
    }
}
