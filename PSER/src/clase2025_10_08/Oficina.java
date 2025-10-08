package clase2025_10_08;

import java.util.ArrayList;

public class Oficina {

    private final int NUM_TRABAJADORES = 5;
    private ArrayList<Persona> genteEnOficina = new ArrayList<>(NUM_TRABAJADORES+1);

    public class Persona extends Thread {
        public boolean haLlegado;
        public Persona(boolean haLlegado) {
            this.haLlegado = haLlegado;
        }
    }

    public class Trabajador extends Persona {
        private String nombre;

        public Trabajador(boolean haLlegado, String nombre) {
            super(haLlegado);
            this.nombre = nombre;
        }

    }

    public class Jefe extends Persona{
        public Jefe(boolean haLlegado) {
            super(haLlegado);
        }
    }

    public class Buffer {
        private boolean entraElJefe;

        public synchronized void entraOficina (Persona persona){

        }
    }



}
