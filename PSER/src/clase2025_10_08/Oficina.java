package clase2025_10_08;
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

    private final int NUM_TRABAJADORES = 5;
    private final String[] NOMBRES = {"Pacheco", "Rigoberto", "Severino", "Hermenegilda", "Pancracia"};
    private final ArrayList<Persona> genteEnOficina = new ArrayList<>(NUM_TRABAJADORES + 1);
    private final Buffer buffer = new Buffer();

    public Oficina() throws InterruptedException {

        // CREADOR DE TRABAJDORES (COMENTADO PQ QUIERO USAR NOMBRES)
        for (int i = 0; i <= NUM_TRABAJADORES-1; i++) {
            Persona.Trabajador trabajador = new Persona.Trabajador(this, buffer, NOMBRES[i]);
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

        Persona.Jefe jefe = new Persona.Jefe(this, buffer);
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

        public synchronized void entraOficina(Persona persona) {

            // Se comprueba quien entra
            if (persona instanceof Persona.Jefe) {
                // si es el jefe, se avisa
                entraElJefe = true;
                System.out.println("¡EL JEFE... HA LLEGADO!");
                notifyAll();
            } else if (persona instanceof Persona.Trabajador t) {
                // si es un trabajador
                if (!entraElJefe) {
                    // y el jefe aun no llegó, se echa a sobar
                    System.out.println(t.getNombre() + " ha llegado. zzzZZZzzzzZZZ");
                    try {
                        // y espera a que entre
                        wait();
                        System.out.println("*" + t.getNombre() + ", desperezándose* ... Bueeenos días jefe, aquí estoy trabajando :3");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    // si el jefe ya ha entrado, saluda y se pone a lo suyo
                    System.out.println(t.getNombre() + " ha llegado (tarde). ¡Hola jefe, me pongo a trabajar...!");
                }
            }
        }
    }

    // casi que no hay main pq el "programa" está en el constructor de oficina
    public static void  main(String[] args) throws InterruptedException {
        Oficina oficina = new Oficina();
    }
}
