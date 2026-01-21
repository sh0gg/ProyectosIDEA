package procedimientos.ejercicios;

import procedimientos.logica.GestorProcedimientos;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.util.Scanner;

public class Ej4_FuncNEmpDepart {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        try (Connection conn = GestorConexiones.getConnection(TipoSGBD.SQLSERVER, "BDEMPRESA25", "sa", "abc123.")) {
            GestorProcedimientos gp = new GestorProcedimientos();

            System.out.print("Nome departamento: ");
            String nomeDep = sc.nextLine().trim();

            gp.numEmpregadosDepartamento(conn, nomeDep);
        }
    }
}
