import util.GestorConexiones;
import util.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;

import static util.TipoSGBD.*;

public class MainCount {
    public static void main(String[] args) {
        try (Connection conexion = GestorConexiones.getConnection(MYSQL, "dbpruebas", "root", "abc123.,")) {

            // Contar el número de usuarios
            String sqlCount = "SELECT COUNT(*) FROM usuarios";
            int totalUsuarios = (Integer) JDBCUtils.executeScalar(conexion, sqlCount);
            System.out.println("Total de usuarios: " + totalUsuarios);

        } catch (SQLException ex) {
            System.out.println("Error al contar los registros: " + ex.getMessage());
        }
    }
}
