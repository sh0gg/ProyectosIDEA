package util;

import java.sql.*;
import java.util.*;

public class JDBCUtils {

    // Método para ejecutar INSERT, UPDATE o DELETE
    public static int executeUpdate(Connection conn, String sql, Object... params) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            return ps.executeUpdate();
        }
    }

    // Método para ejecutar SELECT y retornar los resultados en una lista de mapas
    public static List<Map<String, Object>> executeQuery(Connection conn, String sql, Object... params) throws SQLException {
        List<Map<String, Object>> resultados = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData meta = rs.getMetaData();
                int columnas = meta.getColumnCount();
                while (rs.next()) {
                    Map<String, Object> fila = new HashMap<>();
                    for (int i = 1; i <= columnas; i++) {
                        fila.put(meta.getColumnLabel(i), rs.getObject(i));
                    }
                    resultados.add(fila);
                }
            }
        }
        return resultados;
    }

    // Método para ejecutar SELECT y retornar un único valor (como COUNT, SUM, etc.)
    public static Object executeScalar(Connection conn, String sql, Object... params) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getObject(1);
                }
                return null;
            }
        }
    }
}
