package procedimientos.ejercicios;

import procedimientos.logica.GestorProcedimientos;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.util.Scanner;

public class Ej3_DepartControlaNProx {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        try (Connection conn = GestorConexiones.getConnection(TipoSGBD.SQLSERVER, "BDEMPRESA25", "sa", "abc123.")) {
            GestorProcedimientos gp = new GestorProcedimientos();

            System.out.print("Min proxectos: ");
            int n = Integer.parseInt(sc.nextLine().trim());

            gp.departamentosConMinProx(conn, n);
        }
    }
}
