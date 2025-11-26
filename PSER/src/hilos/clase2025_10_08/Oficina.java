package hilos.clase2025_10_08;
/*
En una oficina, los empleados (hilos) van llegando a la oficina y se ponen a trabajar… pero solo si ya ha llegado el jefe (el otro hilo), porque en caso contrario se ponen a dormir.

En cuanto llega el jefe, inmediatamente se ponen a trabajar sin que éste se de cuenta.



Simúlese la situación anterior, visualizando los mensajes adecuados para ver que se cumplen las condiciones indicadas. Los mensajes pueden ser:

{nombreEmpleado} ha llegado. ZZZZZZ  (si no está el jefe)
{nombreEmpleado} ha llegado. Hola jefe!, me pongo a trabajar...  (si ya está el jefe)
EL JEFE HA LLEGADO!  (dice el jefe gritando al llegar, por lo que despierta a todos)
({nombreEmpleado} desperazándose), buenos días jefe, aquí estoy trabajando  (si acaba de llegar el jefe)
*/

import java.util.ArrayList;

public class Oficina {

    private final String[] NOMBRES = {"Pacheco", "Rigoberto", "Severino", "Hermenegilda", "Pancracia"};
    private final int NUM_TRABAJADORES = NOMBRES.length;
    private final ArrayList<Persona> genteEnOficina = new ArrayList<>(NUM_TRABAJADORES + 1);
    private final Buffer buffer = new Buffer();

    public Oficina() throws InterruptedException {

        // CREADOR DE TRABAJDORES (COMENTADO PQ QUIERO USAR NOMBRES)
        for (int i = 0; i <= NUM_TRABAJADORES-1; i++) {
            Persona.Trabajador trabajador = new Persona.Trabajador(buffer, NOMBRES[i]);
            genteEnOficina.add(trabajador);

            try {
                Thread.sleep((int)(Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /*
        DEBUG: espera para entrar el jefe
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */

        Persona.Jefe jefe = new Persona.Jefe(buffer);
        genteEnOficina.add(jefe);

        // Para que entren aleatoriamente a la oficina
        java.util.Collections.shuffle(genteEnOficina);
        for (Persona persona : genteEnOficina) {
            Thread.sleep(50);
            persona.start();
        }

    }

    public static class Buffer {
        private boolean entraElJefe;

        public synchronized void entraOficinaJefe(Persona.Jefe jefe) {
            entraElJefe = true;
            System.out.println("¡EL JEFE... HA LLEGADO!");
            notifyAll();
        }

        public synchronized void entraOficinaTrabajador(Persona.Trabajador t) {
            if (!entraElJefe) {
                System.out.println(t.getNombre() + " ha llegado. zzzZZZzzzzZZZ");
                try {
                    wait();
                    System.out.println("*" + t.getNombre() + ", desperezándose* ... Bueeenos días jefe, aquí estoy trabajando :3");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(t.getNombre() + " ha llegado (tarde). ¡Hola jefe, me pongo a trabajar...!");
            }
        }
    }

    // casi que no hay main pq el "programa" está en el constructor de oficina
    public static void main(String[] args) throws InterruptedException {
        Oficina oficina = new Oficina();
    }
}
