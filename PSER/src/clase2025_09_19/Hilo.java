package clase2025_09_19;

/*
Realice una aplicación que cree y ejecute simultáneamente MAX_HILOS ~ 10.

Cada hilo visualizará su nombre y un número, del 1 al MAX_NUM ~ 100

Se probará el comportamiento del programa con y sin una espera (Thread.sleep dentro del bucle)

Realizará la aplicación:

creando una clase que herede de Thread
creando una clase que no herede de Thread
sin crear una clase de forma explícita para los hilos
*/

// TODO: UF1_1

public class Hilo extends Thread {
    final static int MAX_HILOS = 10;
    final static int MAX_NUM = 100;

    public Hilo(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i <= 99; i++) {
            System.out.println(getName() + " > " + i);


            // revisalo pq andas perdido
        }
    }
}
