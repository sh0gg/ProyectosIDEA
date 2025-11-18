package logica;

import clases.ClasesCorredor.Corredor;
import clases.ClasesCorredor.Corredores;
import clases.ClasesCorredor.Velocista;
import jakarta.xml.bind.JAXBException;
import persistencia.CorredoresJAXB;

public class GestorCorredores {

    public void mostrarCorredores(String rutaXML) throws JAXBException {
        try {
            Corredores corredores = CorredoresJAXB.leerCorredores(rutaXML);

            if (corredores == null || corredores.getLista() == null) {
                System.out.println("No hay corredores a mostrar");
                return;
            }

            for (Corredor c : corredores.getLista()) {
                if (c instanceof Velocista) {
                    System.out.println("VELOCISTA" + c);
                } else {
                    System.out.println("FONDISTA" + c);
                }
            }
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        GestorCorredores g = new GestorCorredores();
        String rutaXML = "src/test/Corredores.xml";
        try {
            g.mostrarCorredores(rutaXML);
        } catch (JAXBException e) {}
    }
}
