package hilos.hackers;

import java.util.Random;

public class Hacker extends Thread {

    private final ControlAtaque control;
    private final TipoClave tipo;
    private final int maxChars;
    private final Random random = new Random();

    public Hacker(String nombre, ControlAtaque control, TipoClave tipo, int maxChars) {
        super(nombre);
        this.control = control;
        this.tipo = tipo;
        this.maxChars = maxChars;
    }

    @Override
    public void run() {

        while (!control.isEncontrada()) {

            String clave = generarClaveAleatoria();

            control.probarClave(getName(), clave);
        }
    }

    private String generarClaveAleatoria() {
        int longitud = random.nextInt(maxChars) + 1;  // 1..maxChars
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < longitud; i++) {
            char c;

            switch (tipo) {
                case ALFABETICA:
                    c = generarLetra();
                    break;

                case NUMERICA:
                    c = generarDigito();
                    break;

                default:
                case MIXTA:
                    c = random.nextBoolean() ? generarLetra() : generarDigito();
                    break;
            }

            sb.append(c);
        }

        return sb.toString();
    }

    private char generarLetra() {
        return (char) ('a' + random.nextInt(26)); // a..z
    }

    private char generarDigito() {
        return (char) ('0' + random.nextInt(10)); // 0..9
    }
}
