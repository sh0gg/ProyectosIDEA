package examen_ud2.ejercicios;

import examen_ud2.logica.GestorExamen;
import util.GestorConexiones;
import util.TipoSGBD;

import java.math.BigDecimal;
import java.sql.Connection;

public class Ej4_Actualizaciones {
    public static void main(String[] args) throws Exception {
        try (Connection conn = GestorConexiones.getConnection(TipoSGBD.SQLSERVER, "BDEMPRESA25", "user", "pass")) {
            GestorExamen g = new GestorExamen();
            g.subirSalarioDep(conn, 1, new BigDecimal("100.00"));
            g.borrarEmpleado(conn, "222B");
        }
    }
}
