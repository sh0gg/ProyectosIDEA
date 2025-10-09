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
    private ArrayList<Persona> genteEnOficina = new ArrayList<>(NUM_TRABAJADORES + 1);
    private Buffer buffer = new Buffer();

    public Oficina(){

        // CREADOR DE TRABAJDORES (COMENTADO PQ QUIERO USAR NOMBRES)
        for (int i = 0; i <= NUM_TRABAJADORES-1; i++) {
            Trabajador trabajador = new Trabajador(this, buffer, NOMBRES[i]);
            genteEnOficina.add(trabajador);

            try {
                Thread.sleep((int)(Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // DEBUG: espera para entrar el jefe
//        try {
//            Thread.sleep(1500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Jefe jefe = new Jefe(this, buffer);
        genteEnOficina.add(jefe);

        // Para que entren aleatoriamente a la oficina
        java.util.Collections.shuffle(genteEnOficina);
        for (Persona persona : genteEnOficina) {
            persona.start();
        }

    }

    public class Persona extends Thread {
        private Oficina oficina;
        protected Buffer buffer;

        public Persona(Oficina oficina, Buffer buffer) {
            this.oficina = oficina;
            this.buffer = buffer;
        }
    }

    public class Trabajador extends Persona {
        private String nombre;

        public Trabajador(Oficina oficina, Buffer buffer, String nombre) {
            super(oficina, buffer);
            this.nombre = nombre;
        }

        public String getNombre() {
            return nombre;
        }

        @Override
        public void run() {
            buffer.entraOficina(this);
        }

    }

    public class Jefe extends Persona {
        public Jefe(Oficina oficina, Buffer buffer) {
            super(oficina, buffer);
        }

        @Override
        public void run() {
            buffer.entraOficina(this);
        }
    }

    public class Buffer {
        private boolean entraElJefe;

        public synchronized void entraOficina(Persona persona) {
            if (persona instanceof Jefe) {
                entraElJefe = true;
                System.out.println("EL JEFE HA LLEGADO!");
                notifyAll();
            } else if (persona instanceof Trabajador) {
                Trabajador t = (Trabajador) persona;
                if (!entraElJefe) {
                    System.out.println(t.getNombre() + " ha llegado. ZZZZZZZZZZZ");
                    try {
                        wait();
                        System.out.println("(" + t.getNombre() + " desperezándose), buenos días jefe, aquí estoy trabajando");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println(t.getNombre() + " ha llegado. Hola jefe!, me pongo a trabajar...");
                }
            }
        }
    }

    public static void  main(String[] args) {
        Oficina oficina = new Oficina();
    }
}
