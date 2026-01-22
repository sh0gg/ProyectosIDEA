package logica;

import clases.Fotografia;
import persistencia.ExposicionService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

// DNI : 53612286e
// Nombre: David Besada

public class GestorExposicion {
    public void crearLaboratorios(Connection conn) throws SQLException {
        ExposicionService es = new ExposicionService();
        if (es.crearTablaLaboratorio(conn)) {
            System.out.println("Tabla LABORATORIO creada correctamente");
        } else  {
            System.out.println("Tabla LABORATORIO no ha sido creada");
        }
    }

    public void crearFotografosLabo(Connection conn) throws SQLException {
        ExposicionService es = new ExposicionService();
        if (es.crearTablaFotografoLaboratorio(conn)) {
            System.out.println("Tabla LABORATORIO creada correctamente");
        } else  {
            System.out.println("Tabla LABORATORIO no ha sido creada");
        }
    }

    public void moverFotos(Connection conn, String nombreExp1, String nombreExp2) throws SQLException {
        ExposicionService es = new ExposicionService();

        System.out.println("NOMBRE EXPOSICION:" +  nombreExp1 + " " + ExposicionService.prObtenerLocalidadProvincia(conn, nombreExp1));
        try {
            List<String> res = ExposicionService.prDatosFotosExposicion(conn, nombreExp1);
            for (String s : res) System.out.println(s);

        } catch (SQLException e) {
            System.out.println("ERROR (pr_DatosFotosExposicion): " + e.getMessage());
        }

        int resultado = es.moverFotosExpo(conn, nombreExp1, nombreExp2);
        if (resultado == 1) {
            System.out.println("Se han trasladado las fotos correctamente.");
            System.out.println("Ahora vamos a borrar la exposicion de origen.");
            if(es.borrarExposicion(conn, nombreExp2)) {
                System.out.println("Se ha borrado la exposicion de origen.");
            } else {
                System.out.println("Algo ha impedido borrar la exposicion de origen.");
            }
        }
        if (resultado == 2) {
            System.out.println("¡Ha fallado el traslado! Cambios revertidos.");
        }
    }

    public void addColeccionExposicion(Connection conn, String nombreFotografo, String nombreExposicion, List<Fotografia> coleccion) throws SQLException {
        ExposicionService es = new ExposicionService();
        int resultado = es.addColeccionExposicion(conn, nombreFotografo, nombreExposicion, coleccion);
        if (resultado == 1) {
            System.out.println("Se han añadido a " + nombreExposicion + " las siguientes fotografías de " +  nombreFotografo);
            for (Fotografia f : coleccion) {
                System.out.println(" - " + f.toString());
            }
            System.out.println("Se ha actualizado el numero de fotos de " + nombreFotografo + " a " + ExposicionService.fnNEmpDepart(conn, nombreFotografo) +".");
        }  else if (resultado == 0) {
            System.out.println("Ha fallado el alta, una foto de la colección no estaba bien definida.");
        } else if (resultado == -1) {
            System.out.println("Ha fallado el alta, el fotógrafo no se encuentra en la BD.");
        } else if  (resultado == -2) {
            System.out.println("Ha fallado el alta, la exposición no se encuentra en la BD.");
        }

    }

    public void crearFuncionesYProcs(Connection conn) throws SQLException {
        ExposicionService es = new ExposicionService();
        es.crearFnCalcularFotografias(conn);
        es.crearPrObtenerLocalidadProvincia(conn);
        es.crearPrDatosFotosExposicion(conn);
    }

}
