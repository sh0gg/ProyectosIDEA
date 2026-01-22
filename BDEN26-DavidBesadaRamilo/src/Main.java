import clases.Fotografia;
import logica.GestorExposicion;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// DNI : 53612286e
// Nombre: David Besada

public class Main {
    public static void main(String[] args) throws SQLException {

        try (Connection conn = GestorConexiones.getConnection(
                TipoSGBD.SQLSERVER,
                "BDEXPOSICION26",
                "sa",
                "abc123."
        )) {
            // 1. AMPLIAR EL MODELO DE DATOS ACTUAL MEDIANTE LA CREACION DE NUEVAS ESTRUCTURAS DE ALMACENAMIENTO
            // DESDE LA APLICACIÓN

            GestorExposicion ge =  new GestorExposicion();

            ge.crearFuncionesYProcs(conn);

            // CREAMOS TABLA LABORATORIO
            ge.crearLaboratorios(conn);

            // CREAMOS TABLA FOTOGRAFO-LABORATORIO PARA RELACIONARLAS (N:N, ya que un laboratorio puede trabajar con
            // varios fotografos y estos con uno o varios laboratorios)

            ge.crearFotografosLabo(conn);

            // 2. DESARROLLAR UN MÉTODO QUE PERMITA DAR DE ALTA UNA COLECCION DE NUEVAS FOTOGRAFIAS DE UN FOTOGRAFO
            // Y EXPONERLAS EN UNA EXPOSICION EXISTENTE

            System.out.println("Vamos a añadir una nueva colección a una exposición.\n" +
                    "Ej: AMELIE en CORPUS con la siguiente colección:");

            List<Fotografia> coleccion = new ArrayList<>();
            coleccion.add(new Fotografia("A", "30x20", "1999-12-01", 'N', "DOCUMENTAL", "SOCIAL"));
            coleccion.add(new Fotografia("B", "30x20", "1999-02-01", 'N', "ARTISTICA", "HORIZONTAL", "PAISAJE"));

            ge.addColeccionExposicion(conn, "AMELIE", "CORPUS", coleccion);

            // 3. IMPLEMENTAR UN METODO QUE PERMITA TRASLADAR LAS FOTOGRAFIAS DE UNA EXPOSICION A OTRA

            System.out.println("Vamos a trasladar las fotografias de INVISIBLE a VISION.");
            String nombreExp1 = "INVISIBLE";
            String nombreExp2 = "VISION";
            ge.moverFotos(conn, nombreExp1, nombreExp2);

        }

    }
}