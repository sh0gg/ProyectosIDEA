package examen_ud2.ejercicios;

import examen_ud2.logica.GestorExamen;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;

public class Ej0_MetadatosConexion {
    public static void main(String[] args) throws Exception {
        try (Connection conn = GestorConexiones.getConnection(TipoSGBD.SQLSERVER, "BDEMPRESA25", "user", "pass")) {
            GestorExamen g = new GestorExamen();
            g.mostrarMetadatos(conn);
            g.mostrarColumnas(conn, "PR_EXAM_DEP"); // si no existe, dará error (normal si aún no instalaste)
        }
    }
}
