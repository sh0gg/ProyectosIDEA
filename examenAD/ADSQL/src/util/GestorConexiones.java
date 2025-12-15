package util;

import java.sql.*;
import java.util.Objects;

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

    public static Connection getConnection(TipoSGBD tipo, String bd) throws SQLException {
        String url = "";
        if (Objects.requireNonNull(tipo) == TipoSGBD.SQLITE) {
            url = "jdbc:sqlite:" + bd;
        } else {
            System.out.println("Usa el otro método, este es solo para SQLite");
            return null;
        }
        return DriverManager.getConnection(url);
    }

    public static String obtenerMetadatos(Connection conn) throws SQLException {
        DatabaseMetaData meta = conn.getMetaData();
        StringBuilder sb =  new StringBuilder();
        sb.append("Driver: ").append(meta.getDriverName()).append("\n");
        sb.append("URL: ").append(meta.getURL()).append("\n");
        sb.append("User: ").append(meta.getUserName()).append("\n");
        sb.append("Tipo: ").append(meta.getDatabaseProductName()).append("\n");
        return sb.toString();
    }
    public static void borrarTablas(Connection conn, String... tablas) throws SQLException {
        try {
            conn.setAutoCommit(false); // Iniciar transacción
            try (Statement st = conn.createStatement()) {
                for (String tabla : tablas) {
                    if (tablaExiste(conn, tabla)) {
                        st.addBatch("DROP TABLE " + tabla);
                    }
                }
                st.executeBatch(); // Ejecuta las sentencias en lote
                conn.commit(); // Confirma la transacción
            } catch (SQLException e) {
                conn.rollback(); // Si algo falla, deshace los cambios
                throw new SQLException("Error al borrar tablas", e);
            }
        } catch (SQLException e) {
            throw new SQLException("Error en la conexión o transacción", e);
        }
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

    public static void ejecutarLoteTransaccional(Connection conn, String... sentenciasSQL) throws SQLException {
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
                throw new SQLException("Error al ejecutar el lote transaccional", e);
            }
        } catch (SQLException e) {
            throw new SQLException("Error en la conexión o transacción", e);
        }
    }
}
