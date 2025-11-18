package logica;

import clases.ClasesEquipo.Equipo;
import clases.ClasesEquipo.Equipos;
import jakarta.xml.bind.JAXBException;
import persistencia.EquiposJAXB;

public class GestorEquipos {

    public void mostrarEquipos(String rutaXML) throws JAXBException {
        try {
            Equipos equipos = EquiposJAXB.leerEquipos(rutaXML);

            if (equipos == null || equipos.getEquipo() == null) {
                System.out.println("No hay equipos para mostrar");
                return;
            }

            for (Equipo eq : equipos.getEquipo()) {
                System.out.println(eq);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        GestorEquipos g = new GestorEquipos();
        String rutaXML = "src/test/Equipos.xml";
        try {
            g.mostrarEquipos(rutaXML);
        } catch (JAXBException ex) {}
    }
}
