import util.GestorConexiones;
import util.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;

import static util.TipoSGBD.MYSQL;

public class MainTransacciones {
    public static void main(String[] args) {
        try (Connection conexion = GestorConexiones.getConnection(MYSQL, "dbpruebas", "root", "abc123.,")) {
            // Iniciar transacción
            conexion.setAutoCommit(false);  // Desactivar autocommit

            try {
                // Ejecutar varias operaciones dentro de la transacción

                // Insertar un usuario
                String sqlInsert = "INSERT INTO usuarios (id, nombre, apellido, saldo) VALUES (?, ?, ?, ?)";
                JDBCUtils.executeUpdate(conexion, sqlInsert, 22, "Miguel", "Rodríguez", 1700.00);

                // Actualizar saldo de un usuario
                String sqlUpdate = "UPDATE usuarios SET saldo = ? WHERE id = ?";
                JDBCUtils.executeUpdate(conexion, sqlUpdate, 2000.00, 22);

                // Confirmar transacción
                conexion.commit();
                System.out.println("Transacción completada exitosamente.");

            } catch (SQLException ex) {
                // Si hay un error, revertimos la transacción
                conexion.rollback();
                System.out.println("Error en la transacción. Cambios revertidos: " + ex.getMessage());
            } finally {
                // Restaurar autocommit
                conexion.setAutoCommit(true);
            }

        } catch (SQLException ex) {
            System.out.println("Error al gestionar la conexión: " + ex.getMessage());
        }
    }
}
