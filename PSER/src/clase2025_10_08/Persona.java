package clase2025_10_08;

public class Persona extends Thread {
    private final Oficina oficina;
    protected final Oficina.Buffer buffer;

    public Persona(Oficina oficina, Oficina.Buffer buffer) {
        this.oficina = oficina;
        this.buffer = buffer;
    }


    public static class Trabajador extends Persona {
        private final String nombre;

        public Trabajador(Oficina oficina, Oficina.Buffer buffer, String nombre) {
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

    public static class Jefe extends Persona {
        public Jefe(Oficina oficina, Oficina.Buffer buffer) {
            super(oficina, buffer);
        }

        @Override
        public void run() {
            buffer.entraOficina(this);
        }
    }
}
