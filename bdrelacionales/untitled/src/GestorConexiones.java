package es.ieschandomonte.ud3.empresa25.persistencia;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Se encarga de abrir conexiones y mostrar metadatos de la conexión.
 * Cubre el Ejercicio 5 de la actividad. :contentReference[oaicite:1]{index=1}
 */
public class GestorConexiones {

    public static Connection abrirConexion(TipoBD tipo) throws SQLException {
        ConfiguracionBD cfg = ConfiguracionBD.para(tipo);

        try {
            // Carga explícita del driver (forma "clásica", bien vista en el módulo)
            if (cfg.getDriverClassName() != null) {
                Class.forName(cfg.getDriverClassName());
            }
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se pudo cargar el driver JDBC: " + cfg.getDriverClassName(), e);
        }

        return DriverManager.getConnection(cfg.getUrl(), cfg.getUsuario(), cfg.getPassword());
    }

    public static void mostrarMetadatosConexion(Connection conn, TipoBD tipo) throws SQLException {
        DatabaseMetaData md = conn.getMetaData();
        System.out.println("========== METADATOS CONEXIÓN (" + tipo + ") ==========");
        System.out.println("BD producto     : " + md.getDatabaseProductName() + " " + md.getDatabaseProductVersion());
        System.out.println("Driver          : " + md.getDriverName() + " " + md.getDriverVersion());
        System.out.println("URL             : " + md.getURL());
        System.out.println("Usuario         : " + md.getUserName());
        System.out.println("Só lectura?     : " + md.isReadOnly());
        System.out.println("=====================================================");
    }
}
