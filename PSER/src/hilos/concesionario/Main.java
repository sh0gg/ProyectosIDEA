package hilos.concesionario;

import java.util.ArrayList;
import java.util.List;

public class Main {

//    static final String NOMBRE_COCHE1 = "CIVIC";
//    static final String NOMBRE_COCHE2 = "ACCORD";
//    static final String NOMBRE_COCHE3 = "ODDISEY";
//    static final int UNIDADES_COCHE1 = 10;
//    static final int UNIDADES_COCHE2 = 7;
//    static final int UNIDADES_COCHE3 = 3;

    static final String[] MODELOS = {"CIVIC", "ACCORD", "ODDISEY"};
    static final int[] CANTIDAD_MODELOS = {10,7,3};

    static final int NUMERO_CLIENTES = 50;

    public static void main(String[] args) throws InterruptedException {
        List<Coche> coches = new ArrayList<>();
        int id = 0;
        for (int i = 0; i < MODELOS.length; i++) {
            for (int j = 0; j < CANTIDAD_MODELOS[i]; j++) {
                coches.add(new Coche(id++, MODELOS[i]));
            }
        }
//        for (int i = 0; i < UNIDADES_COCHE1; i++) {
//            coches.add(new Coche(id++, NOMBRE_COCHE1));
//        }
//        for (int i = 0; i < UNIDADES_COCHE2; i++) {
//            coches.add(new Coche(id++, NOMBRE_COCHE2));
//        }
//        for (int i = 0; i < UNIDADES_COCHE3; i++) {
//            coches.add(new Coche(id++, NOMBRE_COCHE3));
//        }


        Vendedor vendedor = new Vendedor(coches);

        List<Cliente> clientes = new ArrayList<>();
        for (int i = 0; i < NUMERO_CLIENTES; i++) {
            clientes.add(new Cliente(vendedor));
        }
        for (Cliente c : clientes) c.start();
        for (Cliente c : clientes) c.join();

        vendedor.mostrarVentas();

        System.out.println("Fin del ejercicio");

    }
}
