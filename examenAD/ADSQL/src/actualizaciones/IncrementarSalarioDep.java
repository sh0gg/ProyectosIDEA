package actualizaciones;

import clases.Departamento;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static persistencia.EmpresaService.*;

public class IncrementarSalarioDep {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        TipoSGBD tipo = TipoSGBD.SQLSERVER;

        try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "sa", "abc123.")) {

            System.out.println("¿Cuanto incrementamos el sueldo? ");
            int incremento = sc.nextInt();

            System.out.println("¿A qué departamento le incrementamos el sueldo? (numero) ");
            List<Departamento> lista = listarDepartamentos(conn);
            for (Departamento departamento : lista) {
                System.out.println(departamento.getNumDepartamento() + " - " + departamento.getNomeDepartamento());
            }
            int numDep = sc.nextInt();

            System.out.println("Incrementando salario de los empleados del departamento " + numDep + " en " + incremento + " euros.");

            int result = incrementarSalariosDepartamento(conn, incremento, numDep);

            int totalEmpleados = numEmpleadosFixDep(conn, numDep);

            if (result == totalEmpleados) {
                System.out.println("Se han actualizado los sueldos de los " + result + " empleados FIJOS.");
            } else {
                System.out.println("Algo ha fallado, no se han actualizado los sueldos.");
            }

        }

    }
}
