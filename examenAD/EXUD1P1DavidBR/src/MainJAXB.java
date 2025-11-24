import logica.GestorAlojamientosJAXB;

public class MainJAXB {
    public static void main(String[] args) {

        String rutaAlojamientosXML = "src/test/AlojamientosHotelJAXB.xml";

        GestorAlojamientosJAXB ga = new GestorAlojamientosJAXB(rutaAlojamientosXML);

        System.out.println("Listar reservas activas");
        ga.listarReservasActivas();
    }
}
