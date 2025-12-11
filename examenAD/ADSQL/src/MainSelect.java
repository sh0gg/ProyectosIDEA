import logica.GestorConexiones;
import persistencia.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static util.TipoSGBD.*;

public class MainSelect {
    public static void main(String[] args) {
        try (Connection conexion = GestorConexiones.getConnection(MYSQL, "dbpruebas", "root", "abc123.,")) {

            // Realiza una consulta SELECT
            String sql = "SELECT * FROM usuarios WHERE saldo > ?";
            List<Map<String, Object>> usuarios = JDBCUtils.executeQuery(conexion, sql, 1000);

            // Procesa los resultados
            for (Map<String, Object> usuario : usuarios) {
                System.out.println(usuario);
            }

        } catch (SQLException ex) {
            System.out.println("Error al realizar la consulta: " + ex.getMessage());
        }
    }
}
