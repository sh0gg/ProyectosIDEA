package concesionario;

import java.util.*;

public class Vendedor {

    private List<Coche> coches;
    private List<Coche> vendidos;
    private Map<Cliente, Coche> ventas;

    public Vendedor(List<Coche> coches) {
        this.coches = coches;
        this.vendidos = new ArrayList<Coche>();
        this.ventas = new HashMap<Cliente, Coche>();
    }

    synchronized public List<Coche> getCoches() {
        return new ArrayList<>(coches);
    }

    synchronized public boolean venderCoche(Coche coche, Cliente cliente) {
        if (coche.vender()) {
            coche.setEnPrueba(false);
            coches.remove(coche);
            vendidos.add(coche);
            ventas.put(cliente, coche);
            notifyAll();
            return true;
        }
        return false;
    }

    synchronized public Coche asignarCocheLibre() throws InterruptedException {
        while (true) {

            // quedan coches a la venta?
            boolean quedanSinVender = false;
            for (Coche c : coches) {
                if (!c.isVendido()) {
                    quedanSinVender = true;
                    break;
                }
            }
            if (!quedanSinVender) {
                return null;
            }


            for (Coche c : coches) {
                if (!c.isVendido() && !c.isEnPrueba()) {
                    c.setEnPrueba(true);
                    return c;
                }
            }

            // solo llega aquí si no hay ningún coche disponible para probar
            wait();
        }
    }


    synchronized public void devolverCoche(Coche coche) {
        coche.setEnPrueba(false);
        notifyAll();
    }

    synchronized public void mostrarVentas() {
        System.out.println("\n--- LISTA DE VENTAS ---");
        for (Map.Entry<Cliente, Coche> entry : ventas.entrySet()) {
            Cliente cliente = entry.getKey();
            Coche coche = entry.getValue();
            System.out.println(cliente.getNombre() + " compró un " + coche.getModelo() + " " + coche.getId() + "[" + coche.getVisitas() + " visitas]");
        }
        System.out.println("------------------------");
    }

}
