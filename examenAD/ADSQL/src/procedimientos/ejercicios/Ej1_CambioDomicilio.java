package procedimientos.ejercicios;

import procedimientos.logica.GestorProcedimientos;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.util.Scanner;

public class Ej1_CambioDomicilio {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        try (Connection conn = GestorConexiones.getConnection(TipoSGBD.SQLSERVER, "BDEMPRESA25", "sa", "abc123.")) {
            GestorProcedimientos gp = new GestorProcedimientos();

            System.out.print("NSS: ");
            String nss = sc.nextLine().trim();

            System.out.print("Rua: ");
            String rua = sc.nextLine().trim();

            System.out.print("Numero: ");
            int numero = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Piso: ");
            String piso = sc.nextLine().trim();

            System.out.print("CodPostal: ");
            String codPostal = sc.nextLine().trim();

            System.out.print("Localidade: ");
            String localidade = sc.nextLine().trim();

            gp.cambioDomicilio(conn, nss, rua, numero, piso, codPostal, localidade);
        }
    }
}
