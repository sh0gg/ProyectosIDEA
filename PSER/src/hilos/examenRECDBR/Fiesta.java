package hilos.examenRECDBR;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fiesta {
    private static final int MAX_ASISTENTES = 6;

    private static final List<Asistente> asistentes = new ArrayList<>();

    static List<Bebida> listaBebidas = new ArrayList<>();

    static boolean isFinished = false;

    static class ControlInicio {
        private boolean inicio = false;

        public synchronized void esperarFiesta(String asistente) throws InterruptedException {
            while (!inicio) wait(); // todos bloquean hasta señal
            System.out.println(asistente + " entra a la fiesta!");
        }

        public synchronized void empezarFiesta() {
            inicio = true;
            System.out.println("Todo el mundo dentro. Empieza la fiesta!!!");
            notifyAll(); // despierta a todos a la vez
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ControlInicio c = new ControlInicio();

        // Generamos 50 bebidas
        for (int i = 0; i <= 50; i++) {
            listaBebidas.add(new Bebida(i));
        }

        Scanner sc = new Scanner(System.in);
        String nombre = "";
        while (asistentes.size() < MAX_ASISTENTES) {
            System.out.println("Ingrese el nombre del asistente: ");
            nombre = sc.nextLine();
            if (nombre.equals("fin")) {
                break;
            }
            asistentes.add(new Asistente(nombre,c));
            asistentes.getLast().start();
        }

        c.empezarFiesta();

        for (Asistente a : asistentes) {
            a.join();
        }

        System.out.println("\nEstadisticas de la fiesta: ");
        for (Asistente a : asistentes) {
            System.out.println("Nombre del asistente: " + a.getNombre());
            System.out.println("Numero de bebidas totales: " + a.getBebidasTomadas().size());
            System.out.println("Numero de bebidas que le gustaron: " + a.getNumBebidasRicas());
//            if (a.isPillado) {
//                System.out.println("Lo pillaron bebiendo alcohol!!");
//            } else {
//                System.out.println("No le pillaron bebiendo alcohol!!");
//            }
            System.out.println("=============================");
        }
    }

    // se coge una bebida (la ultima, por simplificar) sincronizado para que los asistentes no cojan la misma bebida a la vez
    synchronized static public Bebida getBebida() {
        Bebida bebida = listaBebidas.getLast();
        listaBebidas.removeLast();
        return bebida;
    }


}
