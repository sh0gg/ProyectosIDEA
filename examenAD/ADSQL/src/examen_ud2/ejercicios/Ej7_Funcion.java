package examen_ud2.ejercicios;

import examen_ud2.logica.GestorExamen;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;

public class Ej7_Funcion {
    public static void main(String[] args) throws Exception {
        try (Connection conn = GestorConexiones.getConnection(TipoSGBD.SQLSERVER, "BDEMPRESA25", "user", "pass")) {
            GestorExamen g = new GestorExamen();
            g.fnNumEmpDep(conn, "VENTAS");
            g.fnNumEmpDep(conn, "PRODUCCION");
        }
    }
}
