package actualizaciones;

import clases.VehiculoPropio;
import clases.VehiculoRenting;
import logica.GestorEmpresa;
import util.GestorConexiones;
import util.TipoSGBD;

import java.sql.Connection;
import java.time.LocalDate;

public class Ejercicio2_InsertarVehiculo {
    public static void main(String[] args) {

        // EJERCICIO 2:
        // Insertar vehículo Propio o Renting, guardando en tablas correspondientes.
        // Matrícula única.

        TipoSGBD tipo = TipoSGBD.SQLSERVER;

        try (Connection conn = GestorConexiones.getConnection(tipo, "BDEMPRESA25", "sa", "abc123.")) {

            GestorEmpresa gestor = new GestorEmpresa();

            // VehiculoPropio:
            VehiculoPropio vp = new VehiculoPropio();
            vp.setMatricula("1234ABC");
            vp.setMarca("Toyota");
            vp.setModelo("Corolla");
            vp.setTipoCombustible("G");
            vp.setDataCompra(LocalDate.parse("2023-05-15"));
            vp.setPrezoCompra(15000);

            gestor.insertarVehiculo(conn, vp);

            // VehiculoRenting:
            VehiculoRenting vr = new VehiculoRenting();
            vr.setMatricula("5678XYZ");
            vr.setMarca("Ford");
            vr.setModelo("Fiesta");
            vr.setTipoCombustible("D");
            vr.setDataInicio(LocalDate.parse("2023-07-01"));
            vr.setPrezoMensual(200);
            vr.setMesesContratados(24);

            gestor.insertarVehiculo(conn, vr);

        } catch (Exception e) {
            System.out.println("Error en Ejercicio 2: " + e.getMessage());
        }
    }
}
