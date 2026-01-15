package actualizaciones;

import clases.Departamento;
import clases.Proxecto;
import persistencia.EmpresaService;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static persistencia.EmpresaService.*;

public class CambioDepProyecto {
    public static void main(String[] args) {

        // Cambiar el departamento asignado a un proyecto
        // EJERCICIO 3

        Scanner sc = new Scanner(System.in);

        TipoSGBD tipo = TipoSGBD.SQLSERVER;

        try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "sa", "abc123.")) {

            List<Proxecto> listaProx = listaProxectos(conn);
            for (Proxecto prox : listaProx) {
                System.out.println(prox.getNomeProxecto());
            }
            System.out.println("¿Que proyecto quieres actualizar? (escribe el nombre)");
            String nomProxecto = sc.nextLine();


            List<Departamento> listaDep = listarDepartamentos(conn);
            for (Departamento departamento : listaDep) {
                System.out.println(departamento.getNomeDepartamento());
            }
            System.out.println("¿Que departamento le asignamos?");
            String nomDepartamento = sc.nextLine();

            System.out.println("Asignando " + nomDepartamento + " al proyecto " + nomProxecto);

            int result = cambiarDepartamentoProyecto(conn, nomDepartamento, nomProxecto);

            if  (result > 0) {
                System.out.println("¡Se ha actualizado correctamente!");

                listaProx = listaProxectos(conn);

                for (Proxecto prox : listaProx) {
                    System.out.println(prox.toString());
                }
            } else {
                System.out.println("Algo ha fallado, revisa los parámetros introducidos.");
            }


        } catch (SQLException e) {
            System.out.println("Error al obtener los datos del proyecto");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
