package logica;

import util.TipoSGBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
}
