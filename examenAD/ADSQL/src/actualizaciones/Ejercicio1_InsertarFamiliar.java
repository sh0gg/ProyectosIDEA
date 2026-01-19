package actualizaciones;

import clases.Familiar;
import logica.GestorEmpresa;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Scanner;

public class Ejercicio1_InsertarFamiliar {
    public static void main(String[] args) {

        // EJERCICIO 1:
        // Inserir un familiar dun empregado:
        // - NUM_FAMILIAR secuencial por empregado (1,2,3...)
        // - Non repetir o mesmo familiar para o mesmo empregado
        // - Mensaxes de erro claras

        Scanner sc = new Scanner(System.in);
        TipoSGBD tipo = TipoSGBD.SQLSERVER;

        try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "sa", "abc123.")) {

            GestorEmpresa gestor = new GestorEmpresa();

            Familiar f = new Familiar();

            System.out.println("NSS del empleado:");
            f.setNssEmpregado(sc.nextLine().trim());

            System.out.println("NSS del familiar:");
            f.setNssFamiliar(sc.nextLine().trim());

            System.out.println("Nombre:");
            f.setNome(sc.nextLine().trim());

            System.out.println("Apellido 1:");
            f.setApelido1(sc.nextLine().trim());

            System.out.println("Apellido 2 (enter si no):");
            String ap2 = sc.nextLine().trim();
            f.setApelido2(ap2.isEmpty() ? null : ap2);

            System.out.println("Fecha nacimiento (YYYY-MM-DD) (enter si no):");
            String fechaStr = sc.nextLine().trim();
            if (!fechaStr.isEmpty()) f.setDataNacemento(LocalDate.parse(fechaStr));
            else f.setDataNacemento(null);

            System.out.println("Parentesco:");
            f.setParentesco(sc.nextLine().trim());

            System.out.println("Sexo (H/M):");
            f.setSexo(sc.nextLine().trim().toUpperCase());

            gestor.insertarFamiliar(conn, f);

        } catch (Exception e) {
            System.out.println("Error en Ejercicio 1: " + e.getMessage());
        }
    }
}
