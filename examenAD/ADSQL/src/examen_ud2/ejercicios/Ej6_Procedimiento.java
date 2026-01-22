package examen_ud2.ejercicios;

import examen_ud2.logica.GestorExamen;
import util.GestorConexiones;
import util.TipoSGBD;

import java.math.BigDecimal;
import java.sql.Connection;

public class Ej6_Procedimiento {
    public static void main(String[] args) throws Exception {
        try (Connection conn = GestorConexiones.getConnection(TipoSGBD.SQLSERVER, "BDEMPRESA25", "user", "pass")) {
            GestorExamen g = new GestorExamen();
            g.procCambioSalario(conn, "111A", new BigDecimal("50.00"));
            g.procDatosProy(conn, 1);
            g.procDepConMinProyExecute(conn, 1);
        }
    }
}
