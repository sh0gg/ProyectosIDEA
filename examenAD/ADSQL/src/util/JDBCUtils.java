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

    public static Object executeScalar(Connection conexion, String sqlCount) {
        Object resultado = null;
        return resultado;
    }
}
