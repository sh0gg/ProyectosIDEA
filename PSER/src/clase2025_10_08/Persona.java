package clase2025_10_08;

public abstract class Persona extends Thread {
    protected final Oficina.Buffer buffer;

    public Persona(Oficina.Buffer buffer) {
        this.buffer = buffer;
    }

    public abstract void entraOficina();

    @Override
    public void run() {
        entraOficina();
    }


    public static class Trabajador extends Persona {
        private final String nombre;

        public Trabajador(Oficina.Buffer buffer, String nombre) {
            super(buffer);
            this.nombre = nombre;
        }

        public String getNombre() {
            return nombre;
        }

        @Override
        public void entraOficina() {
            buffer.entraOficinaTrabajador(this);
        }
    }


    public static class Jefe extends Persona {
        public Jefe(Oficina.Buffer buffer) {
            super(buffer);
        }

        @Override
        public void entraOficina() {
            buffer.entraOficinaJefe(this);
        }
    }
}
