package hilos.clase2025_10_17;


/* La premisa del ejercicio es que dos pescadores se van respondiendo el uno al otro de un mismo archivo de texto. */

import java.io.*;

public class Conversacion {
    public class Pescador extends Thread {
        int id;

        Pescador(int id) {
            this.id = id;
        }

        boolean leerLinea(String archivo, int n) throws IOException {
            BufferedReader bf_in = new BufferedReader(new FileReader(archivo));
            String linea;
            int contador = 0;
            while ((linea = bf_in.readLine()) != null) {
                if (contador == n) {
                    System.out.println("Pescador " + id + ": " + linea);
                    return true;
                }
                contador++;
            }
            return false;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (Conversacion.this.bloqueo) {
                    // espera que llegue el turno o el final del archivo
                    while (Conversacion.this.turno != id && !Conversacion.this.terminado) {
                        try {
                            Conversacion.this.bloqueo.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }

                    if (Conversacion.this.terminado) {
                        return;
                    }

                    // lee e imprime linea
                    boolean hayLinea;
                    try {
                        hayLinea = leerLinea(Conversacion.this.archivo, Conversacion.this.lineaActual);
                    } catch (IOException e) { // si no quedan lineas o no la puede leer salta excepcion
                        System.err.println(e.getMessage());
                        hayLinea = false;
                    }

                    if (!hayLinea) {
                        // NO MORE LINEASSSSS
                        Conversacion.this.terminado = true;
                        Conversacion.this.bloqueo.notifyAll();
                        return;
                    }

                    // pasamos a la siguiente linea y turno
                    Conversacion.this.lineaActual++;
                    if (id == 1) {
                        Conversacion.this.turno = 2;
                    } else {
                        Conversacion.this.turno = 1;
                    }
                    Conversacion.this.bloqueo.notifyAll();
                }
            }
        }
    }

    private final Object bloqueo = new Object();
    private int turno = 1;
    private int lineaActual = 0;
    private boolean terminado = false;

    Pescador pescador1;
    Pescador pescador2;
    String archivo;

    public Conversacion(String archivo) {
        pescador1 = new Pescador(1);
        pescador2 = new Pescador(2);
        this.archivo = archivo;
        pescador1.start();
        pescador2.start();
    }

    public static void main(String[] args) {
        Conversacion conversacion = new Conversacion("src/clase2025_10_17/hola.txt");
    }

}

