package procedimientos.ejercicios;

import procedimientos.logica.GestorProcedimientos;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;

public class Ej0_InstalarTodo {
    public static void main(String[] args) throws Exception {

        try (Connection conn = GestorConexiones.getConnection(
                TipoSGBD.SQLSERVER,
                "BDEMPRESA25",
                "sa",
                "abc123."
        )) {
            GestorProcedimientos gp = new GestorProcedimientos();
            gp.instalar(conn);
        }
    }
}
