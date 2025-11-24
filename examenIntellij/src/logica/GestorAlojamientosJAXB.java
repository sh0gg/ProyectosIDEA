package logica;

import clases.Alojamiento;
import clases.Alojamientos;
import clases.Habitacion;
import clases.Suite;
import jakarta.xml.bind.JAXBException;

import java.util.List;

import static persistenciaJAXB.AlojamientosJAXB.leerAlojamientos;

public class GestorAlojamientosJAXB {
    private String rutaAlojamientosXML;

    public GestorAlojamientosJAXB(String rutaAlojamientosXML) {
        this.rutaAlojamientosXML = rutaAlojamientosXML;
    }

    public void listarReservasActivas() {
        try {
            Alojamientos alojamientos = leerAlojamientos(rutaAlojamientosXML);
            List<Alojamiento> lista = alojamientos.getLista();

            if (lista == null || lista.isEmpty()) {
                System.out.println("No hay alojamientos en el XML.");
                return;
            }

            for (Alojamiento a : lista) {
                if (a instanceof Habitacion h) {
                    if (h.getEstado() == "ocupada") {
                        System.out.println("Habitacion(" + h.getTipo() + "): " + h.getNumero() + "\n" +
                                "Reserva: " + h.getReservas().getLast().getCodigo() + ", FechaInicio: " + h.getReservas().getLast().getFechaInicio() + ",  Usuario: " + h.getReservas().getLast().getUsuario().toString());
                    }
                } else if (a instanceof Suite s) {
                    if (s.getEstado() == "ocupada") {
                        System.out.println("Suite(" + s.getCategoria() + "): " + s.getNumero() + "\n" +
                                "Reserva: " + s.getReservas().getLast().getCodigo() + ", FechaInicio: " + s.getReservas().getLast().getFechaInicio() + ",  Usuario: " + s.getReservas().getLast().getUsuario().toString());
                    }
                }
                System.out.println("-----------------------------------");
                }


            } catch (JAXBException e) {
            System.out.println("Error JAXB al leer alojamientos: " + e.getMessage());
        }
    }

}
