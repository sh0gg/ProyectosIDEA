package util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

// DNI : 53612286e
// Nombre: David Besada

public class GestorConexiones {
    public static Connection getConnection(TipoSGBD tipo, String bd, String user, String pass) throws SQLException {
        String url = "";
        switch (tipo) {

            case MYSQL:
                url = "jdbc:mysql://localhost:3306/" + bd + "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
                break;

            case SQLSERVER:
                url = "jdbc:sqlserver://localhost:1433;" + "databaseName=" + bd + ";" + "encrypt=false;trustServerCertificate=true";
                break;

            case SQLITE:
                System.out.println("Usa el método sobrecargado que no tiene el parametro de usuario y contraseña");
                url = "jdbc:sqlite:" + bd;
                return DriverManager.getConnection(url);
        }

        return DriverManager.getConnection(url, user, pass);
    }

    public static boolean tablaExiste(Connection conn, String tabla) throws SQLException {
        try (ResultSet rs = conn.getMetaData().getTables(null, null, tabla, null)) {
            while (rs.next()) {
                if (Objects.equals(rs.getString("TABLE_NAME"), tabla)) {
                    return true; // La tabla existe
                }
            }
        }
        return false; // La tabla no existe
    }

    public static boolean ejecutarLoteTransaccional(Connection conn, ArrayList<String> sentenciasSQL) throws SQLException {
        try {
            conn.setAutoCommit(false); // Iniciar transacción
            try (Statement st = conn.createStatement()) {
                for (String sql : sentenciasSQL) {
                    st.addBatch(sql); // Añadir cada sentencia al lote
                }
                st.executeBatch(); // Ejecutar el lote
                conn.commit(); // Confirmar la transacción
            } catch (SQLException e) {
                conn.rollback(); // Deshacer cambios en caso de error
                return false;
            }
        } catch (SQLException e) {
            throw new SQLException("Error en la conexión o transacción", e);
        }
        return true;
    }
}
